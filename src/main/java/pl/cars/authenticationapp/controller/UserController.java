package pl.cars.authenticationapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.cars.authenticationapp.domain.entity.Users;
import pl.cars.authenticationapp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new Users());
        return "registerForm";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid Users users, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registerForm";
        }else {
            userService.addWithDefaultRole(users);
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
        List<Users>users = userService.getOnlyUsers();
        model.addAttribute("users",users);
        return "users";
    }

    @GetMapping("/deleteuser/{idUser}")
    public String deleteUser(@PathVariable("idUser") long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
