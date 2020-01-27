package leroymerlin;

import leroymerlin.upload.xml.service.UploadXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    @Autowired
    private UploadXml uploadXml;

    private static final String fileSource = "src/main/resources/ABCDRUMM_camt.053.001.02.RU2018.01(FCY).xml";
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);



    }
}
