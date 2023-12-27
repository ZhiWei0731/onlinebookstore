package writer.extend;

import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.User;
import writer.Writer;

public class CustomerRegisterWriter extends Writer {
    public CustomerRegisterWriter(HttpServletResponse res) {
		super(res);
	}

    @Override
    public void writeSellerLog(){
        return;
    }

    @Override
    public void writeCustomerLog(){
        String success = "<table class=\"tab\"><tr><td>User Registered Successfully</td></tr></table>";
        getPrintWriter().println(success);
    }

    @Override
    public void writeNotLog(){
        getPrintWriter().println("<table class=\"tab\"><tr><td>" + "FAILURE" + "</td></tr></table>");
        getPrintWriter().println("Sorry for interruption! Try again");
    }

    @Override
    public void writeSellerLog(User user){
        return;
    }

    @Override
    public void writeCustomerLog(User user){
        return;
    }

    @Override
    public void writeNotLog(User user){
        return;
    }
}
