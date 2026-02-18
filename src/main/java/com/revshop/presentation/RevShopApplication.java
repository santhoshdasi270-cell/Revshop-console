package com.revshop.presentation;

import com.revshop.repository.UserRepository;
import com.revshop.service.AuthenticationService;
import com.revshop.model.Seller;
import com.revshop.model.User;
import com.revshop.repository.ProductRepository;
import com.revshop.service.ProductService;
import com.revshop.model.Buyer;
import com.revshop.service.CartService;
import com.revshop.repository.OrderRepository;
import com.revshop.service.OrderService;




import java.util.Scanner;


public class RevShopApplication {

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        AuthenticationService authService = new AuthenticationService(userRepository);
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        CartService cartService = new CartService();
        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository);



        Scanner scanner = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("      Welcome to RevShop");
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

                    authService.registerBuyer(name, email, password);
                    break;

                case 2:
                    System.out.print("Enter Name: ");
                    String sName = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String sEmail = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String sPassword = scanner.nextLine();

                    System.out.print("Enter Business Name: ");
                    String businessName = scanner.nextLine();

                    authService.registerSeller(sName, sEmail, sPassword, businessName);
                    break;

                case 3:
                    System.out.print("Enter Email: ");
                    String lEmail = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String lPassword = scanner.nextLine();

                    User loggedInUser = authService.login(lEmail, lPassword);

                    if (loggedInUser instanceof Seller) {
                        sellerMenu((Seller) loggedInUser, productService, scanner);
                    } else if (loggedInUser instanceof Buyer) {
                        buyerMenu((Buyer) loggedInUser, productService, cartService, orderService, scanner);

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

    //  OUTSIDE main method
    private static void sellerMenu(Seller seller,
                                   ProductService productService,
                                   Scanner scanner) {

        while (true) {

            System.out.println("\n===== Seller Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Logout");
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

                    productService.addProduct(
                            name,
                            description,
                            price,
                            mrp,
                            stock,
                            category,
                            seller
                    );
                    break;

                case 2:
                    productService.viewAllProducts();
                    break;

                case 3:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    // buyer menu
    private static void buyerMenu(Buyer buyer,
                                  ProductService productService,
                                  CartService cartService,
                                  OrderService orderService,
                                  Scanner scanner) {

        while (true) {

            System.out.println("\n===== Buyer Menu =====");
            System.out.println("1. Browse Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. Checkout");
            System.out.println("6. View Orders");
            System.out.println("7. Logout");
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

                    cartService.addToCart(
                            buyer,
                            productService
                                    .getProductRepository()
                                    .findById(productId),
                            quantity
                    );
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
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

}
