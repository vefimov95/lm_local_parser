package ru.leroymerlin.finparser.upload.xml.service;

import ru.leroymerlin.finparser.upload.xml.models.TransactionXml;

import java.util.Map;

public interface TransformationXmlService {

    TransactionXml getTransactionEntityFromMap(Map<String, String> map);
}
