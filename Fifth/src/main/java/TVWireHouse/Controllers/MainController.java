package TVWireHouse.Controllers;

import TVWireHouse.DAO.UserDAO;
import TVWireHouse.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {
    public final UserDAO userDAO;

    @Autowired
    public MainController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GetMapping("/")
    public String redirectToPage(HttpServletRequest httpServletRequest){
        if(httpServletRequest.isUserInRole("ADMIN")){
            return "redirect:/admin";
        }else{
            return "redirect:/user";
        }
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("user", new User());
        return "LoginRegistration/Login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        model.addAttribute("user", new User());
        return "LoginRegistration/Registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(result.hasErrors()){
            return "LoginRegistration/Registration";
        }

        if(userDAO.userExist(user.getUsername())){
            String message = "This username has taken. Try another username";
            model.addAttribute("message", message);
            return "LoginRegistration/Registration";
        }

        user.setRole("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        userDAO.add(user);
        return "redirect:/login";
    }
}
