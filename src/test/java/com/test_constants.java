package com;

import com.bittercode.model.User;
import com.bittercode.model.UserRole;

public class test_constants {
    public static void main(String[] args) {
        testUserRoles();
        testUserNullParam();
        testUserFullParam();
        testUserLackOneParam();
    }

    // com.bittercode.model.UserRole
    public static void testUserRoles() {
        System.out.println("# testUserRoles()");
        System.out.println(UserRole.CUSTOMER.name());   // CUSTOMER
        System.out.println(UserRole.SELLER);            // SELLER
        int SellerType = UserRole.SELLER.equals(UserRole.SELLER) ? 1 : 2;      
        int CustomerType = UserRole.SELLER.equals(UserRole.CUSTOMER) ? 1 : 2;   
        System.out.println(SellerType);     // 1
        System.out.println(CustomerType);   // 2
    }

    // com.bittercode.model.User
    private static void testUserNullParam() {
        System.out.println("\n# testUserNullParam()");
        User user = new User();
        System.out.println(user.toString()); 
    }

    // com.bittercode.model.User
    private static void testUserFullParam() {
        System.out.println("\n# testUserFullParam()");
        String emailId = "admin@gmail.com";
        String password = "Admin";
        String firstName = "Mr.";
        String lastName = "Admin";
        Long phone = Long.parseLong("9584552224521");
        String address = "Haldia WB";      
        UserRole role = UserRole.SELLER;
        User user = new User(emailId, password, firstName, lastName, phone, address, role);
        System.out.println(user.toString()); 
    }

    // com.bittercode.model.User
    private static void testUserLackOneParam() {
        System.out.println("\n# testUserFullParam()");
        String emailId = "admin@gmail.com";
        String password = "Admin";
        String firstName = "Mr.";
        String lastName = "Admin";
        Long phone = Long.parseLong("9584552224521");
        String address = "Haldia WB";      
        User user = new User(emailId, password, firstName, lastName, phone, address);
        System.out.println(user.toString()); 
    }
}
