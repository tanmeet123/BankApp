import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ICICI implements RBI {
    LogManager logManager = LogManager.getLogManager();
    Logger logger = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);
    float balance = 1000.0f, ROI = 2, customerBalance;
    int counter = 0;
    Customer customer;
    Map<String, Float> aadharToBalance = new HashMap<>();

    public ICICI() {
        logger.log(Level.INFO,"Welcome to master.ICICI");
        customer = new Customer();
        this.customerBalance = 0f;
        aadharToBalance.put(customer.customerAadhar, this.customerBalance);
    }

    public ICICI(Customer customer) {
        logger.log(Level.INFO,"Welcome to master.ICICI");
        if (aadharToBalance.containsKey(customer.customerAadhar))
            this.customerBalance = aadharToBalance.get(customer.customerAadhar);
        else {
            this.customerBalance = 0f;
            aadharToBalance.put(customer.customerAadhar, this.customerBalance);
        }

        this.customer = customer;
    }

    public void depositMoney(float amount) {
        this.customerBalance += amount;
        //customer.balance = this.customerBalance;
        logger.log(Level.INFO,"master.Customer Balance in account: " + this.customerBalance);
        aadharToBalance.put(customer.customerAadhar, this.customerBalance);
    }

    public void withdrawMoney(float amount) {
        counter++;

        if (counter > 3) {
            amount += (0.01f * amount);
        }
        if (this.customerBalance - amount < this.balance) {
            logger.log(Level.INFO,"Minimum customer balance reached: Withdrawal not possible.");
        } else {
            customer.balance -= amount;
        }
        logger.log(Level.INFO,"master.Customer Balance in account: " + this.customerBalance);
        //customer.balance = this.customerBalance;
        aadharToBalance.put(customer.customerAadhar, this.customerBalance);
    }

    public void openFD(float amount, int years) {
        amount += (amount * ROI * years / 100);
        this.customerBalance += amount;
        logger.log(Level.INFO,"master.Customer total profit: " + amount);
        logger.log(Level.INFO,"master.Customer Balance in account: " + this.customerBalance);
        //customer.balance = this.customerBalance;
        aadharToBalance.put(customer.customerAadhar, this.customerBalance);
    }

    public void applyLoan(int loanType, float amount, int years) {

        switch (loanType) {
            case 1:
                ROI = 3;
                break;
            case 2:
                ROI = 1;
                break;
            case 3:
                ROI = 5;
                break;
            case 4:
                ROI = 7;
                break;
            default:
                ROI = 2;
                break;
        }

        float interestPaid, principalPaid, newBalance;
        float monthlyInterestRate, monthlyPayment;
        int month;
        int numMonths = years * 12;

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
        this.customerBalance += amount;
        //customer.balance = this.customerBalance;
        aadharToBalance.put(customer.customerAadhar, this.customerBalance);
    }

    public void applyCreditCard() {
        //float ROI = 1;
        if (this.customerBalance <= this.balance * 2) {
            logger.log(Level.INFO,"Credit-Card can be applied.");
        } else {
            logger.log(Level.INFO,"Credit-Card cannot be applied: Not Enough customer balance.");
        }
        aadharToBalance.put(customer.customerAadhar, this.customerBalance);
    }

    public void getBalance() {
        logger.log(Level.INFO,"master.Customer Balance in account: " + aadharToBalance.get(customer.customerAadhar));
    }
}
