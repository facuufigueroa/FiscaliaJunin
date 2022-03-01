package Fiscalia.junin.Controller;


import Fiscalia.junin.Model.LlamadaTelefonica;
import Fiscalia.junin.Services.IllamadaTelefonicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControladorLlamadaTelefonica {


    @Autowired
    private IllamadaTelefonicaService llamadaTelefonicaService;

    @GetMapping("/detalleLlamadaTelefonica/{idLlamada}")
    public String detalleLlamadaTelefonica(@PathVariable Long idLlamada, Model model, RedirectAttributes flash) {
        LlamadaTelefonica llamadaTelefonica = llamadaTelefonicaService.findById(idLlamada);
        System.out.println("INFO LLAMADA " + llamadaTelefonica.getNumeroEmisor());
        model.addAttribute("llamadaT", llamadaTelefonica);

        return "detalleLlamadaT";
    }




}
