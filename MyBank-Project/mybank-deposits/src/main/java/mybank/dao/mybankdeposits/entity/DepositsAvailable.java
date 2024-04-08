package mybank.dao.mybankdeposits.entity;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class DepositsAvailable {

    @NotNull(message = "{deposit.id.null}")
    private long depositId;
    @NotNull(message = "{deposit.name.null}")
    private String depositName;
    @NotNull(message = "{deposit.roi.null}")
    @Digits(integer = 2, fraction = 2, message = "{deposit.roi}")
    private double depositRoi;
    @NotNull(message = "{deposit.type.null}")
    private String depositType;
    @NotNull(message = "{deposit.desc.null}")
    private String depositDescription;

    public DepositsAvailable() {
    }

    public DepositsAvailable(long depositId, String depositName, double depositRoi, String depositType, String depositDescription) {
        this.depositId = depositId;
        this.depositName = depositName;
        this.depositRoi = depositRoi;
        this.depositType = depositType;
        this.depositDescription = depositDescription;
    }

    public long getDepositId() {
        return depositId;
    }

    public void setDepositId(long depositId) {
        this.depositId = depositId;
    }

    public String getDepositName() {
        return depositName;
    }

    public void setDepositName(String depositName) {
        this.depositName = depositName;
    }

    public double getDepositRoi() {
        return depositRoi;
    }

    public void setDepositRoi(double depositRoi) {
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
