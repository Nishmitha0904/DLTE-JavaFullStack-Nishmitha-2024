import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/interact/*")
public class Interactions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPrinciple = req.getParameter("requestPrinciple");
        String requestInterestRate = req.getParameter("requestInterestRate");
        String requestPeriod = req.getParameter("requestPeriod");
        String requestAmount = req.getParameter("requestAmount");

        if (requestPrinciple!=null && requestInterestRate!=null && requestPeriod!=null) {
            int principle = Integer.parseInt(requestPrinciple);
            int interestRate = Integer.parseInt(requestInterestRate);
            int period = Integer.parseInt(requestPeriod);

            double rate=0.0, total=0.0, totalInvested=0.0, estimatedAmount=0.0;
            interestRate = interestRate/(12*100);

            total = (principle*((Math.pow(1+interestRate, period*12)-1)/interestRate)*(1+interestRate))/interestRate;
            totalInvested = principle * (12*period);
            estimatedAmount = total-totalInvested
            resp.setContentType("application/json");
            Gson gson = new Gson();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Estimated Amount: "+estimatedAmount+"\nTotal: "+total);
        }
        else {

        }
    }
}
