package ru.leroymerlin.finparser.upload.xml.service.impl;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.leroymerlin.finparser.domain.service.TransactionService;
import ru.leroymerlin.finparser.properties.SwiftEntityTransaction;
import ru.leroymerlin.finparser.upload.xml.models.TransactionXml;
import ru.leroymerlin.finparser.upload.xml.service.TransformationXmlService;
import ru.leroymerlin.finparser.upload.xml.service.UploadXml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UploadXmlImpl implements UploadXml {

    @Autowired
    private TransformationXmlService transformationXmlService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SwiftEntityTransaction swiftEntityTransaction;

    @Override
    public void load(MultipartFile file) {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document documentXml = null;
        try {
            documentXml = saxBuilder.build(file.getInputStream());
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        processingDocumentXml(documentXml);
    }

    private void processingDocumentXml(Document documentXml) {

        Element rootElement = documentXml.getRootElement();
        List<Element> entityTransactions = getByName(swiftEntityTransaction.getRoot(), rootElement);

        Map<String, String> mapTransaction = new HashMap();
        List<TransactionXml> transactionXmlList = new ArrayList<>();
        for (Element e : entityTransactions) {
            mapTransaction.clear();
            for (Map.Entry<String, String> entry : swiftEntityTransaction.getEntity().entrySet()) {
                String value = entry.getValue();
                String valueTransaction = null;
                if (value.contains("!")) {
                    int endIndex = value.indexOf('!');
                    String nameKey = value.substring(0, endIndex);
                    String nameAttributes = value.substring(endIndex + 1);
                    List<Element> listValue = this.getByName(nameKey, e);
                    if (!listValue.isEmpty()) {
                        valueTransaction = listValue.get(0).getAttribute(nameAttributes).getValue();
                    }
                } else if (value.endsWith("+")) {
                    String valueForFind = value.replace("+", "");
                    valueTransaction = this.getAllValueByName(valueForFind, e);
                } else {
                    valueTransaction = this.getValueByName(value, e);
                }
                mapTransaction.put(entry.getKey(), valueTransaction);

            }
            transactionXmlList.add(transformationXmlService.getTransactionEntityFromMap(mapTransaction));
        }
        transactionXmlList = transactionService.saveAll(transactionXmlList);
    }


    private String getValueByName(String name, Element root) {
        List<Element> elements = this.getByName(name, root);
        if (!elements.isEmpty()) {
            return elements.get(0).getValue();
        }
        return null;
    }

    private String getAllValueByName(String name, Element root) {
        List<Element> elements = this.getByName(name, root);
        if (!elements.isEmpty()) {
            String result = elements.stream()
                    .map(n -> String.valueOf(n.getValue()))
                    .collect(Collectors.joining(", "));
            return result;
        }
        return null;
    }

    private List<Element> getByName(String name, Element root) {
        String[] names = name.split("/");
        List<Element> currentElements = null;
        for (String searchName : names) {
            if (currentElements == null) {
                currentElements = root.getChildren().stream().filter(c -> c.getName().equals(searchName)).collect(Collectors.toList());
            } else {
                List<Element> newCurrentElements = new ArrayList<>();
                for (Element e : currentElements) {
                    List<Element> collect = e.getChildren().stream().filter(c -> c.getName().equals(searchName)).collect(Collectors.toList());
                    if (!collect.isEmpty()) newCurrentElements.addAll(collect);
                }
                currentElements = newCurrentElements;
            }
        }
        return currentElements;
    }
}
