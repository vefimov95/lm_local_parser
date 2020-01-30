package ru.leroymerlin.finparser.domain.service;

import ru.leroymerlin.finparser.domain.models.Bank;
import ru.leroymerlin.finparser.upload.xml.models.BankXml;

public interface BankService {

    Bank getByName(String name);

    Bank getBySwiftNumber(String swiftNumber);

    Bank save(BankXml bankXml);

    Bank findOrSave(BankXml bankXml);
}
