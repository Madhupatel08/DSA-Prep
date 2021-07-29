class stack_using_linkedList{
    class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public class stackLL{
        private Node head;
        public stackLL(){
            this.head = null;
        }
        public void push(int data){
            Node newNode=new Node(data);
            newNode.next = this.head;
            this.head = newNode;
        }
        public void display(){
            if(this.head==null){
                System.out.println("Stack is empty");
            }
            Node temp=this.head;
            while(temp!=null){
                System.out.print(temp.data+" ");
                temp=temp.next;
            }
        }
        public int peek(){
            if(this.head == null){
                System.out.println("Stack is empty");
            }
            return this.head.data;
        }
        public int pop(){
            if(this.head == null){
                System.out.println("Stack is empty");
            }
            Node temp = head;
            this.head = this.head.next;
            return temp.data;
        }
        public int size(){
            if(this.head == null){
                return 0;
            }
            Node temp = this.head;
            int count = 0;
            while(temp != null){
                count++;
                temp = temp.next;
            }
            return count;
        }
    }
    public static void main(){
        stack_using_linkedList.stackLL st = new stack_using_linkedList.stackLL();
        st.display();
        st.push(1);
        st.push(2);
        st.display();
        System.out.println("top element is "+ st.peek());
        st.push(5);
        System.out.println("popped element is "+st.pop());
        System.out.println("size of the stack is "+st.size());
    }
}