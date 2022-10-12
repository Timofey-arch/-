package TVWireHouse.Controllers;

import TVWireHouse.DAO.TVDAO;
import TVWireHouse.Entities.TV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private final TVDAO tvdao;

    @Autowired
    public UserController(TVDAO tvdao) {
        this.tvdao = tvdao;
    }

    @GetMapping()
    public String userPage(){
        return "User/UserPage";
    }

    @GetMapping("/tvs")
    public String TVDatabase(Model model){
        model.addAttribute("tvs", tvdao.allTVs());
        return "TVDatabase";
    }

    @GetMapping("/tvs/find")
    public String findById(Model model){
        model.addAttribute("tv", new TV());
        return "User/FindById";
    }

    @GetMapping("/tvs/find/tv")
    public String find(@ModelAttribute("tv") TV tv, Model model){
        if(tvdao.findById(tv.getId()) == null){
            String message = "This id does not exist in the database";
            model.addAttribute("message", message);
            return "User/FindById";
        }

        TV foundTV = tvdao.findById(tv.id);
        model.addAttribute("tv", foundTV);
        return "User/TV";
    }

}
