package Fiscalia.junin.Controller;

import Fiscalia.junin.Model.LlamadaTelefonica;
import Fiscalia.junin.Model.MovimientoBancario;
import Fiscalia.junin.Services.ImovimientoBancarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovBancarioController {

    @Autowired
    private ImovimientoBancarioService movimientoBancarioService;


    @GetMapping("/detalleMovBancario/{idmov}")

    public String detalleMovimientoB(@PathVariable Long idmov, Model model, RedirectAttributes flash) {
        MovimientoBancario movimientoBancario = movimientoBancarioService.findById(idmov);
        model.addAttribute("movBancario", movimientoBancario);

        return "detalleMovimientoB";
    }


    @GetMapping("/eliminarMovBancario/{id}")
    public String eliminarCausa(@PathVariable Long id, RedirectAttributes flash) {
        movimientoBancarioService.delete(id);
        flash.addFlashAttribute("success", "Movimiento Bancario eliminado con éxito!");
        return "redirect:/causas";
    }



}
