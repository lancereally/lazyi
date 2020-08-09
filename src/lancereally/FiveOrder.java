package lancereally;

import java.util.Arrays;

public class FiveOrder {
    public static void main(String[] args) {
        int[] a = new int[]{1,3,5,0,10,5,9};
       test(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

    public static int[] order_1(int[] a){
        //选择排序[不稳定
        int temp;
        for (int i = 0;i < a.length; i++){
            for (int j = i+1;j < a.length; j++){
                if (a[i] > a[j]){
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        return a;
    }
public static int[] test(int[] a,int s, int e){
        if (s >= e) return a;
        int start = s;
        int end = e;
        int l = a.length;
        int key = a[start];
        while (start<end){
            while (start<end && a[end]>=key)
                end--;
            if (start<end) a[start++] = a[end];
            while (start<end && a[start]<=key)
                start++;
            if (start<end) a[end--] = a[start];
        }
        a[start] = key;
        test(a,s,start-1);
        test(a,start+1,e);
        return a;
}

    public static int[] order_2(int[] a){
        //冒泡排序【稳定
        for (int i = 0; i < a.length - 1; i++) {
            //将无序区的最大值移动到最右的有序区
            for (int j = 0; j < a.length - 1 - i; j++) {
                //如果左边的值大于右边的值，则互换位置
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

    public static int[] order_3(int[] a){
        //插入排序【稳定
        for (int i = 1; i < a.length; i++) {
            // 设置数组中的第2个元素为第一次循环要插入的数据
            int insert = a[i];
            int j = i-1;
            while (j >= 0 && insert < a[j]){
                // 如果要插入的元素小于第j个元素,就将第j个元素向后移动
                a[j+1] = a[j];
                j--;
            }
            // 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
            a[j+1] = insert;
        }
        return a;
    }

    public static int[] order_4(int[] a, int s, int e){
        //快速排序【不稳定
        if (s >= e) return a;
        int start = s, end = e;
        int length = a.length;
        int key = a[start];
        while(start <end){
            while(start < end && a[end] >= key){
                end--;
            }
            if (start < end) a[start++] = a[end];

            while(start < end && a[start] <= key){
                start++;
            }
            if (start < end) a[end--] = a[start];
            }
        a[start] = key;
        order_4(a,s, start-1);
        order_4(a,start+1, e);
        return a;
    }

    public static void order_5(){return;}
}
