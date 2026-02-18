package com.revshop.service;

import com.revshop.model.Buyer;
import com.revshop.model.Seller;
import com.revshop.model.User;
import com.revshop.repository.UserRepository;

import java.util.UUID;

public class AuthenticationService {

    private UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register Buyer
    public User registerBuyer(String name, String email, String password,
                              String securityQuestion, String securityAnswer) {

        if (userRepository.existsByEmail(email)) {
            System.out.println("Email already registered!");
            return null;
        }

        String id = UUID.randomUUID().toString();

        Buyer buyer = new Buyer(id, name, email, password,
                securityQuestion, securityAnswer);

        userRepository.save(buyer);

        System.out.println("Buyer registered successfully!");
        return buyer;
    }


    // Register Seller
    public User registerSeller(String name, String email, String password,
                               String securityQuestion, String securityAnswer,
                               String businessName) {

        if (userRepository.existsByEmail(email)) {
            System.out.println("Email already registered!");
            return null;
        }

        String id = UUID.randomUUID().toString();

        Seller seller = new Seller(id, name, email, password,
                securityQuestion, securityAnswer,
                businessName);

        userRepository.save(seller);

        System.out.println("Seller registered successfully!");
        return seller;
    }


    // Login
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("User not found!");
            return null;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("Invalid password!");
            return null;
        }

        System.out.println("Login successful!");
        return user;
    }
    public void changePassword(User user, String oldPassword, String newPassword) {

        if (!user.getPassword().equals(oldPassword)) {
            System.out.println("Old password incorrect!");
            return;
        }

        user.setPassword(newPassword);
        System.out.println("Password changed successfully!");
    }
    public void forgotPassword(String email, String answer, String newPassword) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        if (!user.getSecurityAnswer().equalsIgnoreCase(answer)) {
            System.out.println("Security answer incorrect!");
            return;
        }

        user.setPassword(newPassword);
        System.out.println("Password reset successful!");
    }


}
