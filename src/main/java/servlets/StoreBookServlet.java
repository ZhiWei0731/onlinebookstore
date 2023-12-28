package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.UserRole;
import com.bittercode.service.SellerBookService;
import com.bittercode.service.impl.SellerBookServiceImpl;
import com.bittercode.util.StoreUtil;

import writer.BookWriter;
import writer.extend.StoreBookWriter;

public class StoreBookServlet extends HttpServlet {

    // book service for database operations and logics
    SellerBookService bookService = new SellerBookServiceImpl();

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        BookWriter writer = new StoreBookWriter(req,res);

        // Check if the customer is logged in, or else return to login page
        if (!StoreUtil.isLoggedIn(UserRole.SELLER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("SellerLogin.html");
            rd.include(req, res);
            writer.writeNotLog();
            return;
        }

        // Add/Remove Item from the cart if requested
        // store the comma separated bookIds of cart in the session
        RequestDispatcher rd = req.getRequestDispatcher("SellerHome.html");
        rd.include(req, res);
        writer.setActiveTab("storebooks");
        writer.write();
    }
}
