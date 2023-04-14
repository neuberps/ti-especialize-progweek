package br.tie.progweek.controller;

import br.tie.progweek.config.ConfigProperties;
import br.tie.progweek.dto.UserDTO;
import br.tie.progweek.model.User;
import br.tie.progweek.service.UserService;
import br.tie.progweek.util.MsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private ConfigProperties properties;

    @RequestMapping(value = "/listUsers")
    public ModelAndView listUsers() {
        ModelAndView modelAndView = new ModelAndView("list-users");
        try {
            List<User> users = userService.list();
            int listSize = 0;
            if (!users.isEmpty()) {
                modelAndView.addObject("list", users);
                modelAndView.addObject("showlist", true);
                listSize = users.size();
            }
            modelAndView.addObject("listSize", listSize);
        } catch (Exception e) {
            modelAndView.addObject("msg", MsgUtil.danger(properties.getErrortitle(), properties.getUserlisterrortext()));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/createUser")
    public ModelAndView userRegister() {

        return new ModelAndView("user");
    }

    @RequestMapping(value = "/editUser")
    public ModelAndView editUser(String id) {
        ModelAndView modelAndView = new ModelAndView("user");
        try {
            log.info("editUser.id: " + id);
            Optional<User> user = userService.getUser(id);
            log.info("editUser.user: " + user.toString());
            if (user.isPresent()) {
                modelAndView.addObject("user", user.get().toUserDTO());
            }
        } catch (Exception e) {
            modelAndView.addObject("msg", MsgUtil.danger(properties.getErrortitle(), properties.getUserediterrortext()));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/saveUser")
    public ModelAndView saveUser(@Valid UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView("user");
        try {
            log.info("saveUser.id: " + userDTO.getId());
            if (existEmail(userDTO, modelAndView)) {
                return modelAndView;
            }
            userService.saveAndUpdate(userDTO.toUser());
            modelAndView = listUsers();
            setMessageSuccess(userDTO, modelAndView);
        } catch (Exception e) {
            modelAndView.addObject("msg", MsgUtil.danger(properties.getErrortitle(), properties.getUserupdateerrortext()));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteUser")
    public ModelAndView deleteUser(String id) {
        ModelAndView modelAndView = new ModelAndView("user");
        try {
            log.info("deleteUser.id: " + id);
            userService.delete(id);
            modelAndView = listUsers();
            modelAndView.addObject("msg", MsgUtil.success(properties.getSuccesstitle(), properties.getUserdeletesuccesstext()));
        } catch (Exception e) {
            modelAndView.addObject("msg", MsgUtil.danger(properties.getErrortitle(), properties.getUserdeleteerrortext()));
        }
        return modelAndView;
    }

    private Boolean existEmail(UserDTO userDTO, ModelAndView modelAndView) {
        try {
            if (userDTO.getId().isEmpty()) {
                Optional<User> userEmailExist = userService.findByEmail(userDTO.toUser());
                if (userEmailExist.isPresent()){
                    userDTO.setId(null);
                    modelAndView.addObject("user", userDTO);
                    modelAndView.addObject("msg", MsgUtil.warning(properties.getWarningtitle(), properties.getUseremailexistwarningtext()));
                    return true;
                }
            }
        } catch (Exception e) {
            modelAndView.addObject("msg", MsgUtil.danger(properties.getErrortitle(), properties.getUservalidemailerrortext()));
            return true;
        }
        return false;
    }
    private void setMessageSuccess(UserDTO userDTO, ModelAndView modelAndView) {
        if (userDTO.getId().isEmpty()) {
            modelAndView.addObject("msg", MsgUtil.success(properties.getSuccesstitle(), properties.getUsercreatesuccesstext()));
        } else {
            modelAndView.addObject("msg", MsgUtil.success(properties.getSuccesstitle(), properties.getUserupdatesuccesstext()));
        }
    }

}
