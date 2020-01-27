package leroymerlin.upload.xml.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTypeXml {
    private Long id;
    private String typeCode;
    private String typeFamilyCode;
    private String typeSubfamilyCode;
}
