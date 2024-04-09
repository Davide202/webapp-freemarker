package spring.mvc.webapp.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DatiExcell {

    private List<RigaExcellComuni> rigaExcellComuniList = new ArrayList<>();

    public void addRiga(RigaExcellComuni r){
        this.rigaExcellComuniList.add(r);
    }
}
