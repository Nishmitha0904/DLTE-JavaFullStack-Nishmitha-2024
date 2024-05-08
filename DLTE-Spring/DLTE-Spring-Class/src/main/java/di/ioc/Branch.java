package di.ioc;

public class Branch {
    private  String branchName;
    private String ifscCode;
    private int branchId;
    private String bankName;
    private long branchContact;

    public Branch() {
    }

    public Branch(String branchName, String ifscCode, int branchId, String bankName, Long branchContact) {
        this.branchName = branchName;
        this.ifscCode = ifscCode;
        this.branchId = branchId;
        this.bankName = bankName;
        this.branchContact = branchContact;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getBranchContact() {
        return branchContact;
    }

    public void setBranchContact(Long branchContact) {
        this.branchContact = branchContact;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchName='" + branchName + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", branchId='" + branchId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", branchContact=" + branchContact +
                '}';
    }
}
