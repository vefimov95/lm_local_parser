package leroymerlin.upload.xml.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountXml {
    private Long id;
    private String number;
}
