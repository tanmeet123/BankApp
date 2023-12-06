public class RBI {
    float balance = 1000.0f;
    int counter=0;
    Customer customer = new Customer();
    public void depositMoney(float amount) {
        customer.balance += amount;
        System.out.println("Customer Balance in account: " + customer.balance);
    }
    public void withdrawMoney(float amount){
        counter++;
        //float money=0.0f;
        if (counter>3){
            amount+= (float) ((0.01*amount));
        }
        if (customer.balance - amount < this.balance){
            System.out.println("Minimum customer balance reached: Withdrawal not possible.");
        }else {
            customer.balance -= amount;
        }
        System.out.println("Customer Balance in account: " + customer.balance);
    }
    public void openFD(float amount, float ROI, int years) {}
    public void applyLoan(String loanType, float amount, float ROI, int years) {}
    public void applyCreditCard() {}
    public float getBalance() {
        return customer.balance;
    }
}