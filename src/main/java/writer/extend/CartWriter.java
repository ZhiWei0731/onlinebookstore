package writer.extend;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bittercode.model.Book;
import com.bittercode.model.Cart;
import com.bittercode.service.CustomerBookService;
import com.bittercode.service.impl.CustomerBookServiceImpl;
import com.bittercode.util.StoreUtil;

import writer.CustomerBookWriter;

public class CartWriter extends CustomerBookWriter {
    HttpServletRequest req = getRequest();
    PrintWriter pw = getPrintWriter();
    CustomerBookService bookService = new CustomerBookServiceImpl();

    public CartWriter(HttpServletRequest req, HttpServletResponse res) {
        super(req, res);
    }

    public void writeTopContent(){
        pw.println("<div id='topmid' style='background-color:grey'>Shopping Cart</div>");
        pw.println("<table class=\"table table-hover\" style='background-color:white'>\r\n"
                + "  <thead>\r\n"
                + "    <tr style='background-color:black; color:white;'>\r\n"
                + "      <th scope=\"col\">BookId</th>\r\n"
                + "      <th scope=\"col\">Name</th>\r\n"
                + "      <th scope=\"col\">Author</th>\r\n"
                + "      <th scope=\"col\">Price/Item</th>\r\n"
                + "      <th scope=\"col\">Quantity</th>\r\n"
                + "      <th scope=\"col\">Amount</th>\r\n"
                + "    </tr>\r\n"
                + "  </thead>\r\n"
                + "  <tbody>\r\n");
    }

    public void writeBookContent(){
        try {
            // Add/Remove Item from the cart if requested
            // store the comma separated bookIds of cart in the session
            StoreUtil.updateCartItems(req);

            HttpSession session = req.getSession();
            String bookIds = "";
            if (session.getAttribute("items") != null)
                bookIds = (String) session.getAttribute("items");// read comma separated bookIds from session

            // Read the books from the database with the respective bookIds
            List<Book> books = bookService.getBooksByCommaSeperatedBookIds(bookIds);
            List<Cart> cartItems = new ArrayList<Cart>();

            if (books == null || books.size() == 0) {
                this.writeNoBookInCart();
            }

            double amountToPay = 0;
            for (Book book : books) {
                int qty = (int) session.getAttribute("qty_" + book.getBarcode());
                Cart cart = new Cart(book, qty);
                cartItems.add(cart);
                amountToPay += (qty * book.getPrice());
                pw.println(getRowData(cart));
            }

            // set cartItems and amountToPay in the session
            session.setAttribute("cartItems", cartItems);
            session.setAttribute("amountToPay", amountToPay);

            this.writePayInfo(amountToPay);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void writeBottomContent(){
        return;
    }

    public void writeFaliOperation(){
        return;
    }

    private void writeNoBookInCart(){
        pw.println("    <tr style='background-color:green'>\r\n"
                + "      <th scope=\"row\" colspan='6' style='color:yellow; text-align:center;'> No Items In the Cart </th>\r\n"
                + "    </tr>\r\n");
    }

    public void writePayInfo(double amountToPay){
        if (amountToPay > 0) {
            pw.println("    <tr style='background-color:green'>\r\n"
                    + "      <th scope=\"row\" colspan='5' style='color:yellow; text-align:center;'> Total Amount To Pay </th>\r\n"
                    + "      <td colspan='1' style='color:white; font-weight:bold'><span>&#8377;</span> "
                    + amountToPay
                    + "</td>\r\n"
                    + "    </tr>\r\n");
            }
        pw.println("  </tbody>\r\n"
                + "</table>");
        if (amountToPay > 0) {
            pw.println("<div style='text-align:right; margin-right:20px;'>\r\n"
                    + "<form action=\"checkout\" method=\"post\">"
                    + "<input type='submit' class=\"btn btn-primary\" name='pay' value='Proceed to Pay &#8377; "
                    + amountToPay + "'/></form>"
                    + "    </div>");
        }
    }

    private String getRowData(Cart cart) {
        Book book = cart.getBook();
        return "    <tr>\r\n"
                + "      <th scope=\"row\">" + book.getBarcode() + "</th>\r\n"
                + "      <td>" + book.getName() + "</td>\r\n"
                + "      <td>" + book.getAuthor() + "</td>\r\n"
                + "      <td><span>&#8377;</span> " + book.getPrice() + "</td>\r\n"
                + "      <td><form method='post' action='cart'><button type='submit' name='removeFromCart' class=\"glyphicon glyphicon-minus btn btn-danger\"></button> "
                + "<input type='hidden' name='selectedBookId' value='" + book.getBarcode() + "'/>"
                + cart.getQuantity()
                + " <button type='submit' name='addToCart' class=\"glyphicon glyphicon-plus btn btn-success\"></button></form></td>\r\n"
                + "      <td><span>&#8377;</span> " + (book.getPrice() * cart.getQuantity()) + "</td>\r\n"
                + "    </tr>\r\n";
    }

}
