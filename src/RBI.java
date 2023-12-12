
public interface RBI {
    float balance = 1000.0f, ROI = 1;
    int counter = 0;

    public void depositMoney(float amount);

    public void withdrawMoney(float amount);

    public void openFD(float amount, int years);

    public void applyLoan(int loanType, float amount, int years);

    public void applyCreditCard();

    public void getBalance();
}