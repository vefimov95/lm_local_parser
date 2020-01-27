package leroymerlin.domain.service;

import leroymerlin.domain.models.Bank;
import leroymerlin.upload.xml.models.BankXml;

public interface BankService {
    Bank getByName(String name);
    Bank getBySwiftNumber(String swiftNumber);
    Bank save(BankXml bankXml);
    Bank findOrSave(BankXml bankXml);
}
