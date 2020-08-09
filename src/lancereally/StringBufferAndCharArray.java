package lancereally;

public class StringBufferAndCharArray {
    static StringBuffer str = new StringBuffer("We Are Happy");

    public static void main(String[] args) {
        replaceSpace(str);
    }

    public static String replaceSpace(StringBuffer str) {
        /**遇到空格追加，使用stringbuilder，底层的实现是会执行多次resize操作。
         * 因此在数据量大的情况下，性能会降低下来*/
        char[] originChars = str.toString().toCharArray();
        char[] specialChars = ("%20").toCharArray();
        //设置计算游标
        int isr = 0;
        //执行一次N的遍历
        for (int i = 0; i < originChars.length; i++) {
            if (originChars[i] == ' ') {
                isr++;
            }
        }
        //最后的新数组的大小
        int newCapacity = originChars.length + (isr * (specialChars.length-1));//减去空格
        char[] newChars = new char[newCapacity];
        for (int i = 0, j = 0; i < newCapacity && j < originChars.length; i++) {
            if (originChars[j] == ' ') {
                for (int t = 0; t < specialChars.length; t++) {
                    newChars[i++] = specialChars[t];
                }
                j ++;
                i --;
                continue;
            }
            //正常迁移
            newChars[i] = originChars[j++];
        }
        int[] a = new int[]{1,2,3};
        System.out.println();
        return new String(newChars);
    }
}
    /**纯StringBuffer版本*/

    /**public String replaceSpace(StringBuffer str) {
     StringBuilder sb = new StringBuilder();
     for(int i=0;i<str.length();i++){
     char c = str.charAt(i);
     if(c == ' '){
     sb.append("%20");
     }else{
     sb.append(c);
     }
     }
     return sb.toString();
     }*/

