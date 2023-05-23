package likelion.techit_first;

public abstract class AbstractPerson implements Person{
    private String name;
    private int age;

    public String getName(){
        return this.name;
    }
    public AbstractPerson(String name, int age){
        this.age=age;
        this.name=name;
    }
    @Override
    public void speak(){
        System.out.println(String.format("Hi, my name is %s",this.name));
    }

    @Override
    public String toString() {
        return "AbstractPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
