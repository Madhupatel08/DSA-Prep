#include<iostream>
using namespace std;
class instruction{
    public:

    int opcode;
    int *dest;
    int *src1;
    int *src2;
    instruction(){}
    instruction(char &ch, int &d, int &s1, int &s2){
        if(ch=='+'){
            opcode = 1;
        }
        if(ch=='-'){
            opcode = 2;
        }
        if(ch == '*'){
            opcode = 3;
        }
        if(ch == '/'){
            opcode = 4;
        }
        dest = &d;
        src1 = &s1;
        src2 = &s2;
    }
    instruction(string s){
    }
    void print(){
        cout<<"\n--------------------------INSTRUCTION--------------------------------";
         cout<<"\nOPCODE | Destination Address | Source Address1 | Source Address2\n ";
        cout<<" "<<opcode<<" | "<<dest<<" | "<<src1<<" | "<<src2;
        cout<<"\n---------------------------------------------------------------------\n"; 
    }
};#include<iostream>
#include<cmath>
using namespace std;
// Function to find (r-1)'s complement
int prevComplement(int n, int b)
{
int maxDigit, maxNum = 0, digits = 0, num = n;
// Calculate number of digits
// in the given number
while(n!=0)
{
digits++;
n = n/10;
}
// Largest digit in the number
// system with base r
maxDigit = b-1;
// Largest number in the number
// system with base r
while(digits--)
{
maxNum = maxNum*10 + maxDigit;
}
// return Complement
return maxNum - num;
}
// Function to find r's complement
int complement(int n, int b)
{
// r's complement = (r-1)'s complement + 1
return prevComplement(n,b) + 1;
}
int main()
{
cout << prevComplement(25, 7)<<endl;
cout << complement(25, 7);
return 0;
}
int accumulator=0;
int R1, R2, R3, R4, R5, R6, R7;
int add(int R1,int R2){
    accumulator=0;
    accumulator = R1;
    int R3 = R1;
    accumulator=R2;
    accumulator = accumulator + R1;
    int R4 = accumulator;
    return R4;
}
int subtract(int R1,int R2){
    accumulator = 0;
    accumulator = R1;

    R3 = R1;
    accumulator = R2;
    accumulator = R1 - accumulator;
    R4 = accumulator;
    return R4;
}
int multiply(int R1,int R2){
    accumulator = 0;
    accumulator = R1;
    R3 = R1;
    accumulator = R2;
    accumulator = R1 * accumulator;
    R4 = accumulator;
    return R4;
}
int divide(int R1,int R2){
    if(R2==0)
    cout<<"error";
    accumulator = 0;
    accumulator = R1;
    R3 = R1;
    accumulator = R2;
    accumulator = R1 / accumulator;
    R4 = accumulator;
    return R4;
}
int bitwiseOR(int R1, int R2) {
    accumulator = 0;
    accumulator = R1;
    R3 = R1;
    accumulator = R2;
    accumulator = R1 | accumulator;
    R4 = accumulator;
    return R4;
}
int bitwiseAND(int R1, int R2) {
    accumulator = 0;
    accumulator = R1;
    R3 = R1;
    accumulator = R2;
    accumulator = R1 & accumulator;
     R4 = accumulator;
    return R4;
}
int bitwiseNOT(int R1)
{
    accumulator = 0;
    accumulator = R1;
    accumulator = ~accumulator;
    R2 = accumulator;
    return R2;
}
int LeftShift(int R1, int R2){
    accumulator = 0;
    accumulator = R1;
    accumulator = accumulator<<R2;
    R3 = accumulator;
    return R3;
}
int RightShift(int R1, int R2){
    accumulator = 0;
    accumulator = R1;
    accumulator = accumulator>>R2;
    R3 = accumulator;
    return R3;
}
void input(){
    int num1, num2;
    cout<<"Enter first number: ";
    cin>>num1;
    cout<<endl;
    cout<<"Enter second number: ";
    cin>>num2;
    cout<<endl;
    R1 = num1, R2 = num2;
}
int main(int args,char** argv){
    int choice;
    char cont='\0';
    do{
    cout<<".........................ARITHMETIC OPERATIONS.......................\n"; cout<<"1. ADD\n";
    cout<<"2. SUB\n";
    cout<<"3. MUL\n";
    cout<<"4. DIV\n";
    cout<<"..........................BITWISE OPERATIONS.........................\n"; cout<<"5. BITWISEOR\n";
    cout<<"6. BITWISEAND\n";
    cout<<"7. BITWISENOT\n";
    cout<<"8. LEFTSHIFT\n";
    cout<<"9. RIGHTSHIFT\n";
    cout<<endl;
    cout<<"Enter choice: ";
    cin>>choice;
    cout<<endl;
    switch (choice) {
        case 1: {

        input();
        R5 = add(R1,R2);
        char ch = '+';
        instruction i1(ch, R5, R1, R2);
        i1.print();
        cout<<"Addtion of two numbers is = " << R5;
        cout<<endl;
        break;
        }
        case 2: {
        input();
        R5 = subtract(R1,R2);
        char ch = '-';
        instruction i1(ch, R5, R1, R2);
        i1.print();
        cout<<"Subtraction of two numbers is = "<< R5 <<endl; break; }
        case 3: {
        input();
        R5 = multiply(R1,R2);
        char ch = '*';
        instruction i1(ch, R5, R1, R2);
        i1.print();
        cout<<"Multiplication of two numbers is = "<< R5<<endl; break; }
        case 4: {
        input();
        R5 = divide(R1,R2);
        char ch = '/';
        instruction i1(ch, R5, R1, R2);
        i1.print();
        cout<<"division of two numbers is = " << R5; break; }
        case 5: {
        input();
        R5 = bitwiseOR(R1,R2);
        cout<<"Biwise OR of two numbers is = " << R5; cout<<endl;
        break;
        }
        case 6: {
        input();
        R5 = bitwiseAND(R1,R2);
        cout<<"Biwise AND of two numbers is = " << R5; cout<<endl;
        break;
        }
        case 7: {
        int num1;
        cout<<"Enter first number: ";
        cin>>num1;
        cout<<endl;
        R1 = num1;

        R5 = bitwiseNOT(R1);
        cout<<"Biwise NOT of number is = " << R5; cout<<endl;
        break;
        }
        case 8: {
        int num1, k;
        cout<<"Enter first number:";
        cin>>num1;
        cout<<endl;
        cout<<"Enter k: ";
        cin>>k;
        cout<<endl;
        R1 = num1, R2 = k;
        R5 = LeftShift(R1,R2);
        cout<<"Left Shift of number is = " << R5; cout<<endl;
        break;
        }
        case 9: {
        int num1, k;
        cout<<"Enter first number: ";
        cin>>num1;
        cout<<endl;
        cout<<"Enter k: ";
        cin>>k;
        cout<<endl;
        R1 = num1, R2 = k;
        R5 = RightShift(R1,R2);
        cout<<"Right Shift of number is = "<< R5; break; }
        default:
        cout<<"wrong Input";
    }
    cout<<endl;
    cout<<"Press Y to continue";
    cin>>cont;
    }
    while(cont=='Y' || cont=='y');
    return 0;
}