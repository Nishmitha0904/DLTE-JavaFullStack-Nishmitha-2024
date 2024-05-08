package initial;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class MySource {
    List<String> defaulters;

    //Initialization
    public MySource() {
        defaulters = Stream.of("Annapoorna", "Medhini", "Arundhathi", "Akash", "Prashanth", "Sanath").collect(Collectors.toList());
    }

    //Web Method to add defaulter to list
    @WebMethod
//    @WebResult(name = "String")
    public String addDefaulter(String name) {
        defaulters.add(name);
        return name+" has been added to defaulters record.";
    }
}
