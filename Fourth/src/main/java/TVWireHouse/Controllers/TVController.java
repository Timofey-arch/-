package TVWireHouse.Controllers;

import TVWireHouse.DAO.TVDAO;
import TVWireHouse.Entities.TV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class TVController {
    private final TVDAO tvdao;

    @Autowired
    public TVController(TVDAO tvdao){
        this.tvdao = tvdao;
    }

    @GetMapping("/")
    public String mainMenu(){
        return "MainMenu";
    }

    @GetMapping("/tvs")
    public String TVDatabase(Model model){
        model.addAttribute("tvs", tvdao.allTVs());
        return "TVDatabase";
    }

    @GetMapping("/tvs/new")
    public String addTV(Model model){
        model.addAttribute("tv", new TV());
        return "AddTV";
    }

    @GetMapping("/tvs/edit")
    public String updateTV(Model model){
        model.addAttribute("tv", new TV());
        return "UpdateTV";
    }

    @GetMapping("/tvs/delete")
    public String deleteTV(Model model){
        model.addAttribute("tv", new TV());
        return "DeleteTV";
    }

    @GetMapping("/tvs/find")
    public String findById(Model model){
        model.addAttribute("tv", new TV());
        return "FindById";
    }

    @PostMapping("/tvs")
    public String add(@Valid @ModelAttribute("tv") TV tv, BindingResult result){
        if(result.hasErrors()){
            return "AddTV";
        }
        tvdao.add(tv);
        return "redirect:/";
    }

    @DeleteMapping("/tvs/delete")
    public String delete(@ModelAttribute("tv") TV tv, Model model){
        if(tvdao.findById(tv.getId()) == null){
            String message = "This id does not exist in the database";
            model.addAttribute("message", message);
            return "DeleteTV";
        }
        tvdao.delete(tv.id);
        return "redirect:/";
    }

    @PutMapping("/tvs/edit")
    public String edit(@Valid @ModelAttribute("tv") TV tv, BindingResult result, Model model){
        if(tvdao.findById(tv.getId()) == null){
            String message = "This id does not exist in the database";
            model.addAttribute("message", message);
            return "UpdateTV";
        }
        if (result.hasErrors()){
            return "UpdateTV";
        }
        tvdao.update(tv);
        return "redirect:/";
    }

    @GetMapping("/tvs/find/tv")
    public String find(@ModelAttribute("tv") TV tv, Model model){
        if(tvdao.findById(tv.getId()) == null){
            String message = "This id does not exist in the database";
            model.addAttribute("message", message);
            return "FindById";
        }
        TV foundTV = tvdao.findById(tv.id);
        model.addAttribute("tv", foundTV);
        return "TV";
    }
}
