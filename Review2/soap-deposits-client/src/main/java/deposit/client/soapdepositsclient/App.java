package deposit.client.soapdepositsclient;

import deposit.client.soapdepositsclient.config.WebServiceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;
import soap.webservice.Deposits;
import soap.webservice.ListAllDepositsRequest;
import soap.webservice.ListAllDepositsResponse;

import java.util.List;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WebServiceConfig.class);
        context.refresh();

        WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);

        App app = new App();
        app.listAllDeposits(webServiceTemplate);
    }

    public void listAllDeposits(WebServiceTemplate webServiceTemplate) {
        ListAllDepositsRequest request = new ListAllDepositsRequest();
        ListAllDepositsResponse response = (ListAllDepositsResponse) webServiceTemplate.marshalSendAndReceive(request);
        List<Deposits> deposits = response.getDeposits();
        for (Deposits deposit: deposits) {
            System.out.println(deposit);
        }
        System.out.println(response.getServiceStatus());
    }
}
