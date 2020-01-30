package ru.leroymerlin.finparser.upload.xml.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadXml {

    void load(MultipartFile file);
}
