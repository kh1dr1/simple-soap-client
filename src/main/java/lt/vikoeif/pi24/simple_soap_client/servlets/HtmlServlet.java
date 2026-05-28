package lt.vikoeif.pi24.simple_soap_client.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

public class HtmlServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1> Hello </h1>");
//        out.println("<img src=\"/images/img_girl.jpg\" " +
//                "alt=\"Girl in a jacket\" width=\"500\" height=\"600\">"
//        );
        out.println("</body></html>");
    }
}
