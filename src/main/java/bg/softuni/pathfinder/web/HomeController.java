package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final PictureService pictureService;

    @Autowired
    public HomeController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("pictureUrls", this.pictureService.findAllUrls());

        return "index";
    }
}