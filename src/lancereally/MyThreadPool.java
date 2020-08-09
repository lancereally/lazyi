package lancereally;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * name:自定义简单线程池
 * author: 徐东方
 */
public class MyThreadPool{
    /**
     * 存放线程的集合
     */
    private ArrayList<MyThread> threads;
    /**
     * 任务队列
     */
    private ArrayBlockingQueue<Runnable> taskQueue;
    /**
     * 线程池初始限定大小
     */
    private static int threadNum;
    /**
     * 已经工作的线程数目
     */
    private static int workThreadNum = 0;

    private final ReentrantLock mainLock = new ReentrantLock();

    public MyThreadPool(int initPoolNum) {
        threadNum = initPoolNum;
        threads = new ArrayList<>(initPoolNum);
        //任务队列初始化为线程池线程数的四倍
        taskQueue = new ArrayBlockingQueue<>(initPoolNum * 4);
        workThreadNum = 0;
    }

    public MyThreadPool() {
    }

    public void execute(Runnable runnable) {
        try {
            mainLock.lock();
            //线程池未满，每加入一个任务则开启一个线程
            if (workThreadNum < threadNum) {
                MyThread myThread = new MyThread(runnable);
                myThread.start();
                threads.add(myThread);
                workThreadNum++;
            }
            //线程池已满，放入任务队列，等待有空闲线程时执行
            else {
                //队列已满，无法添加时，拒绝任务
                if (!taskQueue.offer(runnable)) {
                    rejectTask();
                }
            }
        } finally {
            mainLock.unlock();
        }
    }

    private void rejectTask() {
        System.out.println("任务队列已满，无法继续添加，请扩大您的初始化线程池！");
    }

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(20);
        Runnable task = () ->
        {
            System.out.println(Thread.currentThread().getName() + "执行中");
            Thread.currentThread().interrupt();
            if (Thread.currentThread().isInterrupted())
                System.out.println(Thread.currentThread().getName() + "已完成任务");
        };

        for (int i = 0; i < 10; i++) {
            myThreadPool.execute(task);
        }
    }

    class MyThread extends Thread {
        private Runnable task;

        public MyThread(Runnable runnable) {
            this.task = runnable;
        }

        @Override
        public void run() {
            //该线程一直启动着，不断从任务队列取出任务执行
            while (true) {
                //如果初始化任务不为空，则执行初始化任务
                if (task != null) {
                    task.run();
                    task = null;
                }
                //否则去任务队列取任务并执行
                else {
                    Runnable queueTask = taskQueue.poll();
                    if (queueTask != null)
                        queueTask.run();
                }
            }
        }
    }
}