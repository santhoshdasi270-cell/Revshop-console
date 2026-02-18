package com.revshop.presentation;

import com.revshop.repository.UserRepository;
import com.revshop.service.AuthenticationService;
import com.revshop.model.Seller;
import com.revshop.model.User;
import com.revshop.repository.ProductRepository;
import com.revshop.service.ProductService;


import java.util.Scanner;


public class RevShopApplication {

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        AuthenticationService authService = new AuthenticationService(userRepository);
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);

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
                    } else {
                        System.out.println("Buyer menu will be implemented next.");
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

    // ✅ OUTSIDE main method
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
}
