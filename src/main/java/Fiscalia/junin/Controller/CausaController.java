package Fiscalia.junin.Controller;


import Fiscalia.junin.Model.*;
import Fiscalia.junin.Services.ICausaService;
import Fiscalia.junin.Services.IllamadaTelefonicaService;
import Fiscalia.junin.Services.ImovimientoBancarioService;
import Fiscalia.junin.Services.IredSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
@SessionAttributes("causaSession")

public class CausaController {

    @Autowired
    private ICausaService causaService;

    private Long numero1;

    @Autowired
    private IllamadaTelefonicaService llamadaTelefonicaService;

    @Autowired
    private ImovimientoBancarioService movimientoBancarioService;

    @Autowired
    private IredSocialService redSocialService;


    private List<Informacion2> informacionLista = new ArrayList<>();



    @GetMapping("/causas")
    public String listarCausas(Model model) {

        List<Causa> causas = causaService.findAllByDesc();

        model.addAttribute("titulo", "Listado de causas");
        model.addAttribute("causas", causas);
        model.addAttribute("causa1",new Causa());
        return "/causas";


    }

    @GetMapping("/formCausa")
    public String obtenerFormCausa(Model model) {

        model.addAttribute("mensaje", "Siguiente");
        model.addAttribute("titulo", "Crear causa");
        model.addAttribute("causa", new Causa());
        return "formCausa";

    }

    @GetMapping("/eleccionInformacion/{causaId}")
    public String elegirInformacion(@PathVariable Long causaId, Model model) {

        Causa causa1 = causaService.findById(causaId);
        model.addAttribute("causaSession",causa1);
        return "eleccionInformacion";

    }


    @GetMapping("/formInformacion/{causaId}")
    public String detalleCausaInformacion(@PathVariable Long causaId, Model model) {
        informacionLista.clear();
        Causa causa1 = causaService.findById(causaId);
        recorrerPorLlamada(causa1.getInformacion());
        recorrerPorMovimiento(causa1.getInformacion());
        recorrerPorRed(causa1.getInformacion());


        //ordenarMap();
        Collections.sort(informacionLista,Collections.reverseOrder());
        model.addAttribute("infoSession",informacionLista);
        model.addAttribute("causaSession",causa1);

        return "formInformacion";

    }


    @GetMapping("/formHistorialCausa/{causaId}")
    public String historialCausa(@PathVariable Long causaId, Model model) {
        informacionLista.clear();
        Causa causa1 = causaService.findById(causaId);
        recorrerPorLlamada(causa1.getInformacion());
        recorrerPorMovimiento(causa1.getInformacion());
        recorrerPorRed(causa1.getInformacion());


        //ordenarMap();
        Collections.sort(informacionLista,Collections.reverseOrder());
        model.addAttribute("infoSession",informacionLista);
        model.addAttribute("causaSession",causa1);

        return "formHistorialCausa";

    }


