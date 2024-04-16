package spring.mvc.webapp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.mvc.webapp.service.PdfService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/download")
@Log4j2
public class PdfDownloadController {


    @Autowired
    private PdfService service;


    /**
     * <a href="http://localhost:8082/download/davidone">Test</a>
     * @param name
     * @return
     */
    @GetMapping("/{name}")
    public ResponseEntity<?> downloadPdf(
            @PathVariable(value = "name",required = false) String name
    ){
        File file = null;
        Map<String,String> input = new HashMap<>();
        if (name == null || name == "") name = "Davide";
        input.put("name",name);
        try {
            file = service.getPdf("test-template.ftl",input);
            InputStream inputStream = new FileInputStream(file);
            InputStreamResource resource =
                    new InputStreamResource(
                            new FileInputStream(file)
                    );
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                    .contentLength(file.length())
                    .body(resource);
        } catch (IOException e) {
            log.error(e);
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                    .body(e.getClass() + " : " + e.getMessage());
        }

    }
}
