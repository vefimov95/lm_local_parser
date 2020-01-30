package ru.leroymerlin.finparser.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.leroymerlin.finparser.domain.exception.BankException;
import ru.leroymerlin.finparser.domain.models.TransactionType;
import ru.leroymerlin.finparser.domain.repository.TransactionTypeRepository;
import ru.leroymerlin.finparser.domain.service.TransactionTypeService;
import ru.leroymerlin.finparser.domain.service.TransformationService;
import ru.leroymerlin.finparser.upload.xml.models.TransactionTypeXml;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    @Autowired
    private TransactionTypeRepository typeRepository;

    @Autowired
    @Qualifier("domain")
    private TransformationService transformationService;

    @Override
    public TransactionType getByCode(String code) {
        if (code != null) {
            TransactionType transactionType = typeRepository.findByCode(code);
            if (transactionType != null) {
                return transactionType;
            }
        }
        return null;
    }

    @Override
    public TransactionType save(TransactionTypeXml transactionTypeXml) {
        if (transactionTypeXml.getTypeCode() != null) {
            if (!typeRepository.existsByCode(transactionTypeXml.getTypeCode())) {
                TransactionType transactionType = transformationService.getTransactionTypeFromXml(transactionTypeXml);
                transactionType = typeRepository.save(transactionType);
                return transactionType;
            } else {
                throw new BankException("Type of transaction with code " + transactionTypeXml.getTypeCode() + "exist in DB");
            }
        }
        throw new BankException("Code type of transaction for saving is not set");
    }

    @Override
    public TransactionType findOrSave(TransactionTypeXml transactionTypeXml) {
        if (transactionTypeXml != null) {
            TransactionType type = this.getByCode(transactionTypeXml.getTypeCode());
            if (type == null) {
                type = this.save(transactionTypeXml);
            }
            return type;
        }
        return null;
    }
}
