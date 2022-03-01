package Fiscalia.junin.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String login(@RequestParam(value = "error",required = false) String error,
                        Model model){

        if(error!=null){
            model.addAttribute("danger","Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo");
        }
        return "/login";
    }


}
