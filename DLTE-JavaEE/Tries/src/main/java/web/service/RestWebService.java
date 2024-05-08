package web.service;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
class TransactionServlet extends HttpServlet {

    List<Transaction> transactions =
            Stream.of(new Transaction(new Date(2024, 02, 20),1000.0, "John", "Bills"),
            new Transaction(new Date(2024, 03, 15),5500.0, "Hazel", "Friend"),
            new Transaction(new Date(2024, 01, 25),35000.0,"Karan","Emergency"),
            new Transaction(new Date(2024, 02, 04),15000.0,"Sara","Family"),
            new Transaction(new Date(2023, 12, 22),7000.0,"Sara","Emergency")
    ).collect(Collectors.toList());



    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transactions</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transactions</h1>");


        for (Transaction transaction : transactions) {
            writer.println("<div>");
            writer.println("<p>Date: " + transaction.getDateOfTransaction() + "</p>");
            writer.println("<p>Amount: " + transaction.getAmountInTransaction() + "</p>");
            writer.println("<p>Name: " + transaction.getTransactionTo() + "</p>");
            writer.println("<p>Type: " + transaction.getRemarks() + "</p>");
            writer.println("</div>");
            writer.println("<br>");
        }

        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();

        Transaction transaction = gson.fromJson(req.getReader(),Transaction.class);
        transactions.add(transaction);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transaction Insertion</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transaction Added</h1>");
        writer.println("<p>" + transaction.getTransactionTo() + " has been added to the records.</p>");
        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}