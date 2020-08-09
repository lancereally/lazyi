package Test;

import javafx.scene.web.WebHistory;

import javax.swing.*;
import java.util.*;

public class Test {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        HashMap<String,String> map = new HashMap();
        String userType = "";
        String modelLib = "";
        int n = s.nextInt();
        for (int i = 0; i < n; i++){
            userType = s.next();
            modelLib = s.next();
            //用户类型，模型文件（n/1的关系）
            //输出用户类型 按字母表顺序
            if(map.containsKey(modelLib))
                userType = map.get(modelLib)+" "+userType;
            map.put(modelLib, userType);
        }
        int index;
        ArrayList<String> list = new ArrayList<>();
        for(Map.Entry e: map.entrySet()){
            //要求按字母表顺序输出用户类型
                index = e.toString().indexOf("=");
                userType = e.toString().substring(index+1);
                String[] item = userType.split(" ");
                Arrays.sort(item);
                System.out.print(e.getKey()+" ");
                for (String str : item)
                    System.out.print(str+" ");
            //.toString().replaceAll("="," ")

        }

//        Scanner s = new Scanner(System.in);
//        String S = s.nextLine();//
//        String SS = s.nextLine();
//        System.out.println(S);
//        String[] ss = S.split(" ");
//        for (String a:ss) System.out.println(a);
    }

    public static int fun(){
        Scanner s = new Scanner(System.in);
        int d = s.nextInt();
        int w = s.nextInt();
        StringBuilder str = new StringBuilder("");
        while(s.hasNext()){
            str.append(s.nextInt());
        }//终止输入
        int n = (int)str.length()/2;
        int[] position = new int[n];
        int[] supply = new int[n];
        char[] c = str.toString().toCharArray();
        for(int i = 0; i < n; i++){
            position[i] = c[i];
            System.out.println(c[i]);
        }
        return -1;
    }
}
/**
 * 沙漠，旅人，补给站
 * 输入模式：
 * 起点到终点的举例D 初始带水量L
 * N个数据，各个补给站到起点的举例
 * N个数据，各个补给站提供的水量（不论多少水都是一块钱
 * 求：最少补给次数
 * 到不了返回-1
 * */