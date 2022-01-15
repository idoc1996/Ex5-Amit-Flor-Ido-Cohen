public class Product {
    private String nameOfProduct;
    private double Price;
    private double discount;
    boolean existInStore;

    public Product(String nameOfProduct, double price, double discount, boolean existInStore) {
        this.nameOfProduct = nameOfProduct;
        this.Price = price;
        this.discount = discount;
        this.existInStore = existInStore;
    }
    public double getDiscount() {
        return discount;
    }

    public boolean isExistInStore() {
        return existInStore;
    }

    public void setExistInStore(boolean existInStore) {
        this.existInStore = existInStore;
    }


    public String getNameOfProduct() {
        return nameOfProduct;
    }


    public double getPrice() {
        return Price;
    }

    @Override
    public String toString() {
        return nameOfProduct +
                ", Price: " + Price + "$ ," + " discount for member club: " + discount + "%";
    }
}