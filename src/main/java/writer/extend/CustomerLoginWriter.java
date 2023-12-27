package writer.extend;

import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.User;
import writer.Writer;

public class CustomerLoginWriter extends Writer {
    public CustomerLoginWriter(HttpServletResponse res) {
		super(res);
	}

    @Override
    public void writeSellerLog(){
        return;
    }

    @Override
    public void writeCustomerLog(){
        return;
    }

    @Override
    public void writeNotLog(){
        return;
    }

    @Override
    public void writeSellerLog(User user){
        return;
    }

    @Override
    public void writeCustomerLog(User user){
        String logInfo = "    <div id=\"topmid\"><h1>Welcome to Online <br>Book Store</h1></div>\r\n"
                        + "    <br>\r\n"
                        + "    <table class=\"tab\">\r\n"
                        + "        <tr>\r\n"
                        + "            <td><p>Welcome "+user.getFirstName()+", Happy Learning !!</p></td>\r\n"
                        + "        </tr>\r\n"
                        + "    </table>";
        getPrintWriter().println(logInfo);
    }

    @Override
    public void writeNotLog(User user){
        String notLog = "<table class=\"tab\"><tr><td>Incorrect UserName or PassWord</td></tr></table>";
        getPrintWriter().println(notLog);
    }
}
