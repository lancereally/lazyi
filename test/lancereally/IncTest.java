package lancereally;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class IncTest {
    Inc inc = new Inc();
    @org.junit.Test
    public void aaa() {
        assertEquals(2,inc.aaa());
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String a = s.next();
        String b = s.next();
        LCS(a,b);
//        try {
//            justDoIt("fake");
//        }catch (MyException e){
//            e.printStackTrace();
//        }
    }

    private static void justDoIt(String s) throws MyException{
        if (s.length()<5)
            throw new MyException(s);
    }

    public static void LCS(String a, String b) {
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();
        int x = c1.length;
        int y = c2.length;
        int[][] c3 = new int[x][y];
        //初始化
        for (int j = 0; j < c3[0].length; j++) {//第0行第j列全部赋值为0
            c3[0][j] = 0;
        }
        for (int i = 0; i < c3.length; i++) {//第i行，第0列全部为0
            c3[i][0] = 0;
        }
        //比较
        for (int i = 1; i < c1.length; i++) {
            for (int j = 1; j < c2.length; j++) {
                if (c1[i - 1] == c2[j - 1])
                    c3[i][j] = c3[i - 1][j - 1] + 1;
                else
                    c3[i][j] = Math.max(c3[i][j - 1], c3[i - 1][j]);
            }
        }
        //输出
        int end = c3[x-1][y-1]+1;
        int start = 0;
        for (int i = 0; i<x ;i++){
            if (c3[i][y-1] == 1)
                start = i-1;
        }

        for(int m = 0; m < c3.length; m++){
			for(int n = 0; n < c3[m].length; n++){
				System.out.print(c3[m][n]);
			}
			System.out.println();
    }
        //输出最大重复字段
        String target = a.substring(start, start+end);
        System.out.println(target);
        //找出重复次数
        int count;
        int num = 0;
        while (a.contains(target)) {
            a = a.substring(a.indexOf(target) + target.length());
            num ++;
        }
        System.out.println(num);
    }
}