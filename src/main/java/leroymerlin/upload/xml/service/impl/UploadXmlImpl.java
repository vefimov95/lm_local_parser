package leroymerlin.upload.xml.service.impl;

import leroymerlin.domain.service.TransactionService;
import leroymerlin.upload.xml.models.TransactionXml;
import leroymerlin.upload.xml.service.TransformationService;
import leroymerlin.upload.xml.service.UploadXml;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UploadXmlImpl implements UploadXml {

    @Autowired
    private TransformationService transformationService;

    @Autowired
    private TransactionService transactionService;

    @Value("${swift.file.properties}")
    private String fileProperties;

    @Value("#{'${swift.currency}'.split(',')}")
    private List<String> currency;

    @Override
    public void load(MultipartFile file) {
        Map<String, String> fieldsTransaction = getPropertiesToMap(file.getOriginalFilename());

        SAXBuilder saxBuilder = new SAXBuilder();
        Document build = null;
        try {
            build = saxBuilder.build(file.getInputStream());
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        };
        processingDocumentXml(build, fieldsTransaction);
    }

    private void processingDocumentXml(Document build, Map<String, String> fieldsTransaction) {

        Element rootElement = build.getRootElement();
        List<Element> entityTransactions = getByName(fieldsTransaction.get("root"), rootElement);
        fieldsTransaction.remove("root");

        Map<String, String> valueTransaction = new HashMap();
        List<TransactionXml> transactionXmlList = new ArrayList<>();
        for(Element e: entityTransactions) {
            valueTransaction.clear();
            for (Map.Entry<String, String> entry:fieldsTransaction.entrySet()) {
                String value = entry.getValue();
                if (value.startsWith("!")) {
                    String nameKey = value.substring(1, value.indexOf('/'));
                    String nameAttributes = value.substring(value.indexOf('/') + 1);
                    valueTransaction.put(entry.getKey(),
                            getByName(fieldsTransaction.get(nameKey), e).get(0).getAttribute(nameAttributes).getValue());
               } else {
                   valueTransaction.put(entry.getKey(), getValueByName(value, e));
               }
            }
            transactionXmlList.add(transformationService.getTransactionEntityFromMap(valueTransaction));
        }
        transactionXmlList = transactionService.saveAll(transactionXmlList);
        for (TransactionXml transactionXml : transactionXmlList) {
            System.out.println(transactionXml.toString());
        }
    }


    private  String getValueByName(String name, Element root) {
        List<Element> elements = getByName(name, root);
        if (!elements.isEmpty()) {
            return elements.get(0).getValue();
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

    private Map<String, String> getPropertiesToMap(String fileSource) {

        for (String c:currency) {
            if (fileSource.contains(c)) {
                Pattern replace = Pattern.compile("[(].{3}[)]");
                Matcher matcher2 = replace.matcher(fileProperties);
                fileProperties = matcher2.replaceAll(c);
            }
        }
        Properties property = new Properties();
        try {
            File file = ResourceUtils.getFile(fileProperties);
            property.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap();
        for (Map.Entry<Object, Object> entry:property.entrySet()) {
            map.put((String) entry.getKey(), (String) entry.getValue());
        }
        return map;
    }
}
