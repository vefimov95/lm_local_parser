package leroymerlin.upload.xml.service;

import leroymerlin.upload.xml.models.TransactionXml;

import java.util.Map;

public interface TransformationService {
    TransactionXml getTransactionEntityFromMap(Map<String, String> map);
}
