#include<iostream>


using namespace std;
class atm{
    protected:
    int pin =1234,pin1;
    float bal=10000,with_amt,depo_amt;
    public:
    void withdrawl(){
        cout<<"enter the amount you want to withdraw"<<endl;
        cin>>with_amt;
        cout<<"you have successfully withdrawn "<<with_amt<<"from your accont"<<endl;
        cout<<"current balance is "<<bal - with_amt<<endl;q

    }
    void deposit(){
        cout<<"enter the amount you want to deposit "<<endl;
        cin>>depo_amt;
        cout<<"you have successfully deposited "<<depo_amt<<"to your account "<<endl;
        cout<<"current balance is "<<bal + depo_amt<<endl;
        }
        void bal_enquiry(){
            cout<<"your current balance is "<<bal<<endl;
        }
};
class atm1:public atm{
    public:
    void depo(){
        for(int i=1;i<=4;i++){
            cout<<"enter the pin"<<endl;
            cin>>pin1;
            if(pin1==pin){
                deposit();
                break;
            }
        }
    }
    void with(){
        for(int i=1;i<=4;i++){
            cout<<"enter the pin"<<endl;
            cin>>pin1;
            if(pin1==pin){
                withdrawl();
                break;
            }
        }
    }
    void check(){
        for(int i=1;i<4;i++){
            cout<<"enter the pin"<<endl;
            cin>>pin1;
            if(pin1==pin){
                bal_enquiry();
                break;
            }
        }
    }
};
int main(){
   int x;
  
   do{
     int n;
    atm1 a;
    cout<<"enter 1 for balance enquiry 2 for depositing cash 3 for withdrawl"<<endl;
    cin>>n;
    switch(n){
        case 1: a.check();
        break;
        case 2: a.depo();
        break;
        case 3: a.with();
        break;
        default: cout<<"invalid output"<<endl;
    }
    system("cls");
    cout<<"enter any positive number to return to main menu"<<endl;
    cin>>x;
     system("cls");
   }while(x!=0);

}