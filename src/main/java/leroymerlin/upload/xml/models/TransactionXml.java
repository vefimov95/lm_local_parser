package leroymerlin.upload.xml.models;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionXml {
    private Long Id;
    private TransactionDetailsXml transactionDetailsXml;
    private TransactionTypeXml transactionTypeXml;
    private ClientXml clientSender;
    private BankXml bankSender;
    private AccountXml accountSender;
    private ClientXml clientRecipient;
    private BankXml bankRecipient;
    private AccountXml accountRecipient;
}
