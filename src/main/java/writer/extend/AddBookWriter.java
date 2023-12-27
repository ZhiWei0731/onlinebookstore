package writer.extend;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.constant.db.BooksDBConstants;
import com.bittercode.model.Book;
import com.bittercode.service.SellerBookService;
import com.bittercode.service.impl.SellerBookServiceImpl;

import writer.SellerBookWriter;

public class AddBookWriter extends SellerBookWriter {
    HttpServletRequest req = getRequest();
    PrintWriter pw = getPrintWriter();
    SellerBookService bookService = new SellerBookServiceImpl();
    
    public AddBookWriter(HttpServletRequest req, HttpServletResponse res) {
        super(req, res);
    }

    public  void writeTopContent() {
        pw.println("<div class='container my-2'>");
    }
    
    public void writeBookContent(){
        String bName = req.getParameter(BooksDBConstants.COLUMN_NAME);
        if(bName == null || bName.isBlank()) {
            //render the add book form;
            this.showAddBookForm();
            return;
        } //else process the add book
        
        try {
            String uniqueID = UUID.randomUUID().toString();
            String bCode = uniqueID;
            String bAuthor = req.getParameter(BooksDBConstants.COLUMN_AUTHOR);
            double bPrice = Integer.parseInt(req.getParameter(BooksDBConstants.COLUMN_PRICE));
            int bQty = Integer.parseInt(req.getParameter(BooksDBConstants.COLUMN_QUANTITY));

            Book book = new Book(bCode, bName, bAuthor, bPrice, bQty);
            String message = bookService.addBook(book);
            if ("SUCCESS".equalsIgnoreCase(message)) {
                pw.println(
                        "<table class=\"tab\"><tr><td>Book Detail Updated Successfully!<br/>Add More Books</td></tr></table>");
            } else {
                pw.println("<table class=\"tab\"><tr><td>Failed to Add Books! Fill up CareFully</td></tr></table>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.writeFaliOperation();
        }
    }

    public void writeBookContent(List<Book> books){
        return;
    }

    public void writeBottomContent(){
        pw.println("  </tbody>\r\n" + "</table></div>");
    }

    public void writeFaliOperation(){
        pw.println("<table class=\"tab\"><tr><td>Failed to Add Books! Fill up CareFully</td></tr></table>");
    }

    private void showAddBookForm() {
        String form = "<table class=\"tab my-5\" style=\"width:40%;\">\r\n"
                + "        <tr>\r\n"
                + "            <td>\r\n"
                + "                <form action=\"addbook\" method=\"post\">\r\n"
                + "                    <!-- <label for=\"bookCode\">Book Code : </label><input type=\"text\" name=\"barcode\" id=\"bookCode\" placeholder=\"Enter Book Code\" required><br/> -->\r\n"
                + "                    <label for=\"bookName\">Book Name : </label> <input type=\"text\" name=\"name\" id=\"bookName\" placeholder=\"Enter Book's name\" required><br/>\r\n"
                + "                    <label for=\"bookAuthor\">Book Author : </label><input type=\"text\" name=\"author\" id=\"bookAuthor\" placeholder=\"Enter Author's Name\" required><br/>\r\n"
                + "                    <label for=\"bookPrice\">Book Price : </label><input type=\"number\" name=\"price\" placeholder=\"Enter the Price\" required><br/>\r\n"
                + "                    <label for=\"bookQuantity\">Book Qnty : </label><input type=\"number\" name=\"quantity\" id=\"bookQuantity\" placeholder=\"Enter the quantity\" required><br/>\r\n"
                + "                    <input class=\"btn btn-success my-2\" type=\"submit\" value=\" Add Book \">\r\n"
                + "                </form>\r\n"
                + "            </td>\r\n"
                + "        </tr>  \r\n"
                + "        <!-- <tr>\r\n"
                + "            <td><a href=\"index.html\">Go Back To Home Page</a></td>\r\n"
                + "        </tr> -->\r\n"
                + "    </table>";
        pw.println(form);
    }
}
