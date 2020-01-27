package leroymerlin.domain.service;

import leroymerlin.domain.models.Transaction;
import leroymerlin.upload.xml.models.TransactionXml;

import java.util.List;

public interface TransactionService {
    Transaction getByReference(String reference);
    TransactionXml save(TransactionXml transactionXml);
    List<TransactionXml> saveAll(List<TransactionXml> transactionXmlList);
}
