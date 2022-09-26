package TVWireHouse.Controllers;

import TVWireHouse.DAO.TVDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TVController {
    private final TVDAO tvdao;

    @Autowired
    public TVController(TVDAO tvdao){
        this.tvdao = tvdao;
    }

    @GetMapping("/tvs")
    public String TVDatabase(Model model){
        model.addAttribute("tvs", this.tvdao.allTVs());
        return "TVDatabase";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
