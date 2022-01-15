public class ShoppingCart {
    private UserProduct[] products;
    private double totalPrice;

    public ShoppingCart() {
        products = new UserProduct[0];
    }

    public void addProductToShoppingCart(UserProduct userProduct) {
        UserProduct[] newProd = new UserProduct[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            newProd[i] = products[i];
        }
        newProd[products.length] = userProduct;
        products = newProd;
        totalPrice += userProduct.getProduct().getPrice() * userProduct.getAmount();
    }

    public UserProduct[] getProducts() {
        return products;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        System.out.println("here is your current shopping cart ");
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i].getProduct());
        }
        return "";
    }
}