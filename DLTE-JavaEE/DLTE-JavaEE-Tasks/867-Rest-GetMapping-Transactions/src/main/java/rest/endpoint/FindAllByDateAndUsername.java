package rest.endpoint;

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
import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@WebServlet("/rest/date/*")
public class FindAllByDateAndUsername extends HttpServlet {
    private UserServices userServices;
    private ResourceBundle resourceBundle;
    private Logger logger;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userServices = new UserServices(storageTarget);
        resourceBundle=ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(FindAllByDateAndUsername.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
