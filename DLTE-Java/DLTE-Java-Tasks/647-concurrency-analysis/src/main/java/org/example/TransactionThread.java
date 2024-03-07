package org.example;


public class TransactionThread {
    public static void main(String[] args) throws InterruptedException {
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis();
        Thread threadOne = new Thread(transactionAnalysis::leastAmount);
        Thread threadTwo = new Thread(transactionAnalysis::maximumAmount);
        Thread threadThree = new Thread(transactionAnalysis::numberOfTransaction);
        Thread threadFour = new Thread(transactionAnalysis);
        Thread threadFive = new Thread(transactionAnalysis);
        Thread threadSix = new Thread(transactionAnalysis);
        Thread threadSeven = new Thread(transactionAnalysis);
        Thread threadEight = new Thread(transactionAnalysis);
        Thread threadNine = new Thread(transactionAnalysis);
        Thread threadTen = new Thread(transactionAnalysis);

        threadOne.start();
        threadTwo.start();
        threadThree.start();threadThree.join();
        threadFour.start();
        threadFive.start();
        threadSix.start();
        threadSeven.start();
        threadEight.start();
        threadNine.start();
        threadTen.start();
    }

}
