package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class PagesController {

    @Autowired
    private PersonaService personaService;

    @GetMapping(value = { "/", "/home"})
    public String pageHome(Model model, @AuthenticationPrincipal User user) {
        var personas = personaService.listarPersonas();
        log.info("usuario que hizo login:" + user);
        model.addAttribute("personas", personas);
        model.addAttribute("totalClientes", personas.size());
        return  "index";
    }


    @GetMapping(value = {"/agregarUsuario"})
    public String pageAgregarUsuario(){
        return "agregarUsuario";
    }
}
