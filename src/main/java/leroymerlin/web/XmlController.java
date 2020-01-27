package leroymerlin.web;

import leroymerlin.upload.xml.service.UploadXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
