package employee.console.consoleapplication.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ResourceBundle;

public class App {
    Logger logger = LoggerFactory.getLogger(App.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();


    }
}
