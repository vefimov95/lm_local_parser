package leroymerlin.domain.service.impl;

import leroymerlin.domain.models.*;
import leroymerlin.domain.service.TransformationService;
import leroymerlin.upload.xml.models.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Service(value = "domain")
class TransformationServiceImpl implements TransformationService {
    private static final String pattern = "YYYY-MM-DD";

    @Override
    public Client getClientFromXml(ClientXml clientXml) {
       if (clientXml != null) {
           return Client.builder()
                   .name(clientXml.getName())
                   .inn(clientXml.getTxId())
                   .build();
       } else {
           return null;
       }
    }

    @Override
    public Bank getBankFromXml(BankXml bankXml) {
        if (bankXml != null) {
            return Bank.builder()
                    .name(bankXml.getName())
                    .swiftNumber(bankXml.getSwiftNumber())
                    .country(bankXml.getCountry())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public Account getAccountFromXml(AccountXml accountXml, Client client, Bank bank) {
        if (accountXml != null && client != null && bank != null) {
            return Account.builder()
                    .number(accountXml.getNumber())
                    .client(client)
                    .bank(bank)
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public Transaction getTransactionFromXml(TransactionDetailsXml transactionDetailsXml,
                                             Account accountSender,
                                             Account accountRecipient,
                                             TransactionType transactionType) {
        if (transactionDetailsXml != null && transactionType != null
                && accountSender != null && accountRecipient != null) {
            return Transaction.builder()
                    .amount(transactionDetailsXml.getAmount())
                    .currency(transactionDetailsXml.getAmountCurrency())
                    .dateVal(transformationDateFromString(transactionDetailsXml.getDateVal()))
                    .dateBook(transformationDateFromString(transactionDetailsXml.getDateBook()))
                    .status(transactionDetailsXml.getStatus())
                    .purpose(transactionDetailsXml.getPurpose())
                    .reference(transactionDetailsXml.getReference())
                    .accountSender(accountSender)
                    .accountRecipient(accountRecipient)
                    .typeTrans(transactionType)
                    .build();
        } else {
            return null;
        }
    }

    private Date transformationDateFromString (String date) {
        if (date != null) {
            try {
                return new SimpleDateFormat(pattern).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public TransactionType getTransactionTypeFromXml(TransactionTypeXml transactionTypeXml) {
        if (transactionTypeXml != null) {
            return TransactionType.builder()
                    .code(transactionTypeXml.getTypeCode())
                    .family(transactionTypeXml.getTypeFamilyCode())
                    .build();
        } else {
            return null;
        }
    }

}
