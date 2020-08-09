package lancereally;

import java.util.Scanner;

import static java.lang.Class.forName;

public class MyMinStack{
    static int min;
    static int end;
    static int capacity;
    int[] myStack;
    int[] minStack;
    public MyMinStack(int capacity){
        this.capacity = capacity;
        myStack = new int[capacity];
        minStack = new int[capacity];
        end = 0;
        min = 0;
        System.out.println("最小栈创建！");
    }

    public void push(int node){
        if (capacity > end){
            if (myStack[end] == 0){
                minStack[min] = node;
                myStack[end] = node;
            }else
            myStack[++end] = node;

            if (node < minStack[min]){
                minStack[++min] = node;
            }
        }
        else
            System.out.println(this.getClass().getName()+": StackOutOfBoundsException!");
        /**The Better(index = 0)
         * myStack[index++] = node;
         * minStack[index++] = Math.min(node,minStack[index-1])
         * */
    }

    public void pop(){
        if (capacity > 0){
            //此处未考虑相同元素
            if (minStack[min] == myStack[end]){
                min--;
            }
            myStack[end] = 0;
            end--;
            }else
            System.out.println(this.getClass().getName()+": StackOutOfBoundsException!");
        /**The Better(index = 0)
         * index--;
         * */
    }

    public static void main(String[] args) {
        int op;
        Scanner s = new Scanner(System.in);
        int capacity = s.nextInt();
        MyMinStack stack = new MyMinStack(capacity);
        while((op = s.nextInt()) != -1){
            if (op == 0) System.out.println(stack.minStack[min]);//stack.minStack[index-1]
            else if (op == 1) stack.push(s.nextInt());
            else if (op == 2) stack.pop();
            else
                System.out.println("恁输的是个锤子???");
        }
    }
}
