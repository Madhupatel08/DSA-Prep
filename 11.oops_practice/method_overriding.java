class A{
        public void m1(){
            System.out.println("method 1 of class A");
        }
    }
    class B extends A{
        public void m1(){
            // System.out.println("method 1 of class B");
        }
    }

class method_overriding{
    public static void main(String[] args){
        A a = new A();
        a.m1();
        B b = new B();
        b.m1();
    }
}