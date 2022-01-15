import java.util.Date;
import java.util.Scanner;


public class Shop {
    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final double RANK1_DISCOUNT = 0.9;
    public static final double RANK2_DISCOUNT = 0.8;
    public static final double RANK3_DISCOUNT = 0.7;
    private User[] users;
    private Product[] products;
    private Product[] productsInStock;

    public Product[] getProductsInStock() {
        return productsInStock;
    }

    public Shop() {
        users = new User[0];
        products = new Product[0];
        productsInStock = new Product[0];
    }

    private void updateProductsInStock() {
        Product[] existArr = new Product[products.length];
        int index = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i].isExistInStore()) {
                existArr[index++] = products[i];
            }
        }
        productsInStock = shortenProductArray(existArr, index);
    }

    private Product[] shortenProductArray(Product[] existArr, int index) {
        Product[] shortArr = new Product[index];
        for (int i = 0; i < shortArr.length; i++) {
            shortArr[i] = existArr[i];

        }
        return shortArr;
    }

    public void printAllProducts() {
        for (int i = 0; i < this.products.length; i++) {
            System.out.println(i + 1 + ") " + products[i].getNameOfProduct());
        }

    }

    public int getProductChoice() {
        Scanner scanner = new Scanner(System.in);
        printAllProducts();
        System.out.println("choose the number of product from the list that you want to update");
        int indexOfProduct = scanner.nextInt();
        return indexOfProduct - 1;
    }

    public void AddProductToShop(Product product) {
        Product[] newProd = new Product[products.length + 1];

        for (int i = 0; i < products.length; i++) {
            newProd[i] = products[i];
        }
        newProd[products.length] = product;
        products = newProd;
        updateProductsInStock();
    }

    public Product[] getProducts() {
        return products;
    }


    public void printListOfProductsInStock() {
        for (int i = 0; i < productsInStock.length; i++) {
            System.out.println(i + 1 + " : " + productsInStock[i]);
        }
    }

    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi! which kind of account do you want create?" + "\n" + "1- client" + "\n" + "another number- Employee");
        int result = new Scanner(System.in).nextInt();
        boolean isClient = kindOfUser(result);
        String firstName, lastName;
        do {
            System.out.println("Enter your first name");
            firstName = new Scanner(System.in).nextLine();
            if (!validName(firstName)) {
                System.out.println("the name cannot be with numbers ");
            }
        } while (!validName(firstName));

        do {
            System.out.println("Enter your last name");
            lastName = new Scanner(System.in).nextLine();
            if (!validName(lastName)) {
                System.out.println("the name cannot be with numbers ");
            }
        } while (!validName(lastName));
        String username, password;
        do {
            System.out.println("Enter username: ");
            username = new Scanner(System.in).nextLine();
            if (isUsernameExist(username)) {
                System.out.println("Username already exists,please try a different username");
            }
        } while (isUsernameExist(username));
        do {
            System.out.println("Enter password:" + "\n" + "The password must have at least 6 chars");
            password = new Scanner(System.in).nextLine();
            if (!validPassword(password)) {
                System.out.println("The password isn't strong enough. ");
            }
        } while (!validPassword(password));
        boolean vip = false;
        int workerRank = -1;
        System.out.println("are you a member club?" + "enter 1 for yes or another number for no");
        int isVipMember = scanner.nextInt();
        vip = intToBoolean(isVipMember);


        if (!isClient) {
            do {
                System.out.println("Whats your rank?" + "\n" + "Enter 1 for regular worker" + "\n" + "Enter 2 for director" + "\n" + "Enter 3 for board member\n");
                workerRank = new Scanner(System.in).nextInt();
            } while (workerRank < 1 && workerRank > 3);
        }
        addUserToArray(firstName, lastName, username, password, vip, workerRank, isClient);
        System.out.println("hey " + firstName + "\n" + "user created successfully! ");
    }

    private boolean intToBoolean(int check) {
        return check == 1;
    }

    private void addUserToArray(String firstName, String lastName, String username, String password, boolean vip, int rank, boolean isClient) {
        User userToAdd;
        if (isClient) {
            userToAdd = new Client(firstName, lastName, username, password, vip);
        } else {
            userToAdd = new Employee(firstName, lastName, username, password, rank, vip);
        }

        User[] newArray = new User[users.length + 1];
        for (int i = 0; i < users.length; i++) {
            newArray[i] = users[i];
        }

        newArray[users.length] = userToAdd;
        users = newArray;
    }


    private boolean validPassword(String password) {
        return password.length() >= PASSWORD_MIN_LENGTH;

    }

    private boolean validName(String firstName) {
        boolean valid = true;
        for (int i = 0; i < firstName.length(); i++) {
            if (firstName.charAt(i) >= 48 && firstName.charAt(i) <= 57) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    private boolean kindOfUser(int result) {
        return result == 1;
    }

    public User login(int userChoice) {
        Scanner scanner = new Scanner(System.in);
        String username, password;
        User user = null;
        System.out.println("Enter username: ");
        username = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();
        int userIndex = isUsernameExistInArr(username);
        if (userIndex != -1) {
            if (doesPasswordMatch(userIndex, password)) {
                if (userChoice == 1) {
                    if (!(users[userIndex] instanceof Employee)) {
                        user = users[userIndex];
                    }
                } else {
                    if (users[userIndex] instanceof Employee) {
                        user = users[userIndex];
                    }
                }
            }
        }
        return user;
    }

    private boolean doesPasswordMatch(int userIndex, String password) {
        return users[userIndex].getPassword().equals(password);
    }

    private boolean isUsernameExist(String usernameToCheck) {
        boolean exists = false;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUserName().equals(usernameToCheck)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private int isUsernameExistInArr(String usernameToCheck) {
        int userIndex = -1;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUserName().equals(usernameToCheck)) {
                userIndex = i;
                break;
            }
        }
        return userIndex;
    }


    public void printAllClients() {
        if (users.length > 0) {
            for (int i = 0; i < users.length; i++) {
                System.out.println(((Client) users[i]).fullPrint());
            }
        } else {
            System.out.println("there are no user in the system yet");
        }

    }


    public void printAllVipClients() {
        int count = 0;
        for (int i = 0; i < users.length; i++) {
            Client currentClient = (Client) users[i];
            if (currentClient.isVip()) {
                System.out.println(currentClient.fullPrint());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("there are no vip clients");
        }
    }

    public void printClientsWithOnePurchaseOrMore() {
        int count = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getShoppingTimes() > 0) {
                System.out.println(((Client) users[i]).fullPrint());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("there are no client found");
        }
    }

    public void printHighestBuyingCustomer() {
        double max = 0;
        int index = -1;

        for (int i = 0; i < users.length; i++) {
            if (users[i].getTotalShoppingAmount() > max) {
                max = users[i].getTotalShoppingAmount();
                index = i;
            }
        }
        if (index != -1) {
            System.out.println("The customer who bought the highest amount is " + ((Client) users[index]).fullPrint());
        } else {
            System.out.println("No one made a purchase yet...");
        }
    }

    public void updateProfile(int productChoiceIndex) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the product in stock? enter 1 for yes or another number to no");
        int addToStock = scanner.nextInt();
        boolean toAdd = intToBoolean(addToStock);
        this.products[productChoiceIndex].setExistInStore(toAdd);
        updateProductsInStock();
    }


    public void goShopping(User currentUser) {
        Scanner scanner = new Scanner(System.in);
        boolean shoppingFlag = true;
        while (shoppingFlag) {
            int numOfProductFromList;
            do {
                printListOfProductsInStock();
                System.out.println("Please choose product from list:");
                System.out.println("To finish shopping, press -1 " + "\n");
                numOfProductFromList = scanner.nextInt();
                if ((numOfProductFromList > this.productsInStock.length || numOfProductFromList <= 0) && numOfProductFromList != -1) {
                    System.out.println("The number you selected is invalid");
                }
            } while ((numOfProductFromList > this.productsInStock.length || numOfProductFromList <= 0) && numOfProductFromList != -1);
            if (numOfProductFromList == -1) {
                shoppingFlag = false;
                break;
            }
            int items;
            do {
                System.out.println("How many items would you like from the same product?");
                items = scanner.nextInt();
            } while (items < 0);
            UserProduct userProduct = new UserProduct(this.productsInStock[numOfProductFromList - 1], items);
            currentUser.getShoppingCart().addProductToShoppingCart(userProduct);
            System.out.println(currentUser.getShoppingCart());
            System.out.println("your current price is " + calculateCurrentPrice(currentUser));
        }
        finishSopping(currentUser);
    }

    private void finishSopping(User currentUser) {
        if (currentUser.getShoppingCart().getProducts().length > 0) {
            System.out.println("your total price is " + calculateCurrentPrice(currentUser));
            currentUser.setTotalShoppingAmount(calculateCurrentPrice(currentUser) + currentUser.getTotalShoppingAmount());
            Date now = new Date();
            currentUser.setLastPurchaseDate(now);
            currentUser.incrementShoppingTimes();
            currentUser.setShoppingCart(new ShoppingCart());
        }
    }

    private double calculateCurrentPrice(User currentUser) {
        Client currentClient = (Client) currentUser;
        double sum = 0;
        if (currentClient.isVip()) {

            for (int i = 0; i < currentUser.getShoppingCart().getProducts().length; i++) {

                sum = sum + currentUser.getShoppingCart().getProducts()[i].getProduct().getPrice() * (currentUser.getShoppingCart().getProducts()[i].getProduct().getDiscount() / 100) * currentUser.getShoppingCart().getProducts()[i].getAmount();

            }
        } else {
            sum = currentUser.getShoppingCart().getTotalPrice();
        }
        if (currentUser instanceof Employee) {
            if (((Employee) currentUser).getRank() == 1) {
                sum = sum * RANK1_DISCOUNT;
            } else if (((Employee) currentUser).getRank() == 2) {
                sum = sum * RANK2_DISCOUNT;
            } else {
                sum = sum * RANK3_DISCOUNT;
            }
        }
        return sum;
    }
}
