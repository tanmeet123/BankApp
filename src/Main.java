import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Thread { // m is main obj so m.start and @override run() method and put all code in it from main function

    BufferedReader buff;
    InputStreamReader isr;

    public Main() {
        if (isr == null)
            isr = new InputStreamReader(System.in);
        if (buff == null)
            buff = new BufferedReader(isr);
    }

    Integer selectedBank, selectedOperation;
    float amount;
    int years, loanType;

    public static void main(String[] args) {
        Main obj = new Main();
        obj.start();
    }

    Customer hasAadhar(List<Customer> customers, String aadhar) {

        for (Customer customer : customers) {
            if (customer.customerAadhar.equals(aadhar)) {
                return customer;
            }
        }
        return new Customer();
    }

    RBI insICICI(List<RBI> banks) {
        for (RBI bank : banks) {
            if (bank instanceof ICICI)
                return bank;
        }
        return new ICICI();
    }

    RBI insHDFC(List<RBI> banks) {
        for (RBI bank : banks) {
            if (bank instanceof HDFC)
                return bank;
        }
        return new HDFC();
    }

    @Override
    public void run() {

        Main obj = new Main();
        RBI bank = null;
        String chooseBank;
        String newCustomer = null;
        Map<Integer, List<Customer>> Bank = new HashMap<>();
        Map<String, List<Integer>> customerBank = new HashMap<>();
        List<RBI> bankList = new ArrayList<>();
        do {
            Customer customer = new Customer();

            String currAadhar = "", name = "", email = "", phone = "", address = "";
            System.out.print("enter aadhar: ");
            try {
                currAadhar = obj.buff.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print("enter name: ");
            try {
                name = obj.buff.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print("enter email: ");
            try {
                email = obj.buff.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print("enter phone number: ");
            try {
                phone = obj.buff.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print("enter address: ");
            try {
                address = obj.buff.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
            customer.setCustomerAadhar(currAadhar);
            customer.setCustomerName(name);
            customer.setCustomerEmail(email);
            customer.setCustomerPhone(phone);
            customer.setCustomerAddress(address);
            do {

                System.out.println("Welcome to IBS\nPlease select your bank\n1. ICICI\n2. HDFC");
                try {
                    obj.selectedBank = Integer.parseInt(obj.buff.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Customer Selected " + obj.selectedBank);
                if (Bank.containsKey(obj.selectedBank)) {
                    Bank.get(obj.selectedBank).add(customer);
                } else {
                    ArrayList<Customer> customers = new ArrayList<>();
                    customers.add(customer);
                    Bank.put(obj.selectedBank, customers);
                }
                switch (obj.selectedBank) {
                    case 1:
                        if (bankList.isEmpty()) {
                            bank = new ICICI(customer);
                            bankList.add(bank);
                        } else bank = obj.insICICI(bankList);
                        break;
                    case 2:
                        if (bankList.isEmpty()) {
                            bank = new HDFC(customer);
                            bankList.add(bank);
                        } else {
                            bank = obj.insHDFC(bankList);
                        }
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

                    if (customerBank.containsKey(customer.customerAadhar)) {
                        customerBank.get(customer.customerAadhar).add(obj.selectedBank);
                    } else {
                        ArrayList<Integer> newBankList = new ArrayList<>();
                        newBankList.add(obj.selectedBank);
                        customerBank.put(customer.customerAadhar, newBankList);
                    }

                    System.out.println("Enter 'N' to choose operation again:");
                    try {
                        chooseOperation = obj.buff.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } while (chooseOperation.equals("N"));

                System.out.println("Enter 'N' to choose Bank again:");
                try {
                    chooseBank = obj.buff.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } while (chooseBank.equals("N"));

            System.out.println("Enter choice:\n\t'y' to continue and check status\n\t'n' to exit:");
            try {
                newCustomer = obj.buff.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for (String aadhar: customerBank.keySet()) {
                String key = aadhar;
                String value = customerBank.get(aadhar).toString();
                System.out.println(key + " " + value);
            }
            System.out.println("Enter the bank code to check no. of customers");
            int bankNoOfCustomer = 0;
            try {
                bankNoOfCustomer = Integer.parseInt(obj.buff.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("No. of customers associated are " + Bank.get(bankNoOfCustomer).size());

        } while (!newCustomer.equals("y"));
    }
}