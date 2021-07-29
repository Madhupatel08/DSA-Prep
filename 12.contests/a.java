import java.util.*;
public class a{
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();

        while(t-->0){
            
        }

    }
    //mention all ur methods (in keyword form)in the doc/editor
    // edge cases khud se bata do//and jab run kro to test case editor me hi likh do
    //agar purane method galt tha phir bhi use delete mat kro kyuki wo show karegea tumne itane time ke kya kia
    //Time & space complexity khud se hi likh do pehle hi bina puchhe
    //ask clarifying questions such as ---> is tree a binary tree or complete tree//or input kis formme dia 
    //hai array form me ya linkedlist ya graph form me //
    //don't be solent for a long time ..
    //apni bhasha me khud hi explain na karne ke purpose se khud hi logic samjh and jab confirm ho jae then 
    //explain logic to the interviewerthen dry run with a case and then write code if a asks and then again dry run

    //first que
    // count no of leaf nodes --> in full tree (n+1)/2
    //second que
    // head pointer is not given and u have to delete a refernce node
    // is it singly or doubly LL 
    // practice in above manner
    public static void point(TreenNode pointer){
        //method 1->brute O(n^2)
        //method 2->
        if(pointer == null){
            return;
        }
        // if(pointer.next == null){
        //     pointer = null;
        // }
        //1->2
        TreeNode prevvNode = pointer.prev;//1
        if(prev != null)
            pointer.prev.next = pointer.next;//1->3
        pointer.next.prev = prevvNode;//1<-3
        pointer.next = null;
        pointer.prev = null;
    }
}