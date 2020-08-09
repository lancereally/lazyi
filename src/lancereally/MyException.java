package lancereally;

/**
 * @author lancerally
 * @Version 2.3
 * */
public class MyException extends Exception {
    //传参用
    public MyException(String msg){
        super(msg+"-error_lancereally");
    }

    //无参重写toString
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return"长度不可";
    }
}
