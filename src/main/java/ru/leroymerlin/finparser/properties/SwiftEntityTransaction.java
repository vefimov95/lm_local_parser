package ru.leroymerlin.finparser.properties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwiftEntityTransaction {
    private String root;
    private Map<String, String> entity;
}
