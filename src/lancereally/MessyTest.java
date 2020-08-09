package lancereally;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessyTest extends MyThreadPool{

    public static void main(String[] args) {
        //向上转型不需要强制类型转化
        //向下转型必须强制类型转化
//        MyThreadPool m = new MessyTest();
//        MessyTest mm = (MessyTest)new MyThreadPool();

//        final String a = "1";
////        a = "2";
//        String b = new String("1");
//        System.out.println(a == b);
        System.out.println(Integer.parseInt("-99"));
        String str = "99m99", s = "";
        if(str.contains("")) s = str.replaceAll("\\s","");
        System.out.println(s);
        str = str.trim();
        //java正则表达式
        /**1、matcher()：仅仅有在整个字符串全然匹配才返回true，否则返回false。
         可是假设部分匹配成功。匹配的位置将移动到下次匹配的位置
         2、lookingAt()：总是从第一个字符開始匹配。不管匹配成功与否，都不会再继续向下匹配
         3、find()：部分匹配，假设匹配成功。返回true，匹配的位置移动到下次匹配的位置。*/
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(str);
        //判断是否能匹配
        System.out.println(m.matches());
        System.out.println(m.find());
    }

    public static void splitString(){
        //1
        String s = "1,2,3,4";
        String[] ss = s.split(",");
        for(String a : ss){
            System.out.println(a);
        }
        //2
//        StringTokenizer st = new StringTokenizer(s,",");
//        while(st.hasMoreTokens()) {
//            System.out.println(st.nextToken());
//        }
    }
    public static int Fibonacci(int n) {
        //匿名内部类 1.推荐代码10行以下 2.只用一次 3.马上用到 4.单独命名并不会更好
        int f = new MessyTest(){int i = 3; public int ff(){return i;}}.ff();
        if(n < 3) return 1;
        int[] a = new int[n];
        a[0] = 1;
        a[1] = 1;
        for(int i = 2; i<n; i++){
            a[i] = a[i-1] + a[i-2];
        }
        return a[n-1];
    }

    public static void random(int n, int x){
        for (int i = 0; i < n; i++){
            System.out.println((int)Math.ceil(Math.random()*x));
        }
    }

//手撕求平方根
    public static double SQRT(double n, double inf)
    {
        double s = 0, t = n;
        if(n < 1) t = 1;
        while(t - s > inf)
        {
            double mid = (t + s) / 2;
            if(mid * mid > n)
                t = mid;
            else if(mid * mid == n)
                return mid;
            else
                s = mid;
        }
        return (t + s) / 2;
    }
//递归 上楼梯问题，跳一步（1|2
    public static int jump1(int number ){
        if(number==1) return 1;
        if(number==2) return 2;
        if(number==3) return 3;
        return jump1(number-1) + jump1(number-2);
    }
    //贪心， 上楼梯问题, 跳一步（1-n）
    public static int jump2(int number ){
        return 1<<(number-1);
    }
}

 class Main{
    public static String func(int[][] x, int n , int m, int k){
        boolean flag = true;
        int i = 0, j = m-1;
        while(i<n&&j>0){
            if(x[i][j] > k){
                j--;
            }else if(x[i][j] == k){
                return "Yes";
            }else if(x[i][j] < k){
                i++;
            }else
                return "false";
        }
        return "false";
    }
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int k = s.nextInt();
        int[][] x = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                x[i][j] = s.nextInt();
            }
        }
        System.out.print(func(x,n,m,k));
    }
}