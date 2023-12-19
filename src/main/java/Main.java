import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main extends Thread { // m is java.main obj so m.start and @override run() method and put all code in it from java.main function

    BufferedReader buff;
    InputStreamReader isr;
    LogManager logManager = LogManager.getLogManager();
    Logger logger = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
            logger.log(Level.INFO,"enter aadhar: ");
            try {
                currAadhar = obj.buff.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.log(Level.INFO,"enter name: ");
            try {
                name = obj.buff.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.log(Level.INFO,"enter email: ");
            try {
                email = obj.buff.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.log(Level.INFO,"enter phone number: ");
            try {
                phone = obj.buff.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.log(Level.INFO,"enter address: ");
            try {
                address = obj.buff.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

            customer.setCustomerAadhar(currAadhar);
            customer.setCustomerName(name);
            customer.setCustomerEmail(email);
            customer.setCustomerPhone(phone);
            customer.setCustomerAddress(address);
            do {

                logger.log(Level.INFO,"Welcome to IBS\nPlease select your bank\n1. master.ICICI\n2. master.HDFC");
                try {
                    obj.selectedBank = Integer.parseInt(obj.buff.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                logger.log(Level.INFO,"master.Customer Selected " + obj.selectedBank);
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
                        logger.log(Level.WARNING,"Invalid Choice");
                        break;
                }

                String chooseOperation;
                do {
                    logger.log(Level.INFO,"Select your choice\n1. Deposit\n2. Withdrawal\n3. OpenFD\n4. Apply Loan\n5. Apply CC\n6. Check Balance");
                    try {
                        obj.selectedOperation = Integer.parseInt(obj.buff.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    logger.log(Level.INFO,"master.Customer Selected " + obj.selectedOperation);
                    switch (obj.selectedOperation) {
                        case 1:
                            logger.log(Level.INFO,"Enter the amount: ");
                            try {
                                obj.amount = Float.parseFloat(obj.buff.readLine());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            bank.depositMoney(obj.amount);
                            break;
                        case 2:
                            logger.log(Level.INFO,"Enter the amount: ");
                            try {
                                obj.amount = Float.parseFloat(obj.buff.readLine());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            bank.withdrawMoney(obj.amount);
                            break;
                        case 3:
                            logger.log(Level.INFO,"Enter the amount: ");
                            try {
                                obj.amount = Float.parseFloat(obj.buff.readLine());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            logger.log(Level.INFO,"Enter duration in years: ");
                            try {
                                obj.years = Integer.parseInt(obj.buff.readLine());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            bank.openFD(obj.amount, obj.years);
                            break;
                        case 4:
                            logger.log(Level.INFO,"Enter the amount: ");
                            try {
                                obj.amount = Float.parseFloat(obj.buff.readLine());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            logger.log(Level.INFO,"Enter duration in years: ");
                            try {
                                obj.years = Integer.parseInt(obj.buff.readLine());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            logger.log(Level.INFO,"Select type of Loan\n1. Home\n2. Education\n3. Personal\n4. Car ");
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
                            logger.log(Level.WARNING,"Invalid Choice");
                            break;
                    }

                    if (customerBank.containsKey(customer.customerAadhar)) {
                        customerBank.get(customer.customerAadhar).add(obj.selectedBank);
                    } else {
                        ArrayList<Integer> newBankList = new ArrayList<>();
                        newBankList.add(obj.selectedBank);
                        customerBank.put(customer.customerAadhar, newBankList);
                    }

                    logger.log(Level.INFO,"Enter 'N' to choose operation again:");
                    try {
                        chooseOperation = obj.buff.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } while (chooseOperation.equals("N"));

                logger.log(Level.INFO,"Enter 'N' to choose Bank again:");
                try {
                    chooseBank = obj.buff.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } while (chooseBank.equals("N"));

            logger.log(Level.INFO,"Enter choice:\n\t'y' to continue and check status\n\t'n' to exit:");
            try {
                newCustomer = obj.buff.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logger.log(Level.INFO,"List attached to list of banks:- ");
            for (String aadhar: customerBank.keySet()) {
                String key = aadhar;
                String value = customerBank.get(aadhar).toString();
                logger.log(Level.INFO,"Aadhar: " + key + " - Banks: " + value);
            }
            logger.log(Level.INFO,"Enter the bank code to check no. of customers");
            int bankNoOfCustomer = 0;
            try {
                bankNoOfCustomer = Integer.parseInt(obj.buff.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            logger.log(Level.INFO,"No. of customers associated are " + Bank.get(bankNoOfCustomer).size());

        } while (!newCustomer.equals("y"));
    }
}