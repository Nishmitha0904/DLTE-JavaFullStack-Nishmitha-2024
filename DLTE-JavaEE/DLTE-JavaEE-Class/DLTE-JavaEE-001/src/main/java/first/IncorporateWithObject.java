package first;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/third/obj/*")
public class IncorporateWithObject extends HttpServlet {

    List<Loan> myBankLoans = Stream.of(new Loan(123, 98000, new Date("3/30/2024"), "open", "Ankita"),
            new Loan(345, 60000, new Date("1/20/2024"), "closed", "Nishmitha"),
            new Loan(987, 35000, new Date("11/12/2024"), "open", "Eeksha"),
            new Loan(565, 78000, new Date("5/31/2024"), "open", "Eeksha")).collect(Collectors.toList());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Adding new Loan object
        Gson gson = new Gson();

        Loan loan = gson.fromJson(req.getReader(), Loan.class);
        myBankLoans.add(loan);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(loan.getLoanBorrower()+" has added to the records");
    }
}
