package leroymerlin.domain.service;

import leroymerlin.domain.models.TransactionType;
import leroymerlin.upload.xml.models.TransactionTypeXml;

public interface TransactionTypeService {
    TransactionType getByCode(String code);
    TransactionType save(TransactionTypeXml transactionTypeXml);
    TransactionType findOrSave(TransactionTypeXml transactionTypeXml);
}
