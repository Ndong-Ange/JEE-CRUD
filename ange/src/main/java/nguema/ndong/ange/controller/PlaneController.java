package nguema.ndong.ange.controller;

import nguema.ndong.ange.model.Plane;
import nguema.ndong.ange.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/planes")
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    // Afficher le formulaire d'édition avec les données actuelles
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Plane plane = planeService.findById(id).orElse(null);
        if (plane == null) {
            model.addAttribute("error", "Avion non trouvé");
            return "planes/error";
        }
        model.addAttribute("plane", plane);
        return "planes/edit";
    }

    // Modifier l'avion avec les nouvelles données via POST ou PUT
    @PostMapping("/edit/{id}")
    public String editPlane(@PathVariable("id") Long id, @ModelAttribute Plane plane) {
        Plane existingPlane = planeService.findById(id).orElse(null);
        if (existingPlane != null) {
            existingPlane.setModel(plane.getModel());
            existingPlane.setBrand(plane.getBrand());
            existingPlane.setReleaseDate(plane.getReleaseDate());
            existingPlane.setStatus(plane.getStatus());
            planeService.save(existingPlane);
        }
        return "redirect:/planes";
    }

    // Autres méthodes (ajouter, lister, supprimer un avion)...
}
