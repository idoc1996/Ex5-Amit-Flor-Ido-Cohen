public class UserProduct {
    private Product product;
    private int amount;

    public UserProduct(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return
                product + "(" + amount + " units)";
    }
}
