package ru.leroymerlin.finparser.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.leroymerlin.finparser.upload.xml.service.UploadXml;

@RestController
@RequestMapping("/uploaderXml")
public class XmlController {

    @Autowired
    private UploadXml uploadXml;

    @PostMapping
    public ResponseEntity uploadXml(@RequestBody MultipartFile file) {
        if (file != null) {
            uploadXml.load(file);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
