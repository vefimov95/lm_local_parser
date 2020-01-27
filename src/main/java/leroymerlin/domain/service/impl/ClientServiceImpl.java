package leroymerlin.domain.service.impl;

import leroymerlin.domain.exception.ClientException;
import leroymerlin.domain.models.Client;
import leroymerlin.domain.repository.ClientRepository;
import leroymerlin.domain.service.ClientService;
import leroymerlin.domain.service.TransformationService;
import leroymerlin.upload.xml.models.ClientXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Service
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
//                client = clientRepository.save(client);
                return client;
            } else {
                throw new ClientException("Client with inn " + clientXml.getTxId() + "exist in DB");
            }
        } throw new ClientException("Inn client for saving is not set");
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
