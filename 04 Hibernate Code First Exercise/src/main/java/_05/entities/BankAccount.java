package _05.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "ACCOUNT")
public class BankAccount extends BillingDetails {

    private static final String TYPE = "ACCOUNT";

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "SWIFT_code", nullable = false)
    private String swiftCode;

    public BankAccount() {
    }

    public BankAccount(String number, User user, String bankName, String swiftCode) {
        super(number, user, TYPE);

        this.bankName = bankName;
        this.swiftCode = swiftCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

}
