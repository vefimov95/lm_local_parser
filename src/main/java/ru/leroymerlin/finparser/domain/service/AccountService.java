package ru.leroymerlin.finparser.domain.service;

import ru.leroymerlin.finparser.domain.models.Account;
import ru.leroymerlin.finparser.domain.models.Bank;
import ru.leroymerlin.finparser.domain.models.Client;
import ru.leroymerlin.finparser.upload.xml.models.AccountXml;

public interface AccountService {

    Account getByNumber(String number);

    Account save(AccountXml accountXml, Client client, Bank bank);

    Account findOrSave(AccountXml accountXml, Client client, Bank bank);
}
