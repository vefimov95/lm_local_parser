package ru.leroymerlin.finparser.domain.service;

import ru.leroymerlin.finparser.domain.models.*;
import ru.leroymerlin.finparser.upload.xml.models.*;

public interface TransformationService {

    Client getClientFromXml(ClientXml clientXml);

    Bank getBankFromXml(BankXml bankXml);

    Account getAccountFromXml(AccountXml accountXml, Client client, Bank bank);

    Transaction getTransactionFromXml(TransactionDetailsXml transactionDetailsXml,
                                      Account accountSender,
                                      Account accountRecipient,
                                      TransactionType transactionType);

    TransactionType getTransactionTypeFromXml(TransactionTypeXml transactionTypeXml);
}
