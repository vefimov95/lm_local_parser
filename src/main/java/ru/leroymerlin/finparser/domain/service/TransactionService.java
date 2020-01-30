package ru.leroymerlin.finparser.domain.service;

import ru.leroymerlin.finparser.upload.xml.models.TransactionXml;

import java.util.List;

public interface TransactionService {

    TransactionXml save(TransactionXml transactionXml);

    List<TransactionXml> saveAll(List<TransactionXml> transactionXmlList);
}
