package service.soap.web;

import javax.xml.ws.Endpoint;

public class SoapEndpoints {
    private static String url = "http://localhost:1234/transact";

    public static void main(String[] args) {
        UserSoap userSoap = new UserSoap();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url, userSoap);
    }
}
