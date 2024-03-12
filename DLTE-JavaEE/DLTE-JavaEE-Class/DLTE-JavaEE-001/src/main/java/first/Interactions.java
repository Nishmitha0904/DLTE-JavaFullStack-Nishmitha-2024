package first;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BasicInteractions", value="/second/*")
public class Interactions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //In url everything is considered as a string
        int principle = Integer.parseInt(req.getParameter("principle"));
        int tenure = Integer.parseInt(req.getParameter("tenure"));
        double totalRepayment=0.0, EMI=0.0;
        if (principle>=100000 && principle<=300000) {
            totalRepayment=principle+(principle*0.24);
        } else if (principle>300000 && principle<=500000) {
            totalRepayment=principle+(principle*0.25);
        } else {
            totalRepayment=principle+(principle*0.29);
        }
        EMI=totalRepayment/tenure;

        resp.setContentType("application/json");
        //Converting to json using gson
        Gson gson = new Gson();
        String message = gson.toJson(EMI);
        //sending response in the form of json
        resp.getWriter().println(message);
        //setting status
        resp.setStatus(HttpServletResponse.SC_OK);
        //not json
        resp.getWriter().println(EMI);

    }
}
