package ru.leroymerlin.finparser.domain.service;

import ru.leroymerlin.finparser.domain.models.Client;
import ru.leroymerlin.finparser.upload.xml.models.ClientXml;

public interface ClientService {

    Client getByInn(String inn);

    Client getByName(String name);

    Client save(ClientXml clientXml);

    Client findOrSave(ClientXml clientXml);
}
