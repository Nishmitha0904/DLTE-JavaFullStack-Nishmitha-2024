package mybank.dao.mybankdeposits.entity;

public class DepositsAvailable {

    private Long depositId;
    private String depositName;
    private Double depositRoi;
    private String depositType;
    private String depositDescription;

    public DepositsAvailable() {
    }

    public DepositsAvailable(Long depositId, String depositName, Double depositRoi, String depositType, String depositDescription) {
        this.depositId = depositId;
        this.depositName = depositName;
        this.depositRoi = depositRoi;
        this.depositType = depositType;
        this.depositDescription = depositDescription;
    }

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public String getDepositName() {
        return depositName;
    }

    public void setDepositName(String depositName) {
        this.depositName = depositName;
    }

    public Double getDepositRoi() {
        return depositRoi;
    }

    public void setDepositRoi(Double depositRoi) {
        this.depositRoi = depositRoi;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public String getDepositDescription() {
        return depositDescription;
    }

    public void setDepositDescription(String depositDescription) {
        this.depositDescription = depositDescription;
    }

}
