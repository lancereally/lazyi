package lancereally.entity;

import java.util.ArrayList;
import java.util.List;

public class Japanese extends Human{
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int sss(Human human){System.out.println(human.getAge());
        return human.getAge();
        }

    static void bbb(){
        int a = 1;
    }class wo{

        }
    public static void main(String[] args) {
        Japanese j = new Japanese();
        j.setAge(15);
        j.sss(j);
        List<? super Human> list = new ArrayList<>();
        list.add(j);
//        list.get()
        Object human = list.get(0);
        String s = "1-2-3-8888-123";
        String[] str = s.split("\\-",-1);
        for (String a:str
             ) {
            System.out.println(a);
        }

    }
}
