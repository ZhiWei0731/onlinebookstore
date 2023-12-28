package test_com;

import org.junit.Assert;
import org.junit.Test;

import com.bittercode.constant.ResponseCode;
import com.bittercode.model.Book;
import com.bittercode.model.StoreException;
import com.bittercode.model.User;
import com.bittercode.model.UserRole;

public class ConstantsTest {
    
    @Test
    public void testUserRolesSeller() {
        UserRole user = UserRole.SELLER; 
        int SellerType = user.equals(UserRole.SELLER) ? 1 : 2;  
        Assert.assertEquals(1, SellerType);
    }
    
    @Test
    public void testUserRolesCustomer() {
        UserRole user = UserRole.CUSTOMER; 
        int CustomerType = user.equals(UserRole.SELLER) ? 1 : 2;   
        Assert.assertEquals(2, CustomerType);
    }
    
    @Test
    public void testUserRolesNull() {
        UserRole user = UserRole.NULL; 
        Assert.assertEquals("NULL", user.name());
    }
    
    @Test
    public void testUserFullParam() {
        String emailId = "admin@gmail.com";
        String password = "Admin";
        String firstName = "Mr.";
        String lastName = "Admin";
        Long phone = Long.parseLong("9584552224521");
        String address = "Haldia WB";      
        UserRole role = UserRole.SELLER;
        User user = new User(emailId, password, firstName, lastName, phone, address, role);
        Assert.assertEquals("User{emailId=admin@gmail.com, password=Admin, firstName=Mr., lastName=Admin, "
                + "address=Haldia WB, phone=9584552224521, role=SELLER}", user.toString());
    }
    
    @Test
    public void testUserLackOneParam() {
        String emailId = "admin@gmail.com";
        String password = "Admin";
        String firstName = "Mr.";
        String lastName = "Admin";
        Long phone = Long.parseLong("9584552224521");
        String address = "Haldia WB";      
        User user = new User(emailId, password, firstName, lastName, phone, address);
        Assert.assertEquals("User{emailId=admin@gmail.com, password=Admin, firstName=Mr., lastName=Admin, "
                + "address=Haldia WB, phone=9584552224521, role=NULL}", user.toString());
    }
    
    @Test
    public void testUserNullParam() {
        User user = new User();
        Assert.assertEquals("User{emailId=null, password=null, firstName=null, lastName=null, "
                + "address=null, phone=null, role=NULL}", user.toString());
    }

    @Test
    public void testNewBook() {
        Book book = new Book("123456", "Java", "William", 500, 10);
        Assert.assertEquals("Book{barcode='123456', name='Java', author='William', price=500.0, quantity=10}", 
                book.toString());
    }  

    @Test
    public void testResponseCode() {
        ResponseCode responseCode = ResponseCode.SUCCESS;
        Assert.assertEquals("SUCCESS", responseCode.name());
        Assert.assertEquals(200, responseCode.getCode());
        Assert.assertEquals("OK", responseCode.getMessage());
        Assert.assertEquals("ResponseCode{type='SUCCESS', message='OK', code=200}", responseCode.toString());
    }

    @Test
    public void testStoreException() {
        StoreException se = new StoreException(ResponseCode.SUCCESS);
        Assert.assertEquals("StoreException{errorCode='SUCCESS', errorMessage='OK', statusCode=200}", 
                se.toString());
    }


    
}
