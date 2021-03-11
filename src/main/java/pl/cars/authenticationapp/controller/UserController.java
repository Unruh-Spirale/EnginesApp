package pl.cars.authenticationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.cars.authenticationapp.domain.entity.User;
import pl.cars.authenticationapp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "registerForm";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registerForm";
        }else {
            userService.addWithDefaultRole(user);
            return "redirect:/";
        }
    }

    @GetMapping("/loginform")
    public String login(){
        return "login_form";
    }

    @GetMapping("/users")
    public String users(Model model){
//        List<User> users = userService.getAllUsers();
        List<User>users = userService.getOnlyUsers();
        model.addAttribute("users",users);
        return "users";
    }

    @GetMapping("/deleteuser/{idUser}")
    public String deleteUser(@PathVariable("idUser") long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
