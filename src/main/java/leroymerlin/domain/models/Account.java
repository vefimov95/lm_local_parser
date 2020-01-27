package leroymerlin.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "account")
public class Account {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="id")
    private Long id;

//    @Column(name = "name")
    private String number;

//    @ManyToOne(targetEntity = Client.class)
//    @JoinColumn(name = "client_id")
    private Client client;

//    @ManyToOne(targetEntity = Bank.class)
//    @JoinColumn(name = "bank_id")
    private Bank bank;
}


