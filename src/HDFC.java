public class HDFC implements RBI {
    float balance = 2000.0f, ROI=2;
    int counter = 0;
    Customer customer = new Customer();

    public HDFC() {
        System.out.println("Welcome to HDFC");
    }

    public void depositMoney(float amount) {
        customer.balance += amount;
        System.out.println("Customer Balance in account: " + customer.balance);
    }

    public void withdrawMoney(float amount) {
        counter++;
        //float money=0.0f;
        if (counter > 3) {
            amount += (float) ((0.01f * amount));
        }
        if (customer.balance - amount < this.balance) {
            System.out.println("Minimum customer balance reached: Withdrawal not possible.");
        } else {
            customer.balance -= amount;
        }
        System.out.println("Customer Balance in account: " + customer.balance);
    }

    public void openFD(float amount, int years) {
        amount += (amount * ROI * years / 100);
        customer.balance += amount;
        System.out.println("Customer total profit: " + amount);
        System.out.println("Customer Balance in account: " + customer.balance);
    }

    public void applyLoan(int loanType, float amount, int years) {
        switch(loanType){
            case 1:
                ROI = 4;
                break;
            case 2:
                ROI = 3;
                break;
            case 3:
                ROI = 5;
                break;
            case 4:
                ROI = 6;
                break;
            default:
                ROI = 2;
                break;
        }
        float interestPaid, principalPaid, newBalance;
        float monthlyInterestRate, monthlyPayment;
        int month;
        int numMonths = years * 12;

        // Output monthly payment and total payment
        monthlyInterestRate = ROI / 1200;
        monthlyPayment = (float) (amount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, years * 12)));
        System.out.format("Monthly Payment: %8.2f%n", monthlyPayment);
        System.out.format("Total Payment:   %8.2f%n", monthlyPayment * years * 12);


        for (month = 1; month <= numMonths; month++) {
            // Compute amount paid and new balance for each payment period
            interestPaid = amount * (monthlyInterestRate / 100);
            principalPaid = monthlyPayment - interestPaid;
            newBalance = amount - principalPaid;

            // Update the balance
            amount = newBalance;
        }
        customer.balance += amount;
    }

    public void applyCreditCard() {
        //float ROI = 1;
        if (customer.balance <= this.balance*2){
            System.out.println("Credit-Card can be applied.");
        }else{
            System.out.println("Credit-Card cannot be applied: Not Enough customer balance.");
        }
    }

    public void getBalance() {
        System.out.println("Customer Balance in account: " +customer.balance);
    }
}
