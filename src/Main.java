import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    BufferedReader buff;
    InputStreamReader isr;
    public Main() {
        if(isr == null)
            isr = new InputStreamReader(System.in);
        if(buff==null)
            buff = new BufferedReader(isr);
    }

    int selectedBank, selectedOperation;
    float amount;
    public static void main(String[] args) {
        Main obj = new Main();
        RBI rbi = new RBI();

        System.out.println("Welcome to IBS\nPlease select your bank\n1. ICICI\n2. HDFC\n3. SBI\n4. AXIS\n5. IDFC");
        try {
            obj.selectedBank = Integer.parseInt(obj.buff.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Customer Selected " + obj.selectedBank);

        System.out.println("Select your choice\n1. Deposit\n2. Withdrawal\n3. OpenFD\n4. Apply Loan\n5. Apply CC");
        try {
            obj.selectedOperation = Integer.parseInt(obj.buff.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Customer Selected " + obj.selectedOperation);
        String yes;
        do{
            System.out.println("Select your choice\n1. Deposit\n2. Withdrawal\n3. OpenFD\n4. Apply Loan\n5. Apply CC");
            try {
                obj.selectedOperation = Integer.parseInt(obj.buff.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Customer Selected " + obj.selectedOperation);
        switch (obj.selectedOperation){
            case 1:
                System.out.print("Enter the amount: ");
                try {
                    // bnk acc details

                    obj.amount = Float.parseFloat(obj.buff.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                rbi.depositMoney(obj.amount);
                break;

            case 2:
                System.out.print("Enter the amount: ");
                try {
                    obj.amount = Float.parseFloat(obj.buff.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                rbi.withdrawMoney(obj.amount);
                break;
            default:
                System.out.println("Invalid Choice");
        }
            try {
                yes = obj.buff.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } while (yes.equals("y"));
    }
}