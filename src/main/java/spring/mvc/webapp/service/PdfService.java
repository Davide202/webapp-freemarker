package spring.mvc.webapp.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Service
@Log4j2
public class PdfService {

    private final Configuration cfg = config();
    public Configuration config()  {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        try {

            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            // Some other recommended settings:
            cfg.setIncompatibleImprovements(new Version(2, 3, 20));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setLocale(Locale.US);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            return cfg;
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }

    }

    public File getPdf(String templateName,Object input) throws IOException {

        log.info("START getPdf()");
        Path tempFilePath = getPath();
        File tempFile = tempFilePath.toFile();

        log.info("file {}", tempFile.getAbsolutePath());

        Writer fileWriter = new FileWriter(tempFile);

        try {
            Template template = cfg.getTemplate(templateName);
            template.process(input, fileWriter);

//            String html = Files.readString(tempFilePath);
            String html = new String(Files.readAllBytes(tempFilePath));

            HtmlConverter.convertToPdf(html,new PdfWriter(tempFile));

        }catch (Exception e){
            log.error(e);
        }finally {
            fileWriter.close();
        }

        return tempFile;
    }
    private Path getPath(){
        //        Path tempFilePath = Files.createTempFile("file-",".pdf");
//        Path tempFilePath = Path.of("file.pdf");
        Path tempFilePath = Paths.get("file.pdf");
        try {
            tempFilePath = Files.createFile(tempFilePath);
        } catch (java.nio.file.FileAlreadyExistsException e) {
            tempFilePath.toFile().delete();
            return getPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return tempFilePath;
    }
}
