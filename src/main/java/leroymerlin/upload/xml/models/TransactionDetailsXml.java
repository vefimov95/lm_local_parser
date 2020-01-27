package leroymerlin.upload.xml.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailsXml {
    private Double amount;
    private String amountCurrency;
    private String status;
    private String dateVal;
    private String dateBook;
    private String purpose;
    private String reference;
}
