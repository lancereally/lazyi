package lancereally.entity;

public class Human {
    private int age;
    private String name;
    private Japanese japanese;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Japanese getJapanese() {
        return japanese;
    }

    public void setJapanese(Japanese japanese) {
        this.japanese = japanese;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
