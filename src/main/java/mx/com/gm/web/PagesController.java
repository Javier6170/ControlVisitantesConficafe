package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
@Slf4j
public class PagesController {

    @Autowired
    private PersonaService personaService;

    @GetMapping(value = { "/", "/home"})
    public String pageHome(Model model, @AuthenticationPrincipal User user) {

       if (user != null){
           var personas = personaService.listarPersonas();
           int cont = 0;

           for (int i = 0; i < personas.size(); i++) {
               if (personas.get(i).getFecha() != null){
                   if (personas.get(i).getFecha().getDate() == Calendar.getInstance().getTime().getDate()){
                       cont++;
                   }
               }
           }
           log.info("usuario que hizo login:" + user);
           model.addAttribute("totalClientes", cont);
           return  "index";
       }
        return "login";
    }


    @GetMapping(value = {"/agregarUsuario"})
    public String pageAgregarUsuario(@AuthenticationPrincipal User user){
        if (user != null){
            if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
                return "agregarUsuario";
            }
        }
        return "login";
    }

    @GetMapping(value = {"/listaUsuariosHoy"})
    public String pagelistaUsuarios(Model model,@AuthenticationPrincipal User user){
        if (user != null){
            var personasActuales = new ArrayList();
            var personas = personaService.listarPersonas();
            for (int i = 0; i < personas.size(); i++) {
                if (personas.get(i).getFecha() != null){
                    if (personas.get(i).getFecha().getDate() == Calendar.getInstance().getTime().getDate()){
                        personasActuales.add(personas.get(i));
                    }
                }
            }

            model.addAttribute("personas", personasActuales);
            return "listadoClientes";
        }
        return "redirect:/";
    }



}
