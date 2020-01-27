package leroymerlin.upload.xml.service.impl;

import leroymerlin.upload.xml.models.*;
import leroymerlin.upload.xml.service.TransformationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransformationServiceImpl implements TransformationService {

    @Override
    public TransactionXml getTransactionEntityFromMap(Map<String, String> map) {
        BankXml build = BankXml.builder()
                .name(map.get("bankRecipientName"))
                .swiftNumber(map.get("bankRecipientSwiftNumber"))
                .country(map.get("bankRecipientCountry"))
                .build();
        return TransactionXml.builder()
                .transactionDetailsXml(TransactionDetailsXml.builder()
                                        .amount(Double.valueOf(map.get("amount")))
                                        .amountCurrency(map.get("amountCurrency"))
                                        .status(map.get("status"))
                                        .dateVal(map.get("dateVal"))
                                        .dateBook(map.get("dateBook"))
                                        .purpose(map.get("purpose"))
                                        .reference(map.get("reference")).build())
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
                        .swiftNumber(map.get("bankSenderSwiftNumber"))
                        .country(map.get("bankSenderCountry"))
                        .build())
                .bankRecipient(BankXml.builder()
                        .name(map.get("bankRecipientName"))
                        .swiftNumber(map.get("bankRecipientSwiftNumber"))
                        .country(map.get("bankRecipientCountry"))
                        .build())
                .accountSender(AccountXml.builder()
                        .number(map.get("accountSenderNumber")).build())
                .accountRecipient(AccountXml.builder()
                        .number(map.get("accountRecipientNumber")).build())
                .build();

    }
}
