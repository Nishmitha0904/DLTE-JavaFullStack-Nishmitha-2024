package rest.endpoint;

import com.google.gson.Gson;
import org.database.DatabaseTarget;
import org.database.StorageTarget;
import org.database.User;
import org.database.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rest/all")
public class findAllUsers extends HttpServlet {
    UserServices userServices;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userServices=new UserServices(storageTarget);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("/application/json");
        List<User> users = userServices.callFindAll();
        Gson gson = new Gson();
        String responseData = gson.toJson(users);
        resp.getWriter().println(responseData);
    }

}
