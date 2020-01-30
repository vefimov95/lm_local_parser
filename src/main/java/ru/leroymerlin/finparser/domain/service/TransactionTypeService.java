package ru.leroymerlin.finparser.domain.service;

import ru.leroymerlin.finparser.domain.models.TransactionType;
import ru.leroymerlin.finparser.upload.xml.models.TransactionTypeXml;

public interface TransactionTypeService {

    TransactionType getByCode(String code);

    TransactionType save(TransactionTypeXml transactionTypeXml);

    TransactionType findOrSave(TransactionTypeXml transactionTypeXml);
}
