package spring.mvc.webapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RigaExcellComuni {

    private String regione;
    private String provincia;
    private String comune;

    public static RigaExcellComuni of(String... values){
        return new RigaExcellComuni(values[0],values[1],values[2]);
    }
}
