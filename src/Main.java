import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    BufferedReader buff;
    InputStreamReader isr;

    public Main() {
        if (isr == null)
            isr = new InputStreamReader(System.in);
        if (buff == null)
            buff = new BufferedReader(isr);
    }

    int selectedBank, selectedOperation;
    float amount;
    int years, loanType;

    public static void main(String[] args) {
        Main obj = new Main();
        RBI bank = null;
        String chooseBank;
        do {

            System.out.println("Welcome to IBS\nPlease select your bank\n1. ICICI\n2. HDFC\n3. SBI\n4. AXIS");
            try {
                obj.selectedBank = Integer.parseInt(obj.buff.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Customer Selected " + obj.selectedBank);
            switch(obj.selectedBank){
                case 1:
                    bank = new ICICI();
                    break;
                case 2:
                    bank = new HDFC();
                    break;
                case 3:
                    bank = new SBI();
                    break;
                case 4:
                    bank = new AXIS();
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            String chooseOperation;
            do {
                System.out.println("Select your choice\n1. Deposit\n2. Withdrawal\n3. OpenFD\n4. Apply Loan\n5. Apply CC\n6. Check Balance");
                try {
                    obj.selectedOperation = Integer.parseInt(obj.buff.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Customer Selected " + obj.selectedOperation);
                switch (obj.selectedOperation) {
                    case 1:
                        System.out.print("Enter the amount: ");
                        try {
                            // bnk acc details
                            obj.amount = Float.parseFloat(obj.buff.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bank.depositMoney(obj.amount);
                        break;
                    case 2:
                        System.out.print("Enter the amount: ");
                        try {
                            obj.amount = Float.parseFloat(obj.buff.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bank.withdrawMoney(obj.amount);
                        break;
                    case 3:
                        System.out.print("Enter the amount: ");
                        try {
                            obj.amount = Float.parseFloat(obj.buff.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.print("Enter duration in years: ");
                        try {
                            obj.years = Integer.parseInt(obj.buff.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bank.openFD(obj.amount, obj.years);
                        break;
                    case 4:
                        System.out.print("Enter the amount: ");
                        try {
                            obj.amount = Float.parseFloat(obj.buff.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.print("Enter duration in years: ");
                        try {
                            obj.years = Integer.parseInt(obj.buff.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Select type of Loan\n1. Home\n2. Education\n3. Personal\n4. Car ");
                        try {
                            obj.loanType = Integer.parseInt(obj.buff.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bank.applyLoan(obj.loanType, obj.amount, obj.years);
                        break;
                    case 5:
                        bank.applyCreditCard();
                        break;
                    case 6:
                        bank.getBalance();
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
                System.out.println("Enter 'y' to choose operation again:");
                try {
                    chooseOperation = obj.buff.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } while (chooseOperation.equals("y"));
            System.out.println("Enter 'y' to choose Bank again:");
            try {
                chooseBank = obj.buff.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (chooseBank.equals("y"));
    }
}