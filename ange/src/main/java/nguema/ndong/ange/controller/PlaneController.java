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

    @GetMapping
    public String listPlanes(Model model) {
        model.addAttribute("planes", planeService.findAll());
        return "planes/list";
    }

    @GetMapping("/add")
    public String addPlaneForm(Model model) {
        model.addAttribute("plane", new Plane());
        return "planes/add";
    }

    @PostMapping("/add")
    public String addPlane(@ModelAttribute Plane plane) {
        planeService.save(plane);
        return "redirect:/planes";
    }

    @GetMapping("/edit/{id}")
    public String editPlaneForm(@PathVariable Long id, Model model) {
        Plane plane = planeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid plane Id:" + id));
        model.addAttribute("plane", plane);
        return "planes/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPlane(@PathVariable Long id, @ModelAttribute Plane plane) {
        plane.setId(id);
        planeService.save(plane);
        return "redirect:/planes";
    }

    @GetMapping("/delete/{id}")
    public String deletePlane(@PathVariable Long id) {
        planeService.deleteById(id);
        return "redirect:/planes";
    }
}
