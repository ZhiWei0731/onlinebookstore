package writer.extend;

import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.User;
import writer.Writer;

public class LogutWriter extends Writer{
    public static String logutInfo = "<table class=\"tab\"><tr><td>Successfully logged out!</td></tr></table>";
    public static String notLog = "<table class=\"tab\"><tr><td>Please Login First to Continue!!</td></tr></table>";

    public LogutWriter(HttpServletResponse res) {
		super(res);
	}

    @Override
    public void writeSellerLog(){
        getPrintWriter().println(logutInfo);
    }

    @Override
    public void writeCustomerLog(){
        getPrintWriter().println(logutInfo);
    }

    @Override
    public void writeNotLog(){
        getPrintWriter().println(notLog);
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
