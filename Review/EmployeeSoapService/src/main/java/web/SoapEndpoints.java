package web;

import web.EmployeeSoap;

import javax.xml.ws.Endpoint;

public class SoapEndpoints {
    private static String url = "http://localhost:1234/employee";

    public static void main(String[] args) {
        EmployeeSoap employeeSoap = new EmployeeSoap();
        System.out.println("Webservice hosted at "+url);
        Endpoint.publish(url, employeeSoap);
    }
}
