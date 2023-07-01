package com.example.simpleforms.Controller;

import com.example.simpleforms.model.UsersModels;
import com.example.simpleforms.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModels());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModels());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModels usersModels){
        System.out.println("register request: " + usersModels);
        UsersModels registeredUser = usersService.registerUser(usersModels.getLogin(), usersModels.getPassword(), usersModels.getEmail());
return registeredUser == null ? "error_page": "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModels usersModels, Model model){
        System.out.println("login request: " + usersModels);
        UsersModels authenticated = usersService.authenticate(usersModels.getLogin(), usersModels.getPassword());
        if(authenticated != null){
            model.addAttribute("userLogin", authenticated.getLogin());
            return "personal_page";

        }else{
            return "error_page";
        }
    }

}
