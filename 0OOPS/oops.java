import java.util.*;
class oops{
    public static swap1(person p1,person p2){
        person temp = p1;
        p1 = p2;
        p2 = temp;//not swapped
    }
    public static swap2(person p1,person p2){
        int tage = p1.age;
        p1.age = p2.age;
        p2.age = tage;  //both values swapped

        String tname = p1.name;
        p1.name = p2.name;
        p2.name = tname;
    }
    public static swap2(person p1,person p2){
        int tage = p1.age;
        p1.age = p2.age;
        p2.age = tage;

        String tname = p1.name;
        p1.name = p2.name;
        p2.name = tname;
    }
    public static swap3(person p1,person p2){
        p1 = new person();
        int tage = p1.age;
        p1.age = p2.age;
        p2.age = tage;

        p2 = new person();
        String tname = p1.name;
        p1.name = p2.name;
        p2.name = tname;
    }
    public static swap4(person p1,person p2){
    
        int tage = p1.age;
        p1.age = p2.age;
        p2.age = tage;

        p2 = new person();
        String tname = p1.name;
        p1.name = p2.name;
        p2.name = tname;
    }
    public static swap5(person p1,person p2){
    
        int tage = p1.age;
        p1.age = p2.age;
        p2.age = tage;

        p1 = new person();
        p2 = new person();
        String tname = p1.name;
        p1.name = p2.name;
        p2.name = tname;
    }
    public static void main(String[] args){
        person p1;
        person p2;
        p1 = new person();
        p1.age = 10;p1.name = "A";

        p2 = new person();
        p2.age = 20; p2.name = "B";

        swap(p1,p2);
    }
}