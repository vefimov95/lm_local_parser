package leroymerlin.domain.service;

import leroymerlin.domain.models.Account;
import leroymerlin.domain.models.Bank;
import leroymerlin.domain.models.Client;
import leroymerlin.upload.xml.models.AccountXml;

public interface AccountService {
    Account getByNumber(String number);
    Account save(AccountXml accountXml, Client client, Bank bank);
    Account findOrSave(AccountXml accountXml, Client client, Bank bank);
}
