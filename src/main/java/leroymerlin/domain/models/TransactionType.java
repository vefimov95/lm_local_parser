package leroymerlin.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
//@Entity
//@Table(name = "transaction_type")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionType {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="id")
    private Long id;

//    @Column(name = "code")
    private String code;

//    @Column(name = "family")
    private String family;
}
