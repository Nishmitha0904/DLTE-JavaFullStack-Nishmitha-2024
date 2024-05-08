package initial;

import javax.xml.ws.Endpoint;

public class MyEndpoint {

    //Give any url you wish to, can give any port number
    private static String url="http://localhost:1000/source";

    public static void main(String[] args) {
        MySource mySource = new MySource();
        System.out.println("SOAP webservice running "+url);

        //Hosting the url
        Endpoint.publish(url, mySource);
    }
}
