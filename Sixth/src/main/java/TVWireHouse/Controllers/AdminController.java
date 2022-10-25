package TVWireHouse.Controllers;

import TVWireHouse.DAO.TVDAO;
import TVWireHouse.Entities.TV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final TVDAO tvdao;

    @Autowired
    public AdminController(TVDAO tvdao){
        this.tvdao = tvdao;
    }

    @GetMapping()
    public String adminPage(){
        return "Admin/AdminPage";
    }

    @GetMapping(value = "/tvs", headers = {"Accept=text/html"})
    public String TVDatabase(Model model){
        model.addAttribute("tvs", tvdao.allTVs());
        return "TVDatabase";
    }

    @GetMapping("/tvs/new")
    public String addTV(Model model){
        model.addAttribute("tv", new TV());
        return "Admin/AddTV";
    }

    @GetMapping("/tvs/edit")
    public String updateTV(Model model){
        model.addAttribute("tv", new TV());
        return "Admin/UpdateTV";
    }

    @GetMapping("/tvs/delete")
    public String deleteTV(Model model){
        model.addAttribute("tv", new TV());
        return "Admin/DeleteTV";
    }

    @GetMapping(value = "/tvs/find", headers = {"Accept=text/html"})
    public String findById(Model model){
        model.addAttribute("tv", new TV());
        return "Admin/FindById";
    }

    @PostMapping("/tvs/new")
    public String add(@Valid @ModelAttribute("tv") TV tv, BindingResult result){
        if(result.hasErrors()){
            return "Admin/AddTV";
        }
        tvdao.add(tv);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/tvs/delete", headers = {"Accept=text/html"})
    public String delete(@ModelAttribute("tv") TV tv, Model model){
        if(tvdao.findById(tv.getId()) == null){
            String message = "This id does not exist in the database";
            model.addAttribute("message", message);
            return "Admin/DeleteTV";
        }
        tvdao.delete(tv.id);
        return "redirect:/admin";
    }

    @PutMapping("/tvs/edit")
    public String edit(@Valid @ModelAttribute("tv") TV tv, BindingResult result, Model model){
        if(tvdao.findById(tv.getId()) == null){
            String message = "This id does not exist in the database";
            model.addAttribute("message", message);
            return "Admin/UpdateTV";
        }
        if (result.hasErrors()){
            return "Admin/UpdateTV";
        }
        tvdao.update(tv);
        return "redirect:/admin";
    }

    @GetMapping("/tvs/find/tv")
    public String find(@ModelAttribute("tv") TV tv, Model model){
        if(tvdao.findById(tv.getId()) == null){
            String message = "This id does not exist in the database";
            model.addAttribute("message", message);
            return "Admin/FindById";
        }
        TV foundTV = tvdao.findById(tv.id);
        model.addAttribute("tv", foundTV);
        return "Admin/TV";
    }

    @ResponseBody
    @GetMapping(value = "/tvs", headers = {"Accept=application/json"})
    public ResponseEntity<List<TV>> allTVS(){
        return new ResponseEntity<>(tvdao.allTVs(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/tvs/find/{id}", headers = {"Accept=application/json"})
    public ResponseEntity<TV> find(@PathVariable("id") int id){
        TV tv = tvdao.findById(id);

        if(tv == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tv, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/tvs/new", headers = {"Accept=application/json"})
    public ResponseEntity<TV> add(@RequestBody TV tv){
        tvdao.add(tv);
        return new ResponseEntity<>(tv, HttpStatus.CREATED);
    }

    @ResponseBody
    @DeleteMapping(value = "/tvs/delete/{id}", headers = {"Accept=application/json"})
    public ResponseEntity<TV> delete(@PathVariable("id") int id){
        TV deletedTV = tvdao.findById(id);

        if(deletedTV == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tvdao.delete(id);
        return new ResponseEntity<>(deletedTV, HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping(value = "/tvs/edit", headers = {"Accept=application/json"})
    public ResponseEntity<TV> edit(@RequestBody TV tv){
        tvdao.update(tv);
        return new ResponseEntity<>(tvdao.findById(tv.getId()), HttpStatus.OK);
    }
}
