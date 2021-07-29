class stack_using_Array{
    //instance variables
    int top;
    int capacity;
    int size;//size of stack array
    int[] stack;
    //custructor and class must have same name
    stack_using_Array(){
        top = -1;//when top =-1 array is empty
        capacity = 10;
        stack = new int[capacity];
    }
    //method 1
    public boolean isEmpty(){
        if(top==-1){
            return true;
        }else{
            return false;
        }
    }
    //method 2
    public boolean isFull(){
        if(top==size){
            return true;
        }else{
            return false;
        }
    }
    //method 3
    public void push(int data){
        if(!isFull()){
            top=top+1;//++top
            stack[top]=data;
            size++;
        }else{
            System.out.println("could not insert any element as Stack is full");
        }
    }
    //method 4
    public int pop(){
        if(isEmpty()){
            System.out.println("Stack is empty");
            return -1;
        }else{
            int temp=stack[top];
            top--;size--;
            return temp; 
        }
    }
    //method 5
    public int peek(){
        return stack[top];
    }
    //method 6
    public void display(){
        for(int i=0;i<size;i++){
            System.out.print(stack[i]+" ");
        }
        System.out.println();
    }
    //method 7
    public static void main(String[] args){
        
        stack_using_Array st=new stack_using_Array();//object of class by using new operator
        st.push(43);
        st.push(33);
        st.push(3);
        st.push(56);
        st.display();
        System.out.println(st.isEmpty());
        System.out.println(st.isFull());
        System.out.println("size of stack "+ st.size);
        System.out.println("peek element is "+ st.peek());
        System.out.println("popped elemnt is "+ st.pop());
        st.display();
        System.out.println("size of stack "+ st.size);
        System.out.println("peek element is "+ st.peek());
    }
}