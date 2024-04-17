package org.web.service.mybankdepositsweb.soap;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.deposits.Deposits;
import services.deposits.ListAllDepositsRequest;
import services.deposits.ListAllDepositsResponse;
import services.deposits.ServiceStatus;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ComponentScan("mybank.dao.mybankdeposits")
@Endpoint
public class DepositSoap {
    private final String url = "http://deposits.services";

    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    ResourceBundle messageBundle = ResourceBundle.getBundle("messages");
    Logger logger = LoggerFactory.getLogger(DepositSoap.class);

    @Autowired
//    public DepositService service;
    private DepositInterface depositInterface;

    @PayloadRoot(namespace = url, localPart = "listAllDepositsRequest")
    @ResponsePayload
    public ListAllDepositsResponse listDeposits(@RequestPayload ListAllDepositsRequest listAllDepositsRequest) {
        ListAllDepositsResponse listAllDepositsResponse = new ListAllDepositsResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        List<DepositsAvailable> daoDeposits = null;
        try {
            daoDeposits = depositInterface.listAllDeposits();
            List<Deposits> actualDeposits = new ArrayList<>();
            daoDeposits.forEach(each->{
                Deposits deposits = new Deposits();
                BeanUtils.copyProperties(each,deposits);
                actualDeposits.add(deposits);
            });
            listAllDepositsResponse.getDeposits().addAll(actualDeposits);
            serviceStatus.setMessage(messageBundle.getString("fetch.success"));
            logger.info(messageBundle.getString("fetch.success"));
            logger.info(String.valueOf(HttpServletResponse.SC_OK));
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            listAllDepositsResponse.setServiceStatus(serviceStatus);
        } catch (SQLSyntaxErrorException e) {
            logger.error(messageBundle.getString("internal.error"));
            serviceStatus.setMessage(messageBundle.getString("internal.error"));
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            listAllDepositsResponse.setServiceStatus(serviceStatus);
        } catch (DepositException exception) {
            logger.warn(messageBundle.getString("deposit.exception"));
            serviceStatus.setMessage(messageBundle.getString("deposit.exception"));
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            listAllDepositsResponse.setServiceStatus(serviceStatus);
        }
        finally {
            return listAllDepositsResponse;
        }
//        System.out.println(daoDeposits.toString());

//        Iterator<DepositsAvailable> iterator = fromDao.iterator();

//        while (iterator.hasNext()) {
//            Deposits currentDeposits = new Deposits();
//            BeanUtils.copyProperties(iterator.next(), currentDeposits);
//            actualDeposits.add(currentDeposits);
//        }

    }
}
