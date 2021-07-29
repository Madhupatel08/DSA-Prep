#include<iostream>
using namespace std;
class person{
    public:
        int age;
        string name;
};
void swap(person one,person two){
    person temp = one;
    one = two;
    two = temp;
}
int main(int argc,char** argv){
    person p1;
    person p2;

    p1.age = 10;
    p1.name = "A";

    p2.age = 20;
    p2.name = "B";

    cout<<p1.age<" "<p1.name<<endl;
    cout<<p2.age<" "<p2.name<<endl;
    swap(p1,p2);
     cout<<p1.age<" "<p1.name<<endl;
    cout<<p2.age<" "<p2.name<<endl;
}
