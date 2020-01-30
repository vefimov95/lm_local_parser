package ru.leroymerlin.finparser.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.leroymerlin.finparser.domain.exception.BankException;
import ru.leroymerlin.finparser.domain.models.Bank;
import ru.leroymerlin.finparser.domain.repository.BankRepository;
import ru.leroymerlin.finparser.domain.service.BankService;
import ru.leroymerlin.finparser.domain.service.TransformationService;
import ru.leroymerlin.finparser.upload.xml.models.BankXml;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    @Qualifier("domain")
    private TransformationService transformationService;

    @Override
    public Bank getByName(String name) {
        if (name != null) {
            Bank bank = bankRepository.findByName(name);
            if (bank != null) {
                return bank;
            }
        }
        return null;
    }

    @Override
    public Bank getBySwiftNumber(String swiftNumber) {
        if (swiftNumber != null) {
            Bank bank = bankRepository.findBySwiftNumber(swiftNumber);
            if (bank != null) {
                return bank;
            }
        }
        return null;
    }

    @Override
    public Bank save(BankXml bankXml) {
        if (bankXml.getSwiftNumber() != null) {
            if (!bankRepository.existsBySwiftNumber(bankXml.getSwiftNumber())) {
                Bank bank = transformationService.getBankFromXml(bankXml);
                bank = bankRepository.save(bank);
                return bank;
            } else {
                throw new BankException("Bank with swift code " + bankXml.getSwiftNumber() + "exist in DB");
            }
        }
        throw new BankException("Swift code bank for saving is not set");
    }

    @Override
    public Bank findOrSave(BankXml bankXml) {
        if (bankXml != null) {
            Bank bank = this.getBySwiftNumber(bankXml.getSwiftNumber());
            if (bank == null) {
                bank = this.save(bankXml);
            }
            return bank;
        }
        return null;
    }
}
