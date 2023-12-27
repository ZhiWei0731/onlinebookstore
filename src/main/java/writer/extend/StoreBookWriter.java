package writer.extend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.Book;

import java.io.PrintWriter;
import java.util.List;

import writer.SellerBookWriter;

public class StoreBookWriter extends SellerBookWriter{
    PrintWriter pw = getPrintWriter();

    public StoreBookWriter(HttpServletRequest req, HttpServletResponse res) {
        super(req, res);
    }

    public  void writeTopContent() {
        pw.println("<div class='container'>");
        pw.println("<div id='topmid' style='background-color:grey'>Books Available In the Store</div>");
        pw.println("<table class=\"table table-hover\" style='background-color:white'>\r\n"
                + "  <thead>\r\n"
                + "    <tr style='background-color:black; color:white;'>\r\n"
                + "      <th scope=\"col\">BookId</th>\r\n"
                + "      <th scope=\"col\">Name</th>\r\n"
                + "      <th scope=\"col\">Author</th>\r\n"
                + "      <th scope=\"col\">Price</th>\r\n"
                + "      <th scope=\"col\">Quantity</th>\r\n"
                + "      <th scope=\"col\">Action</th>\r\n"
                + "    </tr>\r\n"
                + "  </thead>\r\n"
                + "  <tbody>\r\n");
    }
    
    public void writeBookContent(){
        return;
    }

    public void writeBookContent(List<Book> books){
        if (books == null || books.size() == 0) {
            this.writeNoAvailableBook();
        }
        for (Book book : books) {
            pw.println(getRowData(book));
        }
    }

    public void writeBottomContent(){
        pw.println("  </tbody>\r\n" + "</table></div>");
    }

    public void writeFaliOperation(){
        return;
    }

    private void writeNoAvailableBook(){
        pw.println("    <tr style='background-color:green'>\r\n"
                    + "      <th scope=\"row\" colspan='6' style='color:yellow; text-align:center;'> No Books Available in the store </th>\r\n"
                    + "    </tr>\r\n");
    }

    private String getRowData(Book book) {
        return "    <tr>\r\n"
                + "      <th scope=\"row\">" + book.getBarcode() + "</th>\r\n"
                + "      <td>" + book.getName() + "</td>\r\n"
                + "      <td>" + book.getAuthor() + "</td>\r\n"
                + "      <td><span>&#8377;</span> " + book.getPrice() + "</td>\r\n"
                + "      <td>"
                + book.getQuantity()
                + "      </td>\r\n"
                + "      <td><form method='post' action='updatebook'>"
                + "          <input type='hidden' name='bookId' value='" + book.getBarcode() + "'/>"
                + "          <button type='submit' class=\"btn btn-success\">Update</button>"
                + "          </form>"
                + "    </tr>\r\n";
    }
}
