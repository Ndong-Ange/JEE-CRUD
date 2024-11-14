package nguema.ndong.ange.controller;

import nguema.ndong.ange.model.Plane;
import nguema.ndong.ange.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/planes")
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    // Afficher la liste des avions
    @GetMapping
    public String listPlanes(Model model) {
        model.addAttribute("planes", planeService.findAll());
        return "planes/list";
    }

    // Afficher le formulaire d'ajout (pour l'interface web)
    @GetMapping("/add")
    public String addPlaneForm(Model model) {
        model.addAttribute("plane", new Plane());
        return "planes/add";  // Retourner le template Thymeleaf "add"
    }

    // Traiter la soumission du formulaire pour ajouter un avion (pour l'interface web)
    @PostMapping("/add")
    public String addPlane(@ModelAttribute Plane plane) {
        planeService.save(plane);
        return "redirect:/planes"; // Rediriger vers la liste des avions
    }

    // Ajouter un avion via POST avec JSON (API REST)
    @PostMapping("/add/api")
    public ResponseEntity<Plane> addPlaneApi(@RequestBody Plane plane) {
        Plane savedPlane = planeService.save(plane);  // Sauvegarde de l'avion
        return new ResponseEntity<>(savedPlane, HttpStatus.CREATED);  // Retourner une réponse HTTP 201
    }

    // Modifier un avion (formulaire Thymeleaf)
    @GetMapping("/edit/{id}")
    public String editPlaneForm(@PathVariable Long id, Model model) {
        Plane plane = planeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid plane Id:" + id));
        model.addAttribute("plane", plane);
        return "planes/edit";
    }

    // Modifier un avion via POST (formulaire Thymeleaf)
    @PostMapping("/edit/{id}")
    public String editPlane(@PathVariable Long id, @ModelAttribute Plane plane) {
        plane.setId(id);
        planeService.save(plane);
        return "redirect:/planes";
    }

    // Modifier un avion via PUT avec JSON (API REST)
    @PutMapping("/edit/{id}/api")
    public ResponseEntity<Plane> editPlaneApi(@PathVariable Long id, @RequestBody Plane plane) {
        Optional<Plane> existingPlaneOpt = planeService.findById(id);
        if (existingPlaneOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retourner 404 si l'avion n'existe pas
        }

        Plane existingPlane = existingPlaneOpt.get();
        existingPlane.setModel(plane.getModel());
        existingPlane.setBrand(plane.getBrand());
        existingPlane.setReleaseDate(plane.getReleaseDate());
        existingPlane.setStatus(plane.getStatus());

        Plane updatedPlane = planeService.save(existingPlane);
        return new ResponseEntity<>(updatedPlane, HttpStatus.OK);  // Retourner 200 OK avec l'avion mis à jour
    }

    // Supprimer un avion
    @GetMapping("/delete/{id}")
    public String deletePlane(@PathVariable Long id) {
        planeService.deleteById(id);
        return "redirect:/planes";
    }

    // Récupérer la liste des avions via une requête GET en JSON (API REST)
    @GetMapping("/api")
    public ResponseEntity<List<Plane>> getAllPlanesApi() {
        List<Plane> planes = planeService.findAll();
        return new ResponseEntity<>(planes, HttpStatus.OK);  // Retourner la liste des avions
    }

    // Récupérer un avion spécifique par ID via une requête GET en JSON (API REST)
    @GetMapping("/api/{id}")
    public ResponseEntity<Plane> getPlaneByIdApi(@PathVariable Long id) {
        Optional<Plane> plane = planeService.findById(id);
        return plane.map(p -> new ResponseEntity<>(p, HttpStatus.OK))  // Si trouvé, retourner 200 OK
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // Sinon, retourner 404 Not Found
    }
}
