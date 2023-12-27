package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.UserRole;
import com.bittercode.util.StoreUtil;

import writer.Writer;
import writer.AboutWriter;

//Http Servlet extended class for showing the about information
public class AboutServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        Writer writer = new AboutWriter(res);

        //If the store is logged in as customer or seller show about info
        if (StoreUtil.isLoggedIn(UserRole.CUSTOMER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("CustomerHome.html");
            rd.include(req, res);
            writer.setActiveTab("about");
            writer.write(UserRole.CUSTOMER);

        } else if (StoreUtil.isLoggedIn(UserRole.SELLER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("SellerHome.html");
            rd.include(req, res);
            writer.setActiveTab("about");
            writer.write(UserRole.SELLER);

        } else {
            //If the user is not logged in, ask to login first
            //Proceed only if logged in or forword to login page
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.include(req, res);
            writer.write(UserRole.NULL);
        }

    }

}
