package Fiscalia.junin.Controller;


import Fiscalia.junin.Model.MovimientoBancario;
import Fiscalia.junin.Model.RedSocial;
import Fiscalia.junin.Services.IredSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("causaSession")
public class RedController {


    @Autowired
    private IredSocialService redSocialService;


    @GetMapping("/detalleRedSocial/{idRed}")

    public String detalleMovimientoB(@PathVariable Long idRed,Model model, RedirectAttributes flash) {
        RedSocial redSocial = redSocialService.findById(idRed);
        model.addAttribute("redSocial", redSocial);

        return "detalleRedSocial";
    }







    @GetMapping("/eliminarRedSocial/{id}")
    public String eliminarCausa(@PathVariable Long id, RedirectAttributes flash) {
        redSocialService.delete(id);
        flash.addFlashAttribute("success", "Red Social eliminada con éxito!");
        return "redirect:/causas";
    }


}
