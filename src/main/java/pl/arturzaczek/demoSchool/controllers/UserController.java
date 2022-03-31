package pl.arturzaczek.demoSchool.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.arturzaczek.demoSchool.model.dto.UserRegisterForm;
import pl.arturzaczek.demoSchool.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login")
    public String getLoginForm() {
        return "user/login-form";
    }

    @GetMapping("user/register")
    public String getRegisterForm(final Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterForm());
        return "user/register-form";
    }

    @PostMapping("/user/register/new")
    public String registerUser(@ModelAttribute final UserRegisterForm userRegisterForm, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRegisterForm", userRegisterForm);
            return "user/register-form";
        }
        if (userService.checkIfUserExist(userRegisterForm.getEmail())) {
            model.addAttribute("employeeRegisterForm", userRegisterForm);
            model.addAttribute("user_error", "user exist");
            return "user/register-form";
        }
        userService.registerUser(userRegisterForm);
        model.addAttribute("registered_success", "new user registered successfully");
        System.out.println("user registered successfully");
        return "redirect:/index";
    }

    @GetMapping("/user/logout")
    public String logoutPage(final HttpServletRequest request, final HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/user/login-form";
    }

    @GetMapping("/user/login-form")
    public String userLogin() {
        return "user/login-form";
    }

    @GetMapping("user/loginError")
    public String userLoginError() {
        return "user/loginError";
    }
}

