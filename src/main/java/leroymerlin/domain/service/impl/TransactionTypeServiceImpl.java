package leroymerlin.domain.service.impl;

import leroymerlin.domain.exception.BankException;
import leroymerlin.domain.models.TransactionType;
import leroymerlin.domain.repository.TransactionTypeRepository;
import leroymerlin.domain.service.TransactionTypeService;
import leroymerlin.domain.service.TransformationService;
import leroymerlin.upload.xml.models.TransactionTypeXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Service
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
//                transactionType =  typeRepository.save(transactionType);
                return transactionType;
            } else {
                throw new BankException("Type of transaction with code " + transactionTypeXml.getTypeCode() + "exist in DB");
            }
        } throw new BankException("Code type of transaction for saving is not set");
    }

    @Override
    public TransactionType findOrSave(TransactionTypeXml transactionTypeXml) {
        if (transactionTypeXml != null) {
            TransactionType type = this.getByCode(transactionTypeXml.getTypeCode());
            if (type == null) {
                type =this.save(transactionTypeXml);
            }
            return type;
        }
        return null;
    }
}
