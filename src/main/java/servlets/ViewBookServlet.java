package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.UserRole;
import com.bittercode.service.CustomerBookService;
import com.bittercode.service.impl.CustomerBookServiceImpl;
import com.bittercode.util.StoreUtil;

import writer.CustomerBookWriter;
import writer.extend.ViewBookWriter;

public class ViewBookServlet extends HttpServlet {

    CustomerBookService bookService = new CustomerBookServiceImpl();

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        CustomerBookWriter writer = new ViewBookWriter(req, res);

        // Check if the customer is logged in, or else return to login page
        if (!StoreUtil.isLoggedIn(UserRole.CUSTOMER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("CustomerLogin.html");
            rd.include(req, res);
            writer.writeNotLog();
            return;
        }

        // Default Page to load data into
        RequestDispatcher rd = req.getRequestDispatcher("CustomerHome.html");
        rd.include(req, res);
        writer.setActiveTab("books");
        writer.write();
    }
}
