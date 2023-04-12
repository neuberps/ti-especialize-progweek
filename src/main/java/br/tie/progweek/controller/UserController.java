package br.tie.progweek.controller;

import br.tie.progweek.model.User;
import br.tie.progweek.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listUsers")
    public ModelAndView listUsers() {
        ModelAndView modelAndView = new ModelAndView("list-users");
        List<User> users = userService.list();
        int listSize = 0;
        if (!users.isEmpty()) {
            modelAndView.addObject("list", users);
            modelAndView.addObject("showlist", true);
            listSize = users.size();
        }
        modelAndView.addObject("listSize", listSize);
        return modelAndView;
    }

    @RequestMapping(value = "/createUser")
    public ModelAndView userRegister() {

        return new ModelAndView("user");
    }

    @RequestMapping(value = "/editUser")
    public ModelAndView editUser(String id) {
        ModelAndView modelAndView = new ModelAndView("user");
        log.info("editUser.id: " + id);
        Optional<User> user = userService.getUser(id);
        log.info("editUser.user: " + user.toString());
        if (user.isPresent()) {
            modelAndView.addObject("user", user.get());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/saveUser")
    public ModelAndView saveUser(User user) {
        log.info("saveUser.id: " + user.getId());
        userService.saveAndUpdate(user);
        ModelAndView modelAndView = listUsers();
        return modelAndView;
    }

    @RequestMapping(value = "/deleteUser")
    public ModelAndView deleteUser(String id) {
        log.info("deleteUser.id: " + id);
        userService.delete(id);
        ModelAndView modelAndView = listUsers();
        return modelAndView;
    }

}
