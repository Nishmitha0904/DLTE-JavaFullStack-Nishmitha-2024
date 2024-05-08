import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/types")
public class PrimitiveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Context context = new InitialContext();
            Integer myAge = (Integer) context.lookup("java:/MyAge");
            String myRole = (String) context.lookup("java:/MyRole");
            resp.getWriter().println(myAge);
            resp.getWriter().println(myRole);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }
}
