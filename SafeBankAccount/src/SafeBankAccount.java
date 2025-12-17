public class SafeBankAccount {

    private static final int fine = 200;
    private static final int balance1 = 1000000;
    private static final int balance2 = 200000;
    private static final double rate_category1 = 0.01;
    private static final double rate_category2 = 0.02;
    private static final double rate_category3 = 0.03;


    private int balance;

    //constructor
    public SafeBankAccount() {
        balance = 0;
    }

    //constructor + initial balance
    public SafeBankAccount(int amount) {
        balance = amount;
    }

    public int computeInterest() {
        //computes yearly interest based on balace amount

        double interestRate;

        if (balance > balance1) {
            interestRate = rate_category3;
        } else if (balance > balance2) {
            interestRate = rate_category2;
        } else {
            interestRate = rate_category1;
        }

        return Math.toIntExact(Math.round(interestRate * balance));
    }

    public void applyInterest() {
        //adds yearly interest to balance

        balance = balance + this.computeInterest();
    }


    // deposits amount units into the bank account
    public void deposit(int amount)  {
        //precondition amount >= 0
        balance = balance + amount;
    }

    public void withdraw(int amount) {

        if (amount > balance){
            balance = balance - fine;
        }
        else {
            balance = balance - amount ;
        }
    }

    public int getBalance()  {
        return balance;
    }

    public int getFine(){
        return fine;
    }

}
