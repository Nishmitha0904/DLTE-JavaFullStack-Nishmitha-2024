import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/users")
public class ReadUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Connection connection=null;
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:/OracleDS");
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from mybank_users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(3));
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }
}
