package com.revshop.presentation;

import com.revshop.model.*;
import com.revshop.repository.*;
import com.revshop.service.*;
import com.revshop.util.NotificationService;

import java.util.Scanner;

public class RevShopApplication {

    public static void main(String[] args) {

        // ====== Repositories ======
        UserRepository userRepository = new UserRepository();
        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository();

        // ====== Services ======
        AuthenticationService authService = new AuthenticationService(userRepository);
        ProductService productService = new ProductService(productRepository);
        CartService cartService = new CartService();
        NotificationService notificationService = new NotificationService();
        OrderService orderService = new OrderService(orderRepository, notificationService);
        ReviewService reviewService = new ReviewService();

        Scanner scanner = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("        Welcome to RevShop");
        System.out.println("===================================");

        while (true) {
            System.out.println("\n1. Register Buyer");
            System.out.println("2. Register Seller");
            System.out.println("3. Login");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter Security Question: ");
                    String securityQuestion = scanner.nextLine();
                    System.out.print("Enter Security Answer: ");
                    String securityAnswer = scanner.nextLine();

                    authService.registerBuyer(name, email, password, securityQuestion, securityAnswer);
                    break;

                case 2:
                    System.out.print("Enter Name: ");
                    String sName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String sEmail = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String sPassword = scanner.nextLine();
                    System.out.print("Enter Security Question: ");
                    String sSecurityQuestion = scanner.nextLine();
                    System.out.print("Enter Security Answer: ");
                    String sSecurityAnswer = scanner.nextLine();
                    System.out.print("Enter Business Name: ");
                    String businessName = scanner.nextLine();

                    authService.registerSeller(sName, sEmail, sPassword, sSecurityQuestion, sSecurityAnswer, businessName);
                    break;

                case 3:
                    System.out.print("Enter Email: ");
                    String lEmail = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String lPassword = scanner.nextLine();

                    User loggedInUser = authService.login(lEmail, lPassword);

                    if (loggedInUser instanceof Seller) {
                        sellerMenu((Seller) loggedInUser, productService, orderService, scanner);
                    } else if (loggedInUser instanceof Buyer) {
                        buyerMenu((Buyer) loggedInUser, productService, cartService, orderService, reviewService, scanner);
                    }
                    break;

                case 4:
                    System.out.println("Exiting application...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= SELLER MENU =================
    private static void sellerMenu(Seller seller, ProductService productService,
                                   OrderService orderService, Scanner scanner) {

        while (true) {
            System.out.println("\n===== Seller Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. View My Orders");
            System.out.println("4. Set Product Threshold");
            System.out.println("5. Logout");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter MRP: ");
                    double mrp = scanner.nextDouble();
                    System.out.print("Enter Stock: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();

                    productService.addProduct(name, description, price, mrp, stock, category, seller);
                    break;

                case 2:
                    productService.viewAllProducts();
                    break;

                case 3:
                    orderService.viewSellerOrders(seller);
                    break;

                case 4:
                    productService.viewAllProducts();
                    System.out.print("Enter Product ID: ");
                    String pId = scanner.nextLine();
                    System.out.print("Enter New Threshold: ");
                    int newThreshold = scanner.nextInt();
                    scanner.nextLine();

                    Product product = productService.getProductRepository().findById(pId);
                    if (product != null) {
                        product.setThreshold(newThreshold);
                        System.out.println("Threshold updated!");
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 5:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= BUYER MENU =================
    private static void buyerMenu(Buyer buyer, ProductService productService,
                                  CartService cartService, OrderService orderService,
                                  ReviewService reviewService, Scanner scanner) {

        while (true) {
            System.out.println("\n===== Buyer Menu =====");
            System.out.println("1. Browse Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. Checkout");
            System.out.println("6. View Orders");
            System.out.println("7. Add Review");
            System.out.println("8. Logout");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    productService.viewAllProducts();
                    break;

                case 2:
                    productService.viewAllProducts();
                    System.out.print("Enter Product ID: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    cartService.addToCart(buyer, productService.getProductRepository().findById(productId), quantity);
                    break;

                case 3:
                    cartService.viewCart(buyer);
                    break;

                case 4:
                    System.out.print("Enter Product ID to remove: ");
                    String removeId = scanner.nextLine();
                    cartService.removeFromCart(buyer, removeId);
                    break;

                case 5:
                    orderService.checkout(buyer);
                    break;

                case 6:
                    orderService.viewOrders(buyer);
                    break;

                case 7:
                    productService.viewAllProducts();
                    System.out.print("Enter Product ID: ");
                    String rProductId = scanner.nextLine();
                    System.out.print("Enter Rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Comment: ");
                    String comment = scanner.nextLine();

                    reviewService.addReview(buyer,
                            productService.getProductRepository().findById(rProductId),
                            rating, comment);
                    break;

                case 8:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
