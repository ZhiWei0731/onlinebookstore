package writer;

import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.User;

public class AboutWriter extends Writer {
    public static String about = "<iframe src=\"https://flowcv.me/shashirajraja\" class=\"holds-the-iframe\"\r\n"
                + "        title=\"My Personal Website\" width=\"100%\" height=\"100%\"></iframe>";
    public static String notLog = "<table class=\"tab\"><tr><td>Please Login First to Continue!!</td></tr></table>";


    public AboutWriter(HttpServletResponse res) {
		super(res);
	}

    @Override
    public void writeSellerLog(){
        getPrintWriter().println(about);
    }

    @Override
    public void writeCustomerLog(){
        getPrintWriter().println(about);
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
