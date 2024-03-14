import com.google.gson.Gson;
import org.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@WebServlet("/rest/user/*")
public class FindTransactions extends HttpServlet {
    private UserServices userServices;
    private ResourceBundle resourceBundle;
    private Logger logger;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userServices = new UserServices(storageTarget);
        resourceBundle = ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(FindTransactions.class);
    }


    //Getting list of transactions based on username
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        resp.setContentType("application/json");
        try {
            List<Transaction> transactions = userServices.callFindByUsername(username);
            Gson gson = new Gson();
            String responseData = gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(responseData);
        }
        catch (UserException | MissingResourceException userException){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println(resourceBundle.getString("user.not.found"));
        }
    }
}
