package ru.leroymerlin.finparser.upload.xml.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BankXml {
    private Long id;
    private String name;
    private String swiftNumber;
    private String country;
}
