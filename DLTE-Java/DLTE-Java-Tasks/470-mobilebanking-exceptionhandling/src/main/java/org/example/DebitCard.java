package org.example;

import java.util.Scanner;

public class DebitCard {
    private Long cardNumber;
    private int cardPin;

    public DebitCard(Long cardNumber, int cardPin) {
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardPin() {
        return cardPin;
    }

    public void setCardPin(int cardPin) {
        this.cardPin = cardPin;
    }


}
