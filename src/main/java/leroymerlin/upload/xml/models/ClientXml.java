package leroymerlin.upload.xml.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientXml {
    private Long id;
    private String name;
    private String txId;
}
