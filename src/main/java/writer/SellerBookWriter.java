package writer;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.constant.BookStoreConstants;
import com.bittercode.model.Book;

public abstract class SellerBookWriter {
    private HttpServletRequest req;
    private HttpServletResponse res;
    private PrintWriter pw;

    public SellerBookWriter(HttpServletRequest req, HttpServletResponse res) {
        this.req = req;
        this.res = res;
        init();
    }

    private void init() {
        try {
        res.setContentType(BookStoreConstants.CONTENT_TYPE_TEXT_HTML);
        this.pw = res.getWriter();
        this.writeTopContent();
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

    public void write(List<Book> books){
        writeTopContent();
        writeBookContent(books);
        writeBottomContent();
    }

    public abstract void writeTopContent();
    public abstract void writeBookContent();
    public abstract void writeBookContent(List<Book> books);
    public abstract void writeBottomContent();
    public abstract void writeFaliOperation();
    
}
