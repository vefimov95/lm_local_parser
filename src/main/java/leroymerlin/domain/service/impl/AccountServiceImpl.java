package leroymerlin.domain.service.impl;

import leroymerlin.domain.exception.AccountException;
import leroymerlin.domain.models.Account;
import leroymerlin.domain.models.Bank;
import leroymerlin.domain.models.Client;
import leroymerlin.domain.repository.AccountRepository;
import leroymerlin.domain.service.AccountService;
import leroymerlin.domain.service.TransformationService;
import leroymerlin.upload.xml.models.AccountXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    @Qualifier("domain")
    private TransformationService transformationService;

    @Override
    public Account getByNumber(String number) {
        if (number != null) {
            Account account = accountRepository.findByNumber(number);
            if (account != null) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Account save(AccountXml accountXml, Client client, Bank bank) {
        if (accountXml.getNumber() != null && client != null && bank != null) {
            if (!accountRepository.existsByNumber(accountXml.getNumber())) {
                Account account = transformationService.getAccountFromXml(accountXml, client, bank);
                account = accountRepository.save(account);
                return account;
            } else {
                throw new AccountException("Account with number " + accountXml.getNumber() + "exist in DB");
            }
        } throw new AccountException("Number account, client or bank for saving account is not set");
    }

    @Override
    public Account findOrSave(AccountXml accountXml, Client client, Bank bank) {
        if (accountXml != null) {
            Account account = this.getByNumber(accountXml.getNumber());
            if (account == null) {
                account = this.save(accountXml, client, bank);
            }
            return account;
        }
        return null;
    }
}
