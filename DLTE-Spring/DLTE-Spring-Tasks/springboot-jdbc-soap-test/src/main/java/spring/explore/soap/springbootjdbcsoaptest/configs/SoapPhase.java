package spring.explore.soap.springbootjdbcsoaptest.configs;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.transaction.*;
import spring.explore.soap.springbootjdbcsoaptest.dao.Transaction;
import spring.explore.soap.springbootjdbcsoaptest.dao.TransactionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SoapPhase {
    private final String url = "http://transaction.services";
    @Autowired
    private TransactionService transactionService;

    @PayloadRoot(namespace = url, localPart = "newTransactionRequest")
    @ResponsePayload
    public NewTransactionResponse addNewTransaction(@RequestPayload NewTransactionRequest newTransactionRequest) {
        NewTransactionResponse newTransactionResponse = new NewTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Date date=newTransactionRequest.getTransaction().getTransactionDate().toGregorianCalendar().getTime();
        services.transaction.Transaction actual = newTransactionRequest.getTransaction();
        Transaction daoTransaction = new Transaction();
        daoTransaction.setTransactionDate(date);
        BeanUtils.copyProperties(actual,daoTransaction);

        daoTransaction = transactionService.publishNewTransaction(daoTransaction);

        if (daoTransaction!=null) {
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(daoTransaction, actual);
            newTransactionResponse.setTransaction(actual);
            serviceStatus.setMessage(actual.getTransactionId() + " inserted");
        } else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(actual.getTransactionId() + " is not inserted");
        }
        newTransactionResponse.setServiceStatus(serviceStatus);
        return newTransactionResponse;
    }

    @PayloadRoot(namespace = url, localPart = "findBySenderRequest")
    @ResponsePayload
    public FindBySenderResponse findSender(@RequestPayload FindBySenderRequest findBySenderRequest) {
        FindBySenderResponse findBySenderResponse = new FindBySenderResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> returnTransactions = new ArrayList<>();

        List<Transaction> received = transactionService.findBySender(findBySenderRequest.getSender());

        Iterator<Transaction> iterator = received.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransactions = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransactions);
            returnTransactions.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions fetched");

        findBySenderResponse.setServiceStatus(serviceStatus);
        findBySenderResponse.getTransaction().addAll(returnTransactions);

        return findBySenderResponse;
    }

    @PayloadRoot(namespace = url, localPart = "findByReceiverRequest")
    @ResponsePayload
    public FindByReceiverResponse findReceiver(@RequestPayload FindByReceiverRequest findByReceiverRequest) {
        FindByReceiverResponse findByReceiverResponse = new FindByReceiverResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> returnTransactions = new ArrayList<>();

        List<Transaction> received = transactionService.findByReceiver(findByReceiverRequest.getReceiver());

        Iterator<Transaction> iterator = received.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransactions = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransactions);
            returnTransactions.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions fetched");

        findByReceiverResponse.setServiceStatus(serviceStatus);
        findByReceiverResponse.getTransaction().addAll(returnTransactions);

        return findByReceiverResponse;
    }

    @PayloadRoot(namespace = url, localPart = "findByAmountRequest")
    @ResponsePayload
    public FindByAmountResponse findAmount(@RequestPayload FindByAmountRequest findByAmountRequest) {
        FindByAmountResponse findByAmountResponse = new FindByAmountResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> returnTransactions = new ArrayList<>();

        List<Transaction> received = transactionService.findByAmount(findByAmountRequest.getMinAmount(), findByAmountRequest.getMaxAmount());

        Iterator<Transaction> iterator = received.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransactions = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransactions);
            returnTransactions.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions fetched");

        findByAmountResponse.setServiceStatus(serviceStatus);
        findByAmountResponse.getTransaction().addAll(returnTransactions);

        return findByAmountResponse;
    }

    @PayloadRoot(namespace = url, localPart = "updateRemarksRequest")
    @ResponsePayload
    public UpdateRemarksResponse updatingRemarks(@RequestPayload UpdateRemarksRequest updateRemarksRequest) {
        UpdateRemarksResponse updateRemarksResponse = new UpdateRemarksResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        services.transaction.Transaction transaction = new services.transaction.Transaction();


        Transaction daoTransaction = new Transaction();
        BeanUtils.copyProperties(updateRemarksRequest.getTransaction(), daoTransaction);

        daoTransaction = transactionService.updateRemarks(daoTransaction);

        if (daoTransaction!=null) {
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage(daoTransaction.getTransactionBy() + " has updated the remarks");
        } else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(daoTransaction.getTransactionBy() + " has not updated the remarks");
        }

        BeanUtils.copyProperties(daoTransaction,transaction);

        updateRemarksResponse.setServiceStatus(serviceStatus);
        updateRemarksResponse.setTransaction(transaction);

        return updateRemarksResponse;

    }

    @PayloadRoot(namespace = url, localPart = "deleteTransactionRequest")
    @ResponsePayload
    public DeleteTransactionResponse deletingTransaction(@RequestPayload DeleteTransactionRequest deleteTransactionRequest) {
        DeleteTransactionResponse response = new DeleteTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Date startDate=deleteTransactionRequest.getStartDate().toGregorianCalendar().getTime();
        Date endDate=deleteTransactionRequest.getEndDate().toGregorianCalendar().getTime();
        String fromDAO = transactionService.deleteTransactionBetweenDates(startDate, endDate);
        if (fromDAO.contains("Invalid")) {
            serviceStatus.setStatus("FAILURE");
        } else
            serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage(fromDAO);
        response.setServiceStatus(serviceStatus);
        return response;
    }

}
