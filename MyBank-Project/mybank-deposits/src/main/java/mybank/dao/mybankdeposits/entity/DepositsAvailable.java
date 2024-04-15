package mybank.dao.mybankdeposits.entity;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class DepositsAvailable {

    @Digits(integer = 2, fraction = 0, message = "{deposit.id}")
    private Long depositId;
//    @NotNull(message = "{deposit.name.null}")
    private String depositName;
//    @NotNull(message = "{deposit.roi.null}")
    @Digits(integer = 2, fraction = 2, message = "{deposit.roi}")
    private Double depositRoi;
//    @NotNull(message = "{deposit.type.null}")
    private String depositType;
//    @NotNull(message = "{deposit.desc.null}")
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

    @Override
    public String toString() {
        return "DepositsAvailable{" +
                "depositId=" + depositId +
                ", depositName='" + depositName + '\'' +
                ", depositRoi=" + depositRoi +
                ", depositType='" + depositType + '\'' +
                ", depositDescription='" + depositDescription + '\'' +
                '}';
    }
}
