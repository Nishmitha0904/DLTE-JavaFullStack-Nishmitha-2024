package wire.di.springbootautowiring;

import java.io.Serializable;
import java.util.Date;

public class Loan {
     private Long loanNumber;
     private Double loanAmount;
     private Date loanDate;
     private String loanStatus;
     private String borrowerName;
     private Long borrowerContact;

     public Loan() {
     }

     public Loan(Long loanNumber, Double loanAmount, Date loanDate, String loanStatus, String borrowerName, Long borrowerContact) {
          this.loanNumber = loanNumber;
          this.loanAmount = loanAmount;
          this.loanDate = loanDate;
          this.loanStatus = loanStatus;
          this.borrowerName = borrowerName;
          this.borrowerContact = borrowerContact;
     }

     @Override
     public String toString() {
          return "Loan{" +
                  "loanNumber=" + loanNumber +
                  ", loanAmount=" + loanAmount +
                  ", loanDate=" + loanDate +
                  ", loanStatus='" + loanStatus + '\'' +
                  ", borrowerName='" + borrowerName + '\'' +
                  ", borrowerContact=" + borrowerContact +
                  '}';
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

     public Date getLoanDate() {
          return loanDate;
     }

     public void setLoanDate(Date loanDate) {
          this.loanDate = loanDate;
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
