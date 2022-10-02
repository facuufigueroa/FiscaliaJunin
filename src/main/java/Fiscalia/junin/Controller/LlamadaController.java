package Fiscalia.junin.Controller;


import Fiscalia.junin.Model.Causa;
import Fiscalia.junin.Model.LlamadaTelefonica;
import Fiscalia.junin.Services.ICausaService;
import Fiscalia.junin.Services.IllamadaTelefonicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("causaSession")
public class LlamadaController {


    @Autowired
    private IllamadaTelefonicaService llamadaTelefonicaService;

    @Autowired
    private ICausaService causaService;

    @GetMapping("/detalleLlamadaTelefonica/{idLlamada}")
    public String detalleLlamadaTelefonica(@PathVariable Long idLlamada,Model model, RedirectAttributes flash) {
        LlamadaTelefonica llamadaTelefonica = llamadaTelefonicaService.findById(idLlamada);
        model.addAttribute("llamadaT", llamadaTelefonica);
        return "detalleLlamadaT";
    }


    @GetMapping("/eliminarLlamada/{id}")
    public String eliminarCausa(@PathVariable Long id, RedirectAttributes flash) {
        llamadaTelefonicaService.delete(id);
        flash.addFlashAttribute("success", "Llamada eliminada con éxito!");
        return "redirect:/causas";
    }

}
