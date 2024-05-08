package wire.di.springbootautowiring;

import java.util.ArrayList;

public class Loan {
     private Long loanNumber;
     private Double loanAmount;
     private String loanType;
     private String loanStatus;
     private String borrowerName;
     private Long borrowerContact;
     private ArrayList<Integer> numbers;

     public ArrayList getNumbers() {
          return numbers;
     }

     public void setNumbers(ArrayList numbers) {
          this.numbers = numbers;
     }

     public Loan() {
     }

     public Loan(Long loanNumber, Double loanAmount, String loanType, String loanStatus, String borrowerName, Long borrowerContact, ArrayList numbers) {
          this.loanNumber = loanNumber;
          this.loanAmount = loanAmount;
          this.loanType = loanType;
          this.loanStatus = loanStatus;
          this.borrowerName = borrowerName;
          this.borrowerContact = borrowerContact;
          this.numbers = numbers;
     }

     public Loan(Long loanNumber, Double loanAmount, String loanType, String loanStatus, String borrowerName, Long borrowerContact) {
          this.loanNumber = loanNumber;
          this.loanAmount = loanAmount;
          this.loanType = loanType;
          this.loanStatus = loanStatus;
          this.borrowerName = borrowerName;
          this.borrowerContact = borrowerContact;
     }

     @Override
     public String toString() {
          return "\nLoan{" +
                  "loanNumber=" + loanNumber +
                  ", loanAmount=" + loanAmount +
                  ", loanType='" + loanType + '\'' +
                  ", loanStatus='" + loanStatus + '\'' +
                  ", borrowerName='" + borrowerName + '\'' +
                  ", borrowerContact=" + borrowerContact +
                  "}\n";
     }

     public Long getLoanNumber() {
          return loanNumber;
     }

     public void setLoanNumber(Long loanNumber) {
          this.loanNumber = loanNumber;
     }

     public Double getLoanAmount() {
          return loanAmount;
     }

     public void setLoanAmount(Double loanAmount) {
          this.loanAmount = loanAmount;
     }

     public String getLoanType() {
          return loanType;
     }

     public void setLoanType(String loanType) {
          this.loanType = loanType;
     }

     public String getLoanStatus() {
          return loanStatus;
     }

     public void setLoanStatus(String loanStatus) {
          this.loanStatus = loanStatus;
     }

     public String getBorrowerName() {
          return borrowerName;
     }

     public void setBorrowerName(String borrowerName) {
          this.borrowerName = borrowerName;
     }

     public Long getBorrowerContact() {
          return borrowerContact;
     }

     public void setBorrowerContact(Long borrowerContact) {
          this.borrowerContact = borrowerContact;
     }
}
