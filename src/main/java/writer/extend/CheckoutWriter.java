package writer.extend;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import writer.CustomerBookWriter;

public class CheckoutWriter extends CustomerBookWriter {
    HttpServletRequest req = getRequest();
    PrintWriter pw = getPrintWriter();

    public CheckoutWriter(HttpServletRequest req, HttpServletResponse res) {
        super(req, res);
    }

    public void writeTopContent(){
        return;
    }

    public void writeBookContent(){
        return;
    }
    
    public void writeBottomContent(){
        try {
            pw.println("Total Amount<span class=\"price\" style=\"color: black\"><b>&#8377; "
                    + req.getSession().getAttribute("amountToPay")
                    + "</b></span>");

            pw.println("<input type=\"submit\" value=\"Pay & Place Order\" class=\"btn\">"
                    + "</form>");

            pw.println("</div>\r\n"
                    + " </div>\r\n"
                    + " </div>\r\n"
                    + " </div>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFaliOperation(){
        return;
    }
}
