package writer.extend;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.constant.ResponseCode;
import com.bittercode.constant.db.BooksDBConstants;
import com.bittercode.model.Book;
import com.bittercode.service.SellerBookService;
import com.bittercode.service.impl.SellerBookServiceImpl;

import writer.SellerBookWriter;


public class UpdateBookWriter extends SellerBookWriter {
    HttpServletRequest req = getRequest();
    PrintWriter pw = getPrintWriter();
    SellerBookService bookService = new SellerBookServiceImpl();

    public UpdateBookWriter(HttpServletRequest req, HttpServletResponse res) {
        super(req, res);
    }

    public  void writeTopContent() {
        pw.println("<div class='container my-2'>");
    }
    
    public void writeBookContent(){
        try {
            String bookId = req.getParameter("bookId");

            if (bookId != null) {
                Book book = bookService.getBookById(bookId);
                this.showUpdateBookForm(book);
            }

            if (req.getParameter("updateFormSubmitted") != null) {
                String bName = req.getParameter(BooksDBConstants.COLUMN_NAME);
                String bCode = req.getParameter(BooksDBConstants.COLUMN_BARCODE);
                String bAuthor = req.getParameter(BooksDBConstants.COLUMN_AUTHOR);
                double bPrice = Double.parseDouble(req.getParameter(BooksDBConstants.COLUMN_PRICE));
                int bQty = Integer.parseInt(req.getParameter(BooksDBConstants.COLUMN_QUANTITY));

                Book book = new Book(bCode, bName, bAuthor, bPrice, bQty);
                String message = bookService.updateBook(book);
                if (ResponseCode.SUCCESS.name().equalsIgnoreCase(message)) {
                    pw.println("<table class=\"tab\"><tr><td>Book Detail Updated Successfully!</td></tr></table>");
                } else {
                    pw.println("<table class=\"tab\"><tr><td>Failed to Update Book!!</td></tr></table>");
                }
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            this.writeFaliOperation();
        }
    }

    public void writeBottomContent(){
        return;
    }

    public void writeFaliOperation(){
        pw.println("<table class=\"tab\"><tr><td>Failed to Load Book data!!</td></tr></table>");
    }

    private void showUpdateBookForm(Book book) {
        String form = "<table class=\"tab my-5\" style=\"width:40%;\">\r\n"
                + "        <tr>\r\n"
                + "            <td>\r\n"
                + "                <form action=\"updatebook\" method=\"post\">\r\n"
                + "                    <label for=\"bookCode\">Book Code : </label><input type=\"text\" name=\"barcode\" id=\"bookCode\" placeholder=\"Enter Book Code\" value='"
                + book.getBarcode() + "' readonly><br/>"
                + "                    <label for=\"bookName\">Book Name : </label> <input type=\"text\" name=\"name\" id=\"bookName\" placeholder=\"Enter Book's name\" value='"
                + book.getName() + "' required><br/>\r\n"
                + "                    <label for=\"bookAuthor\">Book Author : </label><input type=\"text\" name=\"author\" id=\"bookAuthor\" placeholder=\"Enter Author's Name\" value='"
                + book.getAuthor() + "' required><br/>\r\n"
                + "                    <label for=\"bookPrice\">Book Price : </label><input type=\"number\" name=\"price\" placeholder=\"Enter the Price\" value='"
                + book.getPrice() + "' required><br/>\r\n"
                + "                    <label for=\"bookQuantity\">Book Qnty : </label><input type=\"number\" name=\"quantity\" id=\"bookQuantity\" placeholder=\"Enter the quantity\" value='"
                + book.getQuantity() + "' required><br/>\r\n"
                + "                    <input class=\"btn btn-success my-2\" type=\"submit\" name='updateFormSubmitted' value=\" Update Book \">\r\n"
                + "                </form>\r\n"
                + "            </td>\r\n"
                + "        </tr>  \r\n"
                + "    </table>";
        pw.println(form);
    }
}
