package leroymerlin.domain.service;

import leroymerlin.domain.models.Client;
import leroymerlin.upload.xml.models.ClientXml;

public interface ClientService {
    Client getByInn(String inn);
    Client getByName(String name);
    Client save(ClientXml clientXml);
    Client findOrSave(ClientXml clientXml);
}
