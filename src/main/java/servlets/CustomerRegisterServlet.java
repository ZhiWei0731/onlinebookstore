package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.constant.ResponseCode;
import com.bittercode.constant.db.UsersDBConstants;
import com.bittercode.model.User;
import com.bittercode.model.UserRole;
import com.bittercode.service.UserService;
import com.bittercode.service.impl.UserServiceImpl;

import writer.Writer;
import writer.CustomerRegisterWriter;

public class CustomerRegisterServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        Writer writer = new CustomerRegisterWriter(res);

        User user = new User(
            req.getParameter(UsersDBConstants.COLUMN_MAILID),
            req.getParameter(UsersDBConstants.COLUMN_PASSWORD),
            req.getParameter(UsersDBConstants.COLUMN_FIRSTNAME),
            req.getParameter(UsersDBConstants.COLUMN_LASTNAME),
            Long.parseLong(req.getParameter(UsersDBConstants.COLUMN_PHONE)),
            req.getParameter(UsersDBConstants.COLUMN_ADDRESS)
        );

        try {
            String respCode = userService.register(UserRole.CUSTOMER, user);
            System.out.println(respCode);
            if (ResponseCode.SUCCESS.name().equalsIgnoreCase(respCode)) {
                RequestDispatcher rd = req.getRequestDispatcher("CustomerLogin.html");
                rd.include(req, res);
                writer.write(UserRole.CUSTOMER);
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("CustomerRegister.html");
                rd.include(req, res);
                writer.write(UserRole.NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}