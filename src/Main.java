import java.util.Scanner;
public class Main {

    public static final int CREATE_USER = 1;
    public static final int LOGIN_USER = 2;
    public static final int ENTER_BY_CLIENT = 1;
    public static final int ENTER_BY_EMPLOYEE = 2;
    public static final int EXIT = 3;
    public static final int PRINT_ALL_CLIENTS = 1;
    public static final int PRINT_VIP_CLIENTS = 2;
    public static final int SHOW_CLIENT_WITH_AT_LEAST_ONE_PURCHASE = 3;
    public static final int SHOW_HIGHEST_BUYING_COSTUMER = 4;
    public static final int ADD_NEW_PRODUCT = 5;
    public static final int CHANGE_PROFILE = 6;
    public static final int SHOPPING = 7;
    public static final int EXIT_EMPLOYEE_MENU = 8;
    public static final int MIN_DISCOUNT = 0;
    public static final int MAX_DISCOUNT = 100;

    public static void main(String[] args) {
        Shop shop = new Shop();
        boolean exit = false;
        do {
            switch (startMenu()) {
                case CREATE_USER:
                    shop.createUser();
                    break;
                case LOGIN_USER:
                    int userChoice = userMenu();
                    switch (userChoice) {
                        case ENTER_BY_CLIENT:
                            User currentUser = shop.login(userChoice);
                            if (currentUser == null) {
                                System.out.println("User name or password doesn't match");
                                break;
                            } else {
                                System.out.println("hello " + currentUser);
                                if (shop.getProductsInStock().length > 0) {
                                    shop.goShopping(currentUser);
                                } else {
                                    System.out.println("there are no products in the store yet");
                                }
                            }
                            break;
                        case ENTER_BY_EMPLOYEE:
                            User currentUser1 = shop.login(userChoice);
                            if (currentUser1 == null) {
                                System.out.println("User name or password doesn't match");
                            } else {
                                System.out.println("hello " + currentUser1);
                                boolean employeeFlag = true;
                                do {
                                    switch (getEmployeeMenu()) {
                                        case PRINT_ALL_CLIENTS:
                                            shop.printAllClients();
                                            break;
                                        case PRINT_VIP_CLIENTS:
                                            shop.printAllVipClients();
                                            break;
                                        case SHOW_CLIENT_WITH_AT_LEAST_ONE_PURCHASE:
                                            shop.printClientsWithOnePurchaseOrMore();
                                            break;
                                        case SHOW_HIGHEST_BUYING_COSTUMER:
                                            shop.printHighestBuyingCustomer();
                                            break;
                                        case ADD_NEW_PRODUCT:
                                            Product newProd = getNewProduct();
                                            shop.AddProductToShop(newProd);
                                            System.out.println("The product was successfully added to the store");
                                            break;
                                        case CHANGE_PROFILE:
                                            if (shop.getProducts().length > 0) {
                                                int productChoice = shop.getProductChoice();
                                                shop.updateProfile(productChoice);
                                                System.out.println("the product profile has been change");
                                            } else {
                                                System.out.println("there are no products in the store yet");
                                            }
                                            break;
                                        case SHOPPING:
                                            if (shop.getProductsInStock().length > 0) {
                                                shop.goShopping(currentUser1);
                                            } else {
                                                System.out.println("there are no products in the store yet");
                                            }
                                            break;
                                        case EXIT_EMPLOYEE_MENU:
                                            employeeFlag = false;
                                            break;
                                        default:
                                            break;
                                    }
                                } while (employeeFlag);
                            }
                    }
                    break;
                case EXIT:
                    exit = true;
                    System.out.println("bye bye...");
                    break;
                default:
                    break;
            }

        } while (!exit);
    }


    private static Product getNewProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what the name of the product?");
        String productName = scanner.nextLine();
        System.out.println("what the price of the product?");
        double price = scanner.nextDouble();
        double discount;
        do {
            System.out.println("how much discount did vip member get on this product?");
            discount = scanner.nextDouble();
        } while (discount < MIN_DISCOUNT && discount > MAX_DISCOUNT);
        return new Product(productName, price, discount, true);
    }

    private static int getEmployeeMenu() {
        int employeeChoice;
        do {
            System.out.println("What do you want to do?" + "\n" + "1) show all clients" +
                    "\n" + "2) show all vip clients" + "\n" + "3) show clients with at least one purchase" +
                    "\n" + "4) show highest buying customer" + "\n" + "5) add new product" + "\n" + "6) change profile for product" +
                    "\n" + "7) do some shopping" + "\n" + "8) exit");

            Scanner s = new Scanner(System.in);
            employeeChoice = s.nextInt();
        } while (employeeChoice <= 0 || employeeChoice > 8);
        return employeeChoice;
    }

    private static int startMenu() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            System.out.println("\n" + "Welcome to our virtual Shop" + "\n" + "What do you want to do? " + "\n" + "1: Create  a new account" + "\n" + "2: Enter exist account " + "\n" + "3: Exit from program");
            System.out.println("Please enter your choice");
            userChoice = scanner.nextInt();
        } while (userChoice < 1 || userChoice > 3);
        return userChoice;
    }

    private static int userMenu() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            System.out.println("Hi! " + "\n" + "Who are you? " + "\n" + "1) Client" + "\n" + "2) Employee ");
            System.out.println("Enter your choice");
            userChoice = scanner.nextInt();
        } while (userChoice < 1 || userChoice > 2);
        return userChoice;
    }


}