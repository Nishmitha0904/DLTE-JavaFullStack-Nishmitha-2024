import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/transaction/obj/*")
public class Incorporate extends HttpServlet {

    List<Transaction> transactions = Stream.of(new Transaction(new Date(2024, 02, 20),1000.0, "John", "Bills"),
            new Transaction(new Date(2024, 03, 15),5500.0, "Hazel", "Friend"),
            new Transaction(new Date(2024, 01, 25),35000.0,"Karan","Emergency"),
            new Transaction(new Date(2024, 02, 04),15000.0,"Sara","Family"),
            new Transaction(new Date(2023, 12, 22),7000.0,"Sara","Emergency")).collect(Collectors.toList());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestMin = req.getParameter("requestMin");
        String requestMax = req.getParameter("requestMax");

        //If max and min values given
        if (requestMin!=null && requestMax!=null) {
            double min = Double.parseDouble(requestMin);
            double max = Double.parseDouble(requestMax);
            Gson gson = new Gson();
            resp.setContentType("application/json");
            for (Transaction transaction: transactions) {
                if (transaction.getAmountInTransaction()>min && transaction.getAmountInTransaction()<max) {
                    resp.getWriter().println(gson.toJson(transaction));
                }
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            Gson gson = new Gson();
            resp.setContentType("application/json");
            String json = gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(json);
        }

        Gson gson = new Gson();
        resp.setContentType("application/json");
        String json = gson.toJson(transactions);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Transaction transaction = gson.fromJson(req.getReader(), Transaction.class);
        transactions.add(transaction);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(transaction.getAmountInTransaction()+" has been transacted");
    }


}
