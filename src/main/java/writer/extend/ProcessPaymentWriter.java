package writer.extend;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bittercode.model.Book;
import com.bittercode.model.Cart;
import com.bittercode.service.CustomerBookService;
import com.bittercode.service.impl.CustomerBookServiceImpl;

import writer.BookWriter;

public class ProcessPaymentWriter extends BookWriter {
    HttpServletRequest req = getRequest();
    PrintWriter pw = getPrintWriter();
    CustomerBookService bookService = new CustomerBookServiceImpl();

    public ProcessPaymentWriter(HttpServletRequest req, HttpServletResponse res) {
        super(req, res);
    }

    public void writeTopContent(){
        pw.println("<div id='topmid' style='background-color:grey'>Your Orders</div>");
        pw.println("<div class=\"container\">\r\n"
                + "        <div class=\"card-columns\">");
    }

    @SuppressWarnings("unchecked")
    public void writeBookContent(){
        try {      
            HttpSession session = req.getSession();
            List<Cart> cartItems = null;
            if (session.getAttribute("cartItems") != null)
                cartItems = (List<Cart>) session.getAttribute("cartItems");
            for (Cart cart : cartItems) {
                Book book = cart.getBook();
                double bPrice = book.getPrice();
                String bCode = book.getBarcode();
                String bName = book.getName();
                String bAuthor = book.getAuthor();
                int availableQty = book.getQuantity();
                int qtToBuy = cart.getQuantity();
                availableQty = availableQty - qtToBuy;
                bookService.updateBookQtyById(bCode, availableQty);
                pw.println(this.addBookToCard(bCode, bName, bAuthor, bPrice, availableQty));
                session.removeAttribute("qty_" + bCode);
            }
            session.removeAttribute("amountToPay");
            session.removeAttribute("cartItems");
            session.removeAttribute("items");
            session.removeAttribute("selectedBookId");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void writeBottomContent(){
        pw.println("</div>\r\n"
                + "    </div>");
    }

    public void writeFaliOperation(){
        return;
    }

    public String addBookToCard(String bCode, String bName, String bAuthor, double bPrice, int bQty) {
        String button = "<a href=\"#\" class=\"btn btn-info\">Order Placed</a>\r\n";
        return "<div class=\"card\">\r\n"
                + "                <div class=\"row card-body\">\r\n"
                + "                    <img class=\"col-sm-6\" src=\"logo.png\" alt=\"Card image cap\">\r\n"
                + "                    <div class=\"col-sm-6\">\r\n"
                + "                        <h5 class=\"card-title text-success\">" + bName + "</h5>\r\n"
                + "                        <p class=\"card-text\">\r\n"
                + "                        Author: <span class=\"text-primary\" style=\"font-weight:bold;\"> " + bAuthor
                + "</span><br>\r\n"
                + "                        </p>\r\n"
                + "                        \r\n"
                + "                    </div>\r\n"
                + "                </div>\r\n"
                + "                <div class=\"row card-body\">\r\n"
                + "                    <div class=\"col-sm-6\">\r\n"
                + "                        <p class=\"card-text\">\r\n"
                + "                        <span style='color:blue;'>Order Id: ORD" + bCode + "TM </span>\r\n"
                + "                        <br><span class=\"text-danger\">Item Yet to be Delivered</span>\r\n"
                + "                        </p>\r\n"
                + "                    </div>\r\n"
                + "                    <div class=\"col-sm-6\">\r\n"
                + "                        <p class=\"card-text\">\r\n"
                + "                        Amout Paid: <span style=\"font-weight:bold; color:green\"> &#8377; " + bPrice
                + " </span>\r\n"
                + "                        </p>\r\n"
                + button
                + "                    </div>\r\n"
                + "                </div>\r\n"
                + "            </div>";
    }
}
