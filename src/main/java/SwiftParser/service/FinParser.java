package SwiftParser.service;

import SwiftParser.models.Transaction;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FinParser {

    private static final String fileProperties = "src/main/resources/swift.properties";
    private static final String fileSource = "src/main/resources/ABCDRUMM_camt.053.001.02.RU2018.01(FCY).xml";
    public static void main(String[] args) throws IOException, JDOMException {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream(fileProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> fieldsTransaction = getPropertiesToMap(property);

        File file = new File(fileSource);
        SAXBuilder saxBuilder = new SAXBuilder();
        Document build = saxBuilder.build(file);
        Element rootElement = build.getRootElement();
        List<Element> entityTransactions = getByName(fieldsTransaction.get("transaction"), rootElement);
        fieldsTransaction.remove("transaction");
        Map<String, String> valueTransaction = new HashMap();
        List<Transaction> transactions = new ArrayList<>();
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
            transactions.add(getTransactionEntity(valueTransaction));
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }

    }

    private static Transaction getTransactionEntity(Map<String, String> map) {
        return Transaction.builder()
                .amount(Double.valueOf(map.get("amount")))
                .amountCurrency(map.get("amountCurrency"))
                .status(map.get("status"))
                .dateVal(map.get("dateVal"))
                .dateBook(map.get("dateBook"))
                .purpose(map.get("purpose"))
                .code(map.get("code"))
                .familyCode(map.get("familyCode"))
                .subfamilyCode(map.get("subfamilyCode"))
                .clientSenderName(map.get("clientSenderName"))
                .clientRecipientName(map.get("clientRecipientName"))
                .bankSenderName(map.get("bankSenderName"))
                .bankSenderSwiftNumber(map.get("bankSenderSwiftNumber"))
                .bankSenderCountry(map.get("bankSenderCountry"))
                .bankRecipientName(map.get("bankRecipientName"))
                .bankRecipientSwiftNumber(map.get("bankRecipientSwiftNumber"))
                .bankRecipientCountry(map.get("bankRecipientCountry"))
                .build();
    }

    private static String getValueByName(String name, Element root) {
        List<Element> elements = getByName(name, root);
        if (!elements.isEmpty()) {
            return elements.get(0).getValue();
        }
        return null;
    }


    private static List<Element> getByName(String name, Element root) {
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

    private static Map<String, String> getPropertiesToMap(Properties property) {
        Enumeration<?> enumeration = property.propertyNames();
        Map<String, String> map = new HashMap();
        while (enumeration.hasMoreElements()) {

            String o = (String) enumeration.nextElement();
            map.put(o, property.getProperty(o));
        }
        return map;
    }

}
