package com.revshop.presentation;

import com.revshop.repository.UserRepository;
import com.revshop.service.AuthenticationService;

import java.util.Scanner;

public class RevShopApplication {

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        AuthenticationService authService = new AuthenticationService(userRepository);

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
            scanner.nextLine(); // clear buffer

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

                    authService.login(lEmail, lPassword);
                    break;

                case 4:
                    System.out.println("Exiting application...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