    @PostMapping("/formCausa")
    public String crearCausa(@Valid @ModelAttribute Causa causa, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("mensaje", "Siguiente");
            model.addAttribute("titulo", "Crear causa");
            return "formCausa";
        }
        model.addAttribute("causaSession", causa);
        model.addAttribute("titulo", "Seleccione tipo de informacion");
        return "eleccionInformacion";
    }


    @GetMapping("/detalle/{id}")
    public String detalleCausa(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Causa causa = causaService.findById(id);
        if (causa == null) {
            flash.addFlashAttribute("danger", "La causa no existe en la base de datos!");
            return "redirect:/causas";
        }
        model.addAttribute("causa", causa);
        model.addAttribute("titulo", "Detalle de la causa");
        model.addAttribute("mensaje", "Editar descripcion");
        return "detalle";
    }


    @PostMapping("/detalle")
    public String guardarDescripcion(@ModelAttribute Causa causa, BindingResult result, Model model,
                                     RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Detalle de la causa");
            model.addAttribute("mensaje", "Editar descripción");
            return "/detalle";
        }
        Causa causa1 = causaService.save(causa);
        flash.addFlashAttribute("success", "Se ha actualizado correctamente la descripción de la causa con numero de expediente: " + causa1.getNumExpediente());
        return "redirect:/causas";
    }


    @GetMapping("/eliminarCausa/{id}")
    public String eliminarCausa(@PathVariable Long id, RedirectAttributes flash) {
        causaService.delete(id);
        flash.addFlashAttribute("success", "Causa eliminada con éxito!");
        return "redirect:/causas";
    }


    /* Buscar por victima*/
    @GetMapping("/causaBusqueda")
    public String buscarPorVictima(@RequestParam String victima, Model model, @ModelAttribute("causa1") Causa causa){
        model.addAttribute("causas",causaService.findByVictima(victima.toUpperCase()));
        return "causas";
    }

    /* Buscar por victimario*/
    @GetMapping("/causaBusquedaVictimario")
    public String buscarPorVictimario(@RequestParam String victimario, Model model, @ModelAttribute("causa1") Causa causa){
        model.addAttribute("causas",causaService.findByVictimario(victimario.toUpperCase()));
        return "causas";
    }

    /* Buscar causa por numero de expediente */
    @GetMapping("/causaBusquedaNumExpediente")
    public String buscarPorNumExpediente(@RequestParam String numExpediente, Model model, @ModelAttribute("causa1") Causa causa){
        model.addAttribute("causas",causaService.findByNumExpediente(numExpediente));
        return "causas";
    }

    /*Metodos llamada telefonica*/

    @GetMapping("/formLLamadaTelefonica")
    public String obtenerFormInfoLLamadaTelefonica(Model model) {

        model.addAttribute("mensaje", "Finalizar");
        model.addAttribute("llamadaTelefonica", new LlamadaTelefonica());
        model.addAttribute("titulo", "Formulario de llamada telefonica");
        return "formLLamadaTelefonica";
    }


    @PostMapping("/formLLamadaTelefonica")
    public String crearInfoLLamadaTelefonica(@Valid @ModelAttribute LlamadaTelefonica llamadaTelefonica, BindingResult result, Model model,
                                             @ModelAttribute("causaSession") Causa causa, RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("mensaje", "Finalizar");
            model.addAttribute("titulo", "Formulario de llamada telefonica");
            return "formLLamadaTelefonica";
        }

        numero1 = verificarIdNulo(causa);
        upperCaseVictimaYvictimario(causa);
        Causa causa1 = causaService.save(causa);
        llamadaTelefonica.setCausa(causa1);
        llamadaTelefonicaService.save(llamadaTelefonica);
        actualizarOcreadaMensaje(numero1,flash,causa);
        return "redirect:/causas";
    }


    /*Metodos Movimiento Bancario*/

    @GetMapping("/formMovimientoBancario")
    public String obtenerFormInfoMovimientoBancario(Model model) {

        model.addAttribute("mensaje", "Finalizar");
        model.addAttribute("movimientoBancario", new MovimientoBancario());
        model.addAttribute("titulo", "Formulario de Movimiento Bancario");
        return "formMovimientoBancario";
    }

    @PostMapping("/formMovimientoBancario")
    public String crearInfoLMovimientoBancario(@Valid @ModelAttribute MovimientoBancario movimientoBancario, BindingResult result, Model model,
                                               @ModelAttribute("causaSession") Causa causa,RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("mensaje", "Finalizar");
            model.addAttribute("titulo", "Formulario de Movimiento Bancario");
            return "formMovimientoBancario";
        }
        numero1 = verificarIdNulo(causa);
        upperCaseVictimaYvictimario(causa);
        Causa causa1 = causaService.save(causa);
        movimientoBancario.setCausa(causa1);
        movimientoBancarioService.save(movimientoBancario);
        actualizarOcreadaMensaje(numero1,flash,causa);
        return "redirect:/causas";
    }

    public void actualizarOcreadaMensaje(Long id, RedirectAttributes flash,Causa causa){
        if(id!=0) {
            flash.addFlashAttribute("success", "La causa con numero de expediente " + causa.getNumExpediente() + " ha sido actualizada satisfactoriamente");
        }else{
            flash.addFlashAttribute("success", "La causa con numero de expediente " + causa.getNumExpediente() + " ha sido creada satisfactoriamente");
        }
    }

    public void upperCaseVictimaYvictimario(Causa causa){
        causa.setVictima(causa.getVictima().toUpperCase());
        causa.setVictimario(causa.getVictimario().toUpperCase());
    }

    public Long verificarIdNulo(Causa causa){
        long numero;
        if(causa.getId()==null) {
            numero = 0;
        }else{
            numero = 1;
        }
        return numero;
    }



    /* Metodos Red Social */

    @GetMapping("/formRedSocial")
    public String obtenerFormInfoRedSocial(Model model) {

        model.addAttribute("mensaje", "Finalizar");
        model.addAttribute("redSocial", new RedSocial());
        model.addAttribute("titulo", "Formulario de red social");
        return "formRedSocial";
    }

    @PostMapping("/formRedSocial")
    public String crearInfoLRedSocial(@Valid @ModelAttribute RedSocial redSocial, BindingResult result, Model model,
                                      @ModelAttribute("causaSession") Causa causa,RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("mensaje", "Finalizar");
            model.addAttribute("titulo", "Formulario de Red Social");
            return "formRedSocial";
        }
        numero1 = verificarIdNulo(causa);
        upperCaseVictimaYvictimario(causa);
        Causa causa1 = causaService.save(causa);
        redSocial.setCausa(causa1);
        redSocialService.save(redSocial);
        actualizarOcreadaMensaje(numero1,flash,causa);
        return "redirect:/causas";
    }

    public void recorrerPorLlamada(List<Informacion> info) {

        for (Informacion i : info) {
            if(i.getEsLlamada()){
                System.out.println("ID DE LA LLAMDA" + i.getId());
                informacionLista.add(new Informacion2("LLamada Telefonica",i.getFecha(),i.getId()));

            }

        }
    }

    public void recorrerPorMovimiento(List<Informacion> inf){

        for(Informacion i : inf){
            if(i.getEsMovimiento()){
                informacionLista.add(new Informacion2("Movimiento Bancario",i.getFecha(),i.getId()));
            }

        }
    }

    public void recorrerPorRed(List<Informacion> inf){

        for(Informacion i : inf){
            if(i.getEsRedSocial()){
            informacionLista.add(new Informacion2("Red Social",i.getFecha(),i.getId()));
            }
        }
    }






 /*
    public List<Map.Entry<String, Date>> ordenarMap() {
        Set<Map.Entry<String,Date>> entrySet = historialInformaciones.entrySet();
        List<Map.Entry<String,Date>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<String, Date>>() {
            @Override
            public int compare(Map.Entry<String, Date> o1, Map.Entry<String, Date> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return list;
    }
    */













}









