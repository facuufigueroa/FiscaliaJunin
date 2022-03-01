package Fiscalia.junin.Controller;


import Fiscalia.junin.Model.User;
import Fiscalia.junin.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SessionAttributes("usuario")
@Controller
public class UserController {




    @Autowired
    public IUserService userService;

    @GetMapping("/form")
    public String formularioUsuario(Model model){
        model.addAttribute("usuario",new User());
        model.addAttribute("mensaje","Guardar usuario");
        model.addAttribute("titulo","Crear Usuario");
        return "form";

    }

    @PostMapping("/form") // el valid sirve para validar si hay algun error, las validaciones las declare en el Model user por ej @NotEmpty
    public String guardarUsuario(@Valid @ModelAttribute("usuario") User user, BindingResult result, Model model, RedirectAttributes flash,
                                 SessionStatus status){

        String mensaje= (user.getId() != null)?"Editar usuario":"Guardar usuario";
        String mensaje2 = (user.getId() != null)?"Editar usuario":"Crear usuario";
        if(result.hasErrors()) {
            model.addAttribute("mensaje",mensaje);
            model.addAttribute("titulo", mensaje2);
            return "form";

        }

        if(userService.save(user)){  //el save verifica que el email no este en uso
            status.setComplete();
            String mensajeFlash = "Operacion realizada satisfactoriamente!";
            flash.addFlashAttribute("success",mensajeFlash);
            return "redirect:/users";
        }else{ //si esta en uso va a entrar al else
            model.addAttribute("mensaje",mensaje);
            model.addAttribute("titulo", mensaje2);
            if(user.getId()!=null){ //pero, en caso que este editando, le va a permitir guardarlo/editarlo al usuario de todas formas
               try {
                   userService.save2(user);
                   String mensajeFlash = (user.getId() != null)?"El usuario se ha modificado con éxito!":"Se ha agregado con éxito al usuario!";
                   flash.addFlashAttribute("success",mensajeFlash);
                   return "redirect:/users";

               }catch (Exception e){
                   model.addAttribute("danger","Error: el email ya se encuentra en uso");
                   return "form";
               }
            }else{
                model.addAttribute("danger","Error: el email ya se encuentra en uso");
                return "form";
            }
        }





    }
    @GetMapping("/users")
    public String principal(Model model,Authentication auth){

        List<User> usuarios = userService.findAll();
        model.addAttribute("usuarios",usuarios);
        model.addAttribute("titulo","Listado de usuarios");
        return "users";
    }



    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id,RedirectAttributes flash,Authentication auth){

        User u = (User) auth.getPrincipal();
        if(u.getId().equals(id)){
            flash.addFlashAttribute("danger","No se puede eliminar al usuario que se encuentra logueado.");
            return "redirect:/users";
        }
        userService.delete(id);
        flash.addFlashAttribute("success","Usuario eliminado con éxito!");
        return "redirect:/users";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model,RedirectAttributes flash){
        Optional<User> usuario;
        if(id>0){
            usuario = userService.buscarUsuario(id);

        }else{
            flash.addFlashAttribute("danger", "El cliente no existe!");
            return "redirect:/users";
        }
        String mensaje= (usuario.get().getId() != null)?"Editar usuario":"Guardar usuario";
        model.addAttribute("mensaje",mensaje);
        model.addAttribute("usuario",usuario);
        model.addAttribute("titulo","Editar usuario");
        return "form";
    }




}
