package first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Adding annotation @WebServlet -> name usually same as classname but can be different; value is for url
//@WebServlet(name = "MyFirstServlet", value="/first/api/")
//Only value also can be mentioned to call Servlet


@WebServlet(value="/first/api/*")
public class MyFirstServlet extends HttpServlet {
    Logger logger;

    //Destroy executed as soon as
    @Override
    public void destroy() {
        logger.info("Servlet's destroy has executed");
    }

    @Override
    public void init() throws ServletException {
        logger = LoggerFactory.getLogger(MyFirstServlet.class);
        logger.info("Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Servlet's Get has executed");
        logger.info("Received "+req.getParameter("lumpSum"));
        String path = req.getPathInfo();
        if (path==null || path.equals("/"))
            logger.info("Servlet's GET has executed");
        else {
//            logger.info("Recieved lumpsum is "+req.getParameter("lumpSum"));
            //String data =
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Servlet's Post has executed");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Servlet's Put has executed");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        logger.info("Servlet's Delete has executed");
    }
}
