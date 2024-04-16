package spring.mvc.webapp.controller;


import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.mvc.webapp.model.User;
import spring.mvc.webapp.model.UserDTO;
import spring.mvc.webapp.service.ExcellService;

import java.util.*;

@Controller
@Log4j2
public class RegistrationController {
    private final static ModelAndView REGISTRATION = new ModelAndView("registration");
    public static final String USER = "user";
    public static final String REGIONE = "regione";
    public static final String REGIONI = "regioni";
    public static final String PROVINCIE = "provincie";
    public static final String COMUNI = "comuni";
    public static final String SETTING_USER = "Setting user {}";

    // http://localhost:8080/registration
    @GetMapping("/registration")
    public ModelAndView formGet( Model model, HttpSession session) {

        clearSession(session);
        getUserSession(session);
        setAttribute(REGIONI,ExcellService.regioni(),session);
        return REGISTRATION;
    }
    @PostMapping("/registration")
    public ModelAndView setUser(UserDTO userDTO, HttpSession session) {
        User sessionUser = getUserSession(session);
        if (StringUtils.isNotBlank(userDTO.getName())) sessionUser.setName(userDTO.getName());
        if (StringUtils.isNotBlank(userDTO.getSurname()))sessionUser.setSurname(userDTO.getSurname());
        if (StringUtils.isNotBlank(userDTO.getMessage()))sessionUser.setMessage(userDTO.getMessage());
        log.info(SETTING_USER,sessionUser);
        return REGISTRATION;
    }
    @RequestMapping(
            value = "/registration/regione/{regione}",
            method = {RequestMethod.GET,RequestMethod.POST}
    )
    public ModelAndView setRegione(
            @PathVariable(value = REGIONE,required = false) String regione, HttpSession session) {
        log.info("Setting regione {}",regione);
        User sessionUser = getUserSession(session);
        if (regione != null){
            sessionUser.setRegione(regione);
            sessionUser.setProvincia(null);
            sessionUser.setComune(null);
            setAttribute(PROVINCIE,ExcellService.provincie(regione),session);
            setAttribute(COMUNI,null,session);
        }
        log.info(SETTING_USER,sessionUser);
        return REGISTRATION;
    }
    @GetMapping("/registration/provincia/{provincia}")
    public ModelAndView setProvincia(@PathVariable(value = "provincia",required = false) String provincia, HttpSession session) {

        log.info("Setting provincia {}",provincia);
        User sessionUser = getUserSession(session);
        if (provincia != null){
            sessionUser.setProvincia(provincia);
            sessionUser.setComune(null);
            setAttribute(COMUNI,ExcellService.comuni(provincia),session);
        }
        log.info(SETTING_USER,sessionUser);
        return REGISTRATION;
    }
    @GetMapping("/registration/comune/{comune}")
    public ModelAndView setComune(@PathVariable(value = "comune",required = false) String comune, HttpSession session) {
        log.info("Setting comune {}",comune);
        User sessionUser = getUserSession(session);
        if (comune != null){
            sessionUser.setComune(comune);
        }
        log.info(SETTING_USER,sessionUser);
        return REGISTRATION;
    }
    @GetMapping("/names/{name}")
    public ModelAndView getNames(@PathVariable(value = "name",required = false) String name, HttpSession session){
        User sessionUser = getUserSession(session);
        List<String> names = new ArrayList<>();
        names.add("davidone");
        names.add("marco");
        names.add("pietro");
        if (name != null && !"".equals(name)){
            for (String e : names){
                if (e.contains(name))
                    sessionUser.setName(e);
            }
        }else{
            sessionUser.setName(name);
        }
        return new ModelAndView("name");
    }
    /* ------------------------------------------------------------------------------------ */
    private User getUserSession(HttpSession session) {
        if (session.getAttribute(USER) != null){
            return (User)session.getAttribute(USER);
        }else{
            User u = new User();
            session.setAttribute(USER,u);
            return u;
        }
    }
    private void clearSession(HttpSession session){
        for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements();){
            session.removeAttribute(e.nextElement());
        }
    }
    private Map<String,Object> getMap(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements();){
            String key = e.nextElement();
            Object value = session.getAttribute(key);
            map.put(key,value);
        }
        return map;
    }
    private void setAttribute(String key,Object value,HttpSession session){

        if (session.getAttribute(key) != null){
            session.removeAttribute(key);
        }
        session.setAttribute(key,value);
    }
}
