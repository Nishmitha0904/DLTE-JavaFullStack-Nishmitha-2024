package app.mybank.remote;

import app.mybank.entity.CreditCard;

import java.util.List;

public interface CreditCardRepository {

    void save(CreditCard creditCard);

    List<CreditCard> findAll();

    CreditCard findById(Long creditCardNumber);

    List<CreditCard> findAllByLimit(CreditCard cardLimit);
}
