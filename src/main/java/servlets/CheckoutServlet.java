package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.UserRole;
import com.bittercode.util.StoreUtil;

import writer.BookWriter;
import writer.extend.CheckoutWriter;

public class CheckoutServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        BookWriter writer = new CheckoutWriter(req, res);

        if (!StoreUtil.isLoggedIn(UserRole.CUSTOMER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("CustomerLogin.html");
            rd.include(req, res);
            writer.writeNotLog();
            return;
        }

        RequestDispatcher rd = req.getRequestDispatcher("payment.html");
        rd.include(req, res);
        writer.setActiveTab("cart");
        writer.write(); 
    }

}
