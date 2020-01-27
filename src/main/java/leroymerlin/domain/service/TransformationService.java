package leroymerlin.domain.service;

import leroymerlin.domain.models.*;
import leroymerlin.upload.xml.models.*;

public interface TransformationService {
    Client getClientFromXml (ClientXml clientXml);
    Bank getBankFromXml (BankXml bankXml);
    Account getAccountFromXml (AccountXml accountXml, Client client, Bank bank);
    Transaction getTransactionFromXml(TransactionDetailsXml transactionDetailsXml,
                                      Account accountSender,
                                      Account accountRecipient,
                                      TransactionType transactionType);
    TransactionType getTransactionTypeFromXml (TransactionTypeXml transactionTypeXml);
}
