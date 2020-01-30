package ru.leroymerlin.finparser.upload.xml.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.leroymerlin.finparser.properties.SwiftCurrency;
import ru.leroymerlin.finparser.upload.xml.models.*;
import ru.leroymerlin.finparser.upload.xml.service.TransformationXmlService;

import java.util.Map;

@Service
public class TransformationServiceXmlImpl implements TransformationXmlService {

    @Autowired
    private SwiftCurrency swiftCurrency;

    @Override
    public TransactionXml getTransactionEntityFromMap(Map<String, String> map) {
        return TransactionXml.builder()
                .transactionDetailsXml(TransactionDetailsXml.builder()
                        .amount(Double.valueOf(map.get("amount")))
                        .currency(map.get("currency"))
                        .number(map.get("number"))
                        .status(map.get("status"))
                        .dateVal(map.get("dateVal"))
                        .dateBook(map.get("dateBook"))
                        .purpose(map.get("purpose")).build())
                .transactionTypeXml(TransactionTypeXml.builder().typeCode(map.get("code"))
                        .typeFamilyCode(map.get("familyCode"))
                        .typeSubfamilyCode(map.get("subFamilyCode"))
                        .build())
                .clientSender(ClientXml.builder()
                        .name(map.get("clientSenderName"))
                        .txId(map.get("clientSenderId"))
                        .build())
                .clientRecipient(ClientXml.builder()
                        .name(map.get("clientRecipientName"))
                        .txId(map.get("clientRecipientId"))
                        .build())
                .bankSender(BankXml.builder()
                        .name(map.get("bankSenderName"))
                        .swiftNumber(getByAnyKey(map, "bankSenderSwiftNumber"))
                        .country(map.get("bankSenderCountry"))
                        .build())
                .bankRecipient(BankXml.builder()
                        .name(map.get("bankRecipientName"))
                        .swiftNumber(getByAnyKey(map, "bankRecipientSwiftNumber"))
                        .country(map.get("bankRecipientCountry"))
                        .build())
                .accountSender(AccountXml.builder()
                        .number(map.get("accountSenderNumber")).build())
                .accountRecipient(AccountXml.builder()
                        .number(map.get("accountRecipientNumber")).build())
                .build();

    }

    private String getByAnyKey(Map<String, String> map, String startName) {
        for (String sc : swiftCurrency.getCurrency()) {
            if (map.get(startName + "." + sc) != null) {
                return map.get(startName + "." + sc);
            }
        }
        return null;
    }
}
