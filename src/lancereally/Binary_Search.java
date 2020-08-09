package lancereally;

public class Binary_Search {
    static int key = -1;
    public static void main(String[] args) {
        int[] x = {1,2,3,6,6,6,6,6,6,7,8,9};
        Binary(x,6);
    }

    public static void Binary(int[] x,int target){
        if (x.length<1) return;
        int low = 0;
        int high = x.length-1;
        ToSearch_1(x, low, high, target);
        System.out.println(key);
    }

    //二分查找-基本型
    public static void ToSearch_0(int[] x, int low, int high, int target) {
        if (low >= high) {
            System.out.println("无");
            return;
        }
        //数组过大的情况下，可使用位运算求mid
        int mid = ((high-low)/2)+low;
        if (x[mid] < target)
            ToSearch_0(x, mid+1, high, target);
        else if (x[mid] > target)
            ToSearch_0(x, low, mid-1, target);
        else {
            System.out.println("下标mid:"+mid);
            key = x[mid];
        }
    }

    //二分查找-重复数据-求第一个符合要求的数据下标
    public static void ToSearch_1(int[] x, int low, int high, int target) {
        if (low >= high) {
            System.out.println("无");
            return;
        }
        //数组过大的情况下，可使用位运算求mid
        int mid = ((high-low)/2)+low;
        if (x[mid] < target)
            ToSearch_1(x, mid+1, high, target);
        else if (x[mid] > target)
            ToSearch_1(x, low, mid-1, target);
        else {
            while (x[mid] == target){
                mid--;
            }
            mid++;
            System.out.println("下标mid:"+mid);
            key = x[mid];
        }
    }

    //二分查找-求小于target的最后一个数据下标
}
