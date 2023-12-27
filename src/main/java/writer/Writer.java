package writer;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.constant.BookStoreConstants;
import com.bittercode.model.User;
import com.bittercode.model.UserRole;

public abstract class Writer {
    private HttpServletResponse res;
    private PrintWriter pw;

    public Writer(HttpServletResponse res) {
        this.res = res;
        init();
    }

    private void init() {
        try {
        res.setContentType(BookStoreConstants.CONTENT_TYPE_TEXT_HTML);
        this.pw = res.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PrintWriter getPrintWriter() {
        return this.pw;
    }

    public void setActiveTab(String activeTab) {
        pw.println("<script>document.getElementById(activeTab).classList.remove(\"active\");activeTab=" + activeTab
            + "</script>");
        pw.println("<script>document.getElementById('" + activeTab + "').classList.add(\"active\");</script>");
    }

    public void write(UserRole userRole){
        switch(userRole.name()){
            case "SELLER":
                writeSellerLog();
                break;
            case "CUSTOMER":
                writeCustomerLog();
                break;
            case "NULL":
                writeNotLog();
                break;
            default:
                break;
        }
    }

    public void write(UserRole userRole, User user){
        switch(userRole.name()){
            case "SELLER":
                writeSellerLog(user);
                break;
            case "CUSTOMER":
                writeCustomerLog(user);
                break;
            case "NULL":
                writeNotLog(user);
                break;
            default:
                break;
        }
    }

    public abstract void writeCustomerLog();
    public abstract void writeSellerLog();
    public abstract void writeNotLog();
    public abstract void writeCustomerLog(User user);
    public abstract void writeSellerLog(User user);
    public abstract void writeNotLog(User user);
}
