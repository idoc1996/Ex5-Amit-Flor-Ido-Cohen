public class Client extends User {

    private boolean isVip;

    public Client(String firstName, String lastName, String userName, String password, boolean areVip) {
        super(firstName, lastName, userName, password);
        this.isVip = areVip;
    }


    @Override
    public String toString() {
        return super.toString() +
                (isVip ? "(VIP)" : "");
    }

    public String fullPrint() {
        return getFirstName() + " " + getLastName() + (isVip ? "(VIP)" : "")
                + "\nThe times he shopped in the store is " + getShoppingTimes() + " times"
                + "\nthe total money he spent is " + getTotalShoppingAmount() + "$"
                + "\nhis last purchase was " + (getLastPurchaseDate() != null ? "on " + getLastPurchaseDate() : "never")
                + "\n";
    }

    public boolean isVip() {
        return isVip;
    }
}