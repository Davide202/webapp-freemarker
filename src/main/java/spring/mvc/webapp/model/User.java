package spring.mvc.webapp.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true)
public class User extends UserDTO  implements Serializable  {

    private String regione ;
    private String provincia ;
    private String comune ;



}
