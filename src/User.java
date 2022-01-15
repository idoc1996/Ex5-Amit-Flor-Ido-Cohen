import java.util.Date;

public abstract class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private ShoppingCart shoppingCart;
    private double totalShoppingAmount;
    private int shoppingTimes;

    public Date getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    private Date lastPurchaseDate;

    public int getShoppingTimes() {
        return shoppingTimes;
    }


    public void setLastPurchaseDate(Date lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        shoppingCart = new ShoppingCart();
        shoppingTimes = 0;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public double getTotalShoppingAmount() {
        return totalShoppingAmount;
    }

    public void setTotalShoppingAmount(double totalShoppingAmount) {
        this.totalShoppingAmount = totalShoppingAmount;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void incrementShoppingTimes() {
        shoppingTimes++;
    }
}