package lancereally;

public class MyPancake {
    public static void main(String[] args) {
        Pancake pancake = new Pancake();
        pancake.setEggs(2);
        pancake.setHam(1);
        pancake.pay();
    }
}

class Pancake{
    private Ineed ineed;
    private int money = 0;

    public void setEggs(int i){
        ineed = new Eggs();
        ineed.set(i);
        money+=ineed.pay();
    }

    public void setHam(int i){
        ineed = new Ham();
        ineed.set(i);
        money+=ineed.pay();
    }

    public void pay(){
        System.err.printf("总共需要花费%d$", money);
    }
}

interface Ineed{
    void set(int i);
    int pay();
}

class Eggs implements Ineed{
    private final int money = 1;
    private int num = 0;
    @Override
    public void set(int i) {
        System.out.printf("加%d个蛋\n", i);
        num = i;
    }
    @Override
    public int pay(){
        return num*money;
    }
}

class Ham implements Ineed{
    private final int money = 5;
    private int num = 0;
    @Override
    public void set(int i) {
        System.out.printf("加%d片火腿\n", i);
        num = i;
    }
    @Override
    public int pay(){
        return num*money;
    }
}