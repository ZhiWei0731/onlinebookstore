package writer.extend;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.constant.ResponseCode;
import com.bittercode.model.Book;
import com.bittercode.service.SellerBookService;
import com.bittercode.service.impl.SellerBookServiceImpl;

import writer.SellerBookWriter;

public class RemoveBookWriter extends SellerBookWriter {
    HttpServletRequest req = getRequest();
    PrintWriter pw = getPrintWriter();
    SellerBookService bookService = new SellerBookServiceImpl();
    
    public RemoveBookWriter(HttpServletRequest req, HttpServletResponse res) {
        super(req, res);
    }

    public  void writeTopContent() {
        pw.println("<div class='container'>");
    }
    
    public void writeBookContent(){
        String bookId = req.getParameter("bookId");
        if (bookId == null || bookId.isBlank()) {
            // render the remove book form;
            this.showRemoveBookForm();
            return;
        } // else continue

        try {
            String responseCode = bookService.deleteBookById(bookId.trim());
            if (ResponseCode.SUCCESS.name().equalsIgnoreCase(responseCode)) {
                pw.println("<table class=\"tab my-5\"><tr><td>Book Removed Successfully</td></tr></table>");
                pw.println("<table class=\"tab\"><tr><td><a href=\"removebook\">Remove more Books</a></td></tr></table>");
            } else {
                pw.println("<table class=\"tab my-5\"><tr><td>Book Not Available In The Store</td></tr></table>");
                pw.println("<table class=\"tab\"><tr><td><a href=\"removebook\">Remove more Books</a></td></tr></table>");
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
        pw.println("</div>");
    }

    public void writeFaliOperation(){
        pw.println("<table class=\"tab\"><tr><td>Failed to Remove Books! Try Again</td></tr></table>");
    }

    private void showRemoveBookForm() {
        String form = "<form action=\"removebook\" method=\"post\" class='my-5'>\r\n"
                + "        <table class=\"tab\">\r\n"
                + "        <tr>\r\n"
                + "            <td>\r\n"
                + "                <label for=\"bookCode\">Enter BookId to Remove </label>\r\n"
                + "                <input type=\"text\" name=\"bookId\" placeholder=\"Enter Book Id\" id=\"bookCode\" required>\r\n"
                + "                <input class=\"btn btn-danger my-2\" type=\"submit\" value=\"Remove Book\">\r\n"
                + "            </td>\r\n"
                + "        </tr>\r\n"
                + "\r\n"
                + "        </table>\r\n"
                + "    </form>";
        pw.println(form);
    }
}
