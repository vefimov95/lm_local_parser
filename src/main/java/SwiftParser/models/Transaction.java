package SwiftParser.models;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transaction {

    private Double amount;

    private String amountCurrency;

    private String status;

    private String dateVal;

    private String dateBook;

    private String purpose;

    private String code;

    private String familyCode;

    private String subfamilyCode;

    private String clientSenderName;

    private String clientRecipientName;

    private String bankSenderName;

    private String bankSenderSwiftNumber;

    private String bankSenderCountry;

    private String bankRecipientName;

    private String bankRecipientSwiftNumber;

    private String bankRecipientCountry;

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", amountCurrency='" + amountCurrency + '\'' +
                ", status='" + status + '\'' +
                ", dateVal='" + dateVal + '\'' +
                ", dateBook='" + dateBook + '\'' +
                ", purpose='" + purpose + '\'' +
                ", code='" + code + '\'' +
                ", familyCode='" + familyCode + '\'' +
                ", subfamilyCode='" + subfamilyCode + '\'' +
                ", clientSenderName='" + clientSenderName + '\'' +
                ", clientRecipientName='" + clientRecipientName + '\'' +
                ", bankSenderName='" + bankSenderName + '\'' +
                ", bankSenderSwiftNumber='" + bankSenderSwiftNumber + '\'' +
                ", bankSenderCountry='" + bankSenderCountry + '\'' +
                ", bankRecipientName='" + bankRecipientName + '\'' +
                ", bankRecipientSwiftNumber='" + bankRecipientSwiftNumber + '\'' +
                ", bankRecipientCountry='" + bankRecipientCountry + '\'' +
                '}';
    }
}
