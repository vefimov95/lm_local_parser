package leroymerlin.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private String status;

    @Column(name = "date_val")
    private Date dateVal;

    @Column(name = "date_book")
    private Date dateBook;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "reference")
    private String reference;

    @ManyToOne(targetEntity = TransactionType.class)
    @JoinColumn(name = "type_id")
    private TransactionType typeTrans;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "sender_id")
    private Account accountSender;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "recipient_id")
    private Account accountRecipient;

}
