package wire.di.springbootautowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HomeLoanFieldInjection {
    public static void main(String[] args) {

        //Perform autowire in the mode of field injection for HomeLoanImplementation
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("wire.di.springbootautowiring");
        context.refresh();
        MyBankService service = context.getBean(MyBankService.class);
        System.out.println(service.executeFindAll());
    }
}
