package lancereally;


import lancereally.entity.Human;
import lancereally.entity.Japanese;

import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class myMap {
    public static void main(String[] args) {
//        //用接口引用对象会使程序更加灵活。
//        Map<Integer, Integer> map= new HashMap<>();
//        //写死了，不灵活 ：HashMap<Integer, Integer> map= new HashMap<>();
//        map.put(1, 1);
//        map.put(2, 2);
//        map.put(114, 514);
//        //loopThrough1(map);
//        String s = "musicId_1";
//        if(s.contains("m"))
//        System.out.println(s);
//
//        Human human = new Human();
//        Japanese japanese = new Japanese();
//        japanese.setAge(20);
//        Japanese j = new Japanese();
//        j.setAge(30);
//        human.setJapanese(japanese);
//        System.out.println(human.getJapanese().getAge());
//        human.setJapanese(j);
//        System.out.println(human.getJapanese().getAge());

        List<Japanese> mList = new ArrayList<>();
        List<Japanese> ll = new ArrayList<>();
        Japanese a = new Japanese();
        a.setAge(10);
        Japanese b = new Japanese();
        b.setAge(20);
        Japanese c = new Japanese();
        c.setAge(30);
        Japanese d = new Japanese();
        d.setAge(40);
        Japanese e = new Japanese();
        e.setAge(50);
        mList.add(d);
        mList.add(a);
        mList.add(e);
//        for (Japanese j:mList)
//            System.out.println(j.getAge());
        int size = mList.size();

        ll.add(b);
        ll.add(c);
        mList.addAll(ll);
        //map()
        List<Integer> list1 = new ArrayList<>();
        list1 = mList.stream().map(Japanese::getAge).collect(Collectors.toList());
        //reduce()
        int sum = mList.stream()
                .map(Japanese::getAge)
                .reduce(Integer::min).get();
        System.out.println("sum "+sum);
        //filter()&peek()
        mList = mList.stream()
                .filter(Japanese -> {
            return Japanese.getAge() > 30;
        })
                .peek(System.out::println)
                .collect(Collectors.toList());
        //Array to stream
        int[] intA = {1,2};
        Stream.of(intA).peek(System.out::println);
        //sorted()
        mList = mList.stream().sorted(Comparator.comparing(Japanese::getAge).reversed()).collect(Collectors.toList());
        for (Japanese j:mList)
            System.out.println(j.getAge());
//        String s = new String("123") + new String("123") ;
//        System.out.println(s.intern());
//        String b = "123123";
//        System.out.println(s == b);
    }
    public static void loopThrough1(Map map, List list){
        /**
         * 迭代1 : map提供的KeySet（）方法
         * 1.先用Set封装，再使用迭代器 iterator
         * 2.增强for循环
         * */
        Set<Integer> set = map.entrySet();
        Iterator iterator = set.iterator();
        Object toolMan;
        while(iterator.hasNext()){
            toolMan = iterator.next();
            System.out.println(toolMan);
        }

//        for (Object key : map.keySet()){
//            System.out.println(key+" "+map.get(key));
//        }
    }

    public static void loopThrough2(Map<String, Integer> map){
        /**
         * 迭代2 : map提供的EntrySet（）方法
         * 1.先用Set封装，再使用迭代器 iterator
         * 2.增强for循环
         * */
        for (Map.Entry e : map.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }
    }
}
