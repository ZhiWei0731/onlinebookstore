package com;

import com.bittercode.model.UserRole;

public class test_constants {
    public static void main(String[] args) {
        printUserRoles();
    }

    public static void printUserRoles() {
        System.out.println(UserRole.CUSTOMER.name());   // CUSTOMER
        System.out.println(UserRole.SELLER);            // SELLER
        int SellerType = UserRole.SELLER.equals(UserRole.SELLER) ? 1 : 2;      
        int CustomerType = UserRole.SELLER.equals(UserRole.CUSTOMER) ? 1 : 2;   
        System.out.println(SellerType);     // 1
        System.out.println(CustomerType);   // 2
    }

}
