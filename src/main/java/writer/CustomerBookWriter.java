package writer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.constant.BookStoreConstants;

public abstract class CustomerBookWriter {
    private HttpServletRequest req;
    private HttpServletResponse res;
    private PrintWriter pw;

    public CustomerBookWriter(HttpServletRequest req, HttpServletResponse res) {
        this.req = req;
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

    public HttpServletRequest getRequest() {
        return this.req;
    }

    public PrintWriter getPrintWriter() {
        return this.pw;
    }

    public void setActiveTab(String activeTab) {
        pw.println("<script>document.getElementById(activeTab).classList.remove(\"active\");activeTab=" + activeTab
            + "</script>");
        pw.println("<script>document.getElementById('" + activeTab + "').classList.add(\"active\");</script>");
    }

    public void writeNotLog(){
        pw.println("<table class=\"tab\"><tr><td>Please Login First to Continue!!</td></tr></table>");
    }
    
    public void write(){
        writeTopContent();
        writeBookContent();
        writeBottomContent();
    }

    public abstract void writeTopContent();
    public abstract void writeBookContent();
    public abstract void writeBottomContent();
    public abstract void writeFaliOperation();
}
