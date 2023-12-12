package print;

import com.bittercode.constant.ResponseCode;
import com.bittercode.model.StoreException;

import com.bittercode.model.Book;


public class SeePrint {

    public static void main(String[] args) {
        printResponseCode();
        printStoreException();
        printBook();
    }
    
    
    // com.bittercode.constant.ResponseCode
    private static void printResponseCode() {
        System.out.println("# printResponseCode()");
        // 使用列舉常數
        System.out.println(ResponseCode.SUCCESS); // output: SUCCESS
        System.out.println(ResponseCode.FAILURE); // output: FAILURE
        
        // 取得列舉常數的值
        System.out.println(ResponseCode.SUCCESS.name());        // output: SUCCESS, Enum class 的 method
        System.out.println(ResponseCode.SUCCESS.getCode());     // output: 200，ResponseCode 的 method
        System.out.println(ResponseCode.SUCCESS.getMessage());  // output: "OK"，ResponseCode 的 method
        
        // how to use Optional
        System.out.println(ResponseCode.getMessageByStatusCode(200)); // output: Optional[SUCCESS]
        System.out.println(ResponseCode.getMessageByStatusCode(199)); // output: Optional.empty
        
    }
    
    
    // com.bittercode.constant.Address
    private static void printAddress() {
        // no constructor
    }
    
    
    // com.bittercode.model.Book
    private static void printBook() {
        System.out.println("\n# printBook()");
        Book book = new Book();
        book.setBarcode("123456");
        book.setName("Java");
        book.setAuthor("William");
        book.setPrice(500);
        book.setQuantity(10);
        
        System.out.println(book.toString()); 
    }  
    
    
    
    // com.bittercode.model.StoreException 
    private static void printStoreException() {
        System.out.println("\n# printStoreException()");
        StoreException se = new StoreException(ResponseCode.SUCCESS);
        System.out.println(se.toString());  // output: {errorCode=SUCCESS, errorMessage=OK, statusCode=200}
    }
    
 
    
}
