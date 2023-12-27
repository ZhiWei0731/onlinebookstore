package writer.extend;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bittercode.model.Book;
import com.bittercode.service.CustomerBookService;
import com.bittercode.service.impl.CustomerBookServiceImpl;
import com.bittercode.util.StoreUtil;

import writer.CustomerBookWriter;

public class ViewBookWriter extends CustomerBookWriter {
    HttpServletRequest req = getRequest();
    PrintWriter pw = getPrintWriter();
    CustomerBookService bookService = new CustomerBookServiceImpl();

    public ViewBookWriter(HttpServletRequest req, HttpServletResponse res) {
        super(req, res);
    }

    public void writeTopContent(){
        pw.println("<div id='topmid' style='background-color:grey'>Available Books"
                + "<form action=\"cart\" method=\"post\" style='float:right; margin-right:20px'>"
                + "<input type='submit' class=\"btn btn-primary\" name='cart' value='Proceed'/></form>"
                + "</div>");
        pw.println("<div class=\"container\">\r\n"
                + "        <div class=\"card-columns\">");
    }

    public void writeBookContent(){
        try {
            // Read All available books from the database
            List<Book> books = bookService.getAllBooks();

            // Add or Remove items from the cart, if requested
            StoreUtil.updateCartItems(req);
            HttpSession session = req.getSession();

            for (Book book : books) {
                // Add each book to display as a card
                pw.println(this.addBookToCard(session, book));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void writeBottomContent(){
        pw.println("</div>"
                + "<div style='float:auto'><form action=\"cart\" method=\"post\">"
                + "<input type='submit' class=\"btn btn-success\" name='cart' value='Proceed to Checkout'/></form>"
                + "    </div>");
    }

    public void writeFaliOperation(){
        return;
    }

    private String addBookToCard(HttpSession session, Book book) {
        String bCode = book.getBarcode();
        int bQty = book.getQuantity();

        // Quantity of the current book added to the cart
        int cartItemQty = 0;
        if (session.getAttribute("qty_" + bCode) != null) {
            // Quantity of each book in the cart will be added in the session prefixed with
            // 'qty_' following with bookId
            cartItemQty = (int) session.getAttribute("qty_" + bCode);
        }

        // Button To Add/Remove item from the cart
        String button = "";
        if (bQty > 0) {
            // If no items in the cart, show add to cart button
            // If items is added to the cart, then show +, - button to add/remove more items
            button = "<form action=\"viewbook\" method=\"post\">"
                    + "<input type='hidden' name = 'selectedBookId' value = " + bCode + ">"
                    + "<input type='hidden' name='qty_" + bCode + "' value='1'/>"
                    + (cartItemQty == 0
                            ? "<input type='submit' class=\"btn btn-primary\" name='addToCart' value='Add To Cart'/></form>"
                            : "<form method='post' action='cart'>"
                                    + "<button type='submit' name='removeFromCart' class=\"glyphicon glyphicon-minus btn btn-danger\"></button> "
                                    + "<input type='hidden' name='selectedBookId' value='" + bCode + "'/>"
                                    + cartItemQty
                                    + " <button type='submit' name='addToCart' class=\"glyphicon glyphicon-plus btn btn-success\"></button></form>")
                    + "";
        } else {
            // If available Quantity is zero, show out of stock button
            button = "<p class=\"btn btn-danger\">Out Of Stock</p>\r\n";
        }

        return prepareCard(book, bCode, bQty, button);
    }

    private String prepareCard(Book book, String bCode, int bQty, String button){
        // Bootstrap card to show the book data
        return "<div class=\"card\">\r\n"
                + "                <div class=\"row card-body\">\r\n"
                + "                    <img class=\"col-sm-6\" src=\"logo.png\" alt=\"Card image cap\">\r\n"
                + "                    <div class=\"col-sm-6\">\r\n"
                + "                        <h5 class=\"card-title text-success\">" + book.getName() + "</h5>\r\n"
                + "                        <p class=\"card-text\">\r\n"
                + "                        Author: <span class=\"text-primary\" style=\"font-weight:bold;\"> "
                + book.getAuthor()
                + "</span><br>\r\n"
                + "                        </p>\r\n"
                + "                        \r\n"
                + "                    </div>\r\n"
                + "                </div>\r\n"
                + "                <div class=\"row card-body\">\r\n"
                + "                    <div class=\"col-sm-6\">\r\n"
                + "                        <p class=\"card-text\">\r\n"
                + "                        <span>Id: " + bCode + "</span>\r\n"
                + (bQty < 20 ? "<br><span class=\"text-danger\">Only " + bQty + " items left</span>\r\n"
                        : "<br><span class=\"text-success\">Trending</span>\r\n")
                + "                        </p>\r\n"
                + "                    </div>\r\n"
                + "                    <div class=\"col-sm-6\">\r\n"
                + "                        <p class=\"card-text\">\r\n"
                + "                        Price: <span style=\"font-weight:bold; color:green\"> &#8377; "
                + book.getPrice()
                + " </span>\r\n"
                + "                        </p>\r\n"
                + button
                + "                    </div>\r\n"
                + "                </div>\r\n"
                + "            </div>";
    }
}
