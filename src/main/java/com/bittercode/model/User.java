package com.bittercode.model;

import java.io.Serializable;
//import java.util.List;

public class User implements Serializable {

    private String emailId;
    private String password;
    private String firstName;
    private String lastName;
    private Long phone;
    private String address;
    private UserRole role;

    public User() {
        this(null, null, null, null, 
            null, null, UserRole.NULL);
    }

    public User(String emailId, String password, String firstName, 
                String lastName, Long phone, String address, UserRole role) {
        setEmailId(emailId);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
        setRole(role);
    }

    public User(String emailId, String password, String firstName, 
                String lastName, Long phone, String address) {
        this(emailId, password, firstName, lastName, phone, address, UserRole.NULL);
    }

    public String getEmailId() {
        return emailId;
    }

    private void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    private void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    public UserRole getRoles() {
        return role;
    }

    private void setRole(UserRole roles) {
        this.role = roles;
    }

    public String toString() {
        return "User{emailId=" + emailId + ", password=" + password + ", firstName=" + firstName + ", lastName="
                + lastName + ", address=" + address + ", phone=" + phone  + ", role=" + role + "}";
    }
//    public static User retrieveFromHttpServletRequest(HttpServletRequest req) {
//        User user = new User();
//        String pWord = req.getParameter(UsersDBConstants.COLUMN_PASSWORD);
//        String fName = req.getParameter(UsersDBConstants.COLUMN_FIRSTNAME);
//        String lName = req.getParameter(UsersDBConstants.COLUMN_LASTNAME);
//        String addr = req.getParameter(UsersDBConstants.COLUMN_ADDRESS);
//        String phNo = req.getParameter(UsersDBConstants.COLUMN_PHONE);
//        String mailId = req.getParameter(UsersDBConstants.COLUMN_MAILID);
//        user.setEmailId(mailId);
//        user.setFirstName(fName);
//        user.setLastName(lName);
//        user.setPassword(pWord);
//        user.setPhone(Long.parseLong(phNo));
//        user.setAddress(addr);
//        return user;
//    }
}
