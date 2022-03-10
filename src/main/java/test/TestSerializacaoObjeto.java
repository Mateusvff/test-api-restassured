package test;

public class TestSerializacaoObjeto {
    private String name;
    private int age;
    private Double salary;

    public TestSerializacaoObjeto(String name, int age, double salary){
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge(){
        return age;
    }

}
