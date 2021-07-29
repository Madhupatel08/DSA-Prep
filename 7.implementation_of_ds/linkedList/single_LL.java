import java.io.*;
import java.util.*;
class linked_list {
    public static class Node {
        int data;
        Node next;
    }
    public static class LinkedList {
        Node head;
        Node tail;
        int size;
        void addLast(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next = null;
            if (size == 0) {
                head = tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }

            size++;
        }
        public int size(){
            return size;
        }
        public void display(){
            for(Node temp = head; temp != null; temp = temp.next){
                System.out.print(temp.data + " ");
            }
            System.out.println();
        }
        public void removeFirst(){
            if(head==null){
                System.out.println("List is empty");
                return;
            }else if(head==tail){
                head=null;tail=null;
            }else{
                Node temp=head;
                head=head.next;
                temp.next=null;
            }
            size--;
        }
        public void addFirst(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next = head;
            head = temp;
            if(size == 0){
                tail = temp;
            }
            size++;
        }
        public void removeLast(){
            if(head==null){
                System.out.println("List is empty");
                return;
            }else if(head==null){
                tail=head=null;
            }else{
                Node temp=head;
                while(temp.next.next!=null){
                    temp=temp.next; 
                }
            temp.next=null;
            tail=temp;
            }
            size--;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner scn=new Scanner(System.in);
        LinkedList list = new LinkedList();
        //adding elements in the list//
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
        list.addLast(60);
        System.out.println("The list is:");
        list.display();
        int size=list.size();
        System.out.println("The size of the list is:"+ size);
        //remove first//
        System.out.println();
        System.out.println("After removing first Element list is:");
        list.removeFirst();
        list.display();
        size=list.size();
        //remove last//
        System.out.println();
        System.out.println("After removing last Element list is:");
        list.removeLast();
        size=list.size();
        list.display();
        System.out.println();
        //add first//
        list.addFirst(4);
        System.out.println("After adding in first place list is:");
        list.display();
        size=list.size();
        System.out.println("The size of the list is:"+size);
    }
}