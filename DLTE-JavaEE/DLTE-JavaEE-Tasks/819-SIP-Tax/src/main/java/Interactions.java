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

        String requestAnnualIncome = req.getParameter("requestAnnualIncome");

        if (requestPrinciple!=null && requestInterestRate!=null && requestPeriod!=null) {
            int principle = Integer.parseInt(requestPrinciple);
            int interestRate = Integer.parseInt(requestInterestRate);
            int period = Integer.parseInt(requestPeriod);

            resp.setContentType("application/json");
            Gson gson = new Gson();
//            resp.setStatus(HttpServletResponse.SC_OK);
            String result = sipCalculator(principle, interestRate, period);
            resp.getWriter().println(result);
        }
        else {
            double annualIncome = Double.parseDouble(requestAnnualIncome);
            double taxToBePaid=0.0;
            if (annualIncome >= 0 && annualIncome <= 250000) {
                taxToBePaid = annualIncome * 0.05;
            } else if (annualIncome > 250000 && annualIncome <= 500000) {
                taxToBePaid = annualIncome * 0.20;
            } else if (annualIncome > 500000 && annualIncome <= 750000) {
                taxToBePaid = annualIncome * 0.20;
            } else if (annualIncome > 750000 && annualIncome <= 100000) {
                taxToBePaid = annualIncome * 0.30;
            } else {
                taxToBePaid = annualIncome * 0.30;
            }
            resp.getWriter().println(taxToBePaid);
        }
    }
    public String sipCalculator(int principle, int interestRate, int period) {
        double rate=0.0, total=0.0, totalInvested=0.0, estimatedAmount=0.0;
        interestRate = interestRate/(12*100);

        total = (principle*((Math.pow(1+interestRate, period*12)-1)/interestRate)*(1+interestRate))/interestRate;
        totalInvested = principle * (12*period);
        estimatedAmount = total-totalInvested;

        return "Estimated Amount: "+estimatedAmount+"\nTotal: "+total;
    }
}
