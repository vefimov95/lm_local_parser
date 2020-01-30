package ru.leroymerlin.finparser.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.leroymerlin.finparser.domain.exception.ClientException;
import ru.leroymerlin.finparser.domain.models.Client;
import ru.leroymerlin.finparser.domain.repository.ClientRepository;
import ru.leroymerlin.finparser.domain.service.ClientService;
import ru.leroymerlin.finparser.domain.service.TransformationService;
import ru.leroymerlin.finparser.upload.xml.models.ClientXml;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    @Qualifier("domain")
    private TransformationService transformationService;

    @Override
    public Client getByInn(String inn) {
        if (inn != null) {
            Client client = clientRepository.findByInn(inn);
            if (client != null) {
                return client;
            }
        }
        return null;
    }

    @Override
    public Client getByName(String name) {
        if (name != null) {
            Client client = clientRepository.findByName(name);
            if (client != null) {
                return client;
            }
        }
        return null;
    }

    @Override
    public Client save(ClientXml clientXml) {
        if (clientXml.getName() != null) {
            if (!clientRepository.existsByName(clientXml.getName())) {
                Client client = transformationService.getClientFromXml(clientXml);
                client = clientRepository.save(client);
                return client;
            } else {
                throw new ClientException("Client with inn " + clientXml.getTxId() + "exist in DB");
            }
        }
        throw new ClientException("Inn client for saving is not set");
    }

    @Override
    public Client findOrSave(ClientXml clientXml) {
        if (clientXml != null) {
            Client client = this.getByName(clientXml.getName());
            if (client == null) {
                client = this.save(clientXml);
            }
            return client;
        }
        return null;
    }
}
