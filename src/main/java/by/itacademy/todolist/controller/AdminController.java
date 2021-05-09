package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.MessageService;
import by.itacademy.todolist.service.ProfileService;
import by.itacademy.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final MessageService messageService;
    private final ProfileService profileService;

    @GetMapping
    public ModelAndView loadAdminPage() {
        try {
            return new ModelAndView("adminPage");
        } catch (Exception e) {
            log.warn("exception in loadAdminPage method ", e);
            return new ModelAndView("redirect:/error");
        }
    }

    @GetMapping("/users")
    public ModelAndView loadAllUsers(@RequestParam(required = false) String error) {
        try {
            List<User> users = userService.getAllUsers();
            ModelAndView modelAndView = new ModelAndView("/users");
            modelAndView.addObject(ApplicationConstants.USERS_KEY, users);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in loadAllUsers method ", e);
            return new ModelAndView("redirect:/error");
        }
    }

    @GetMapping("/messages")
    public ModelAndView loadAllMessages(@RequestParam(required = false) String error) {
        try {
            List<Message> messages = messageService.getAll();
            ModelAndView modelAndView = new ModelAndView("/messages");
            modelAndView.addObject(ApplicationConstants.MESSAGES_KEY, messages);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in loadAllMessages method ", e);
            return new ModelAndView("redirect:/error");
        }
    }

    @GetMapping("/user/block/{id}")
    public ModelAndView blockUser(@PathVariable Long id) {
        try {
            log.info("admin tries block user with id {}", id);
            ModelAndView modelAndView = updateUser(id, user -> {
                user.getProfile().setEnable(false);
                return user;
            });
            log.info("admin successfully blocked user with id {} ", id);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in method blockUser", e);
            return new ModelAndView("redirect:/admin/users",
                    ApplicationConstants.ERROR_KEY, "blocking user");
        }
    }

    @GetMapping("/user/unblock/{id}")
    public ModelAndView unblockUser(@PathVariable Long id) {
        try {
            log.info("admin tries unblock user with id {}", id);
            ModelAndView modelAndView = updateUser(id, user -> {
                user.getProfile().setEnable(true);
                return user;
            });
            log.info("admin successfully unblock user with id {} ", id);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in method unblock", e);
            return new ModelAndView("redirect:/admin/users",
                    ApplicationConstants.ERROR_KEY, "unblock user");
        }
    }

    @GetMapping("/user/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/users");
        log.info("admin tries delete user with id {}", id);
        try {
            userService.deleteById(id);
            log.info("admin successfully delete user with id {} ", id);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in method deleteUser", e);
            return new ModelAndView("redirect:/admin/users",
                    ApplicationConstants.ERROR_KEY, "delete user");
        }
    }

    private ModelAndView updateUser(Long id, Function<User, User> function) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/users");
        User updatingUser = userService.getById(id);
        updatingUser = function.apply(updatingUser);

        profileService.update(updatingUser.getProfile());
        return modelAndView;
    }

    @GetMapping("/message/{id}")
    public ModelAndView loadMessagePage(@PathVariable Long id) {
        try {
            Message message = messageService.getById(id);
            ModelAndView modelAndView = new ModelAndView("messageCard");
            modelAndView.addObject(ApplicationConstants.MESSAGE_KEY, message);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in loadMessagePage method ", e);
            return new ModelAndView("redirect:/error");
        }
    }

    @GetMapping("/message/delete/{id}")
    public ModelAndView deleteMessage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/messages");
        log.info("admin tries delete message with id {}", id);
        try {
            messageService.delete(id);
            log.info("admin successfully delete message with id {} ", id);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in deleteMessage method ", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, "deleting message");
            return modelAndView;
        }
    }

    @GetMapping("/user/{id}")
    public ModelAndView loadUserPage(@PathVariable Long id) {
        try {
            User user = userService.getById(id);
            ModelAndView modelAndView = new ModelAndView("userCard");
            modelAndView.addObject(ApplicationConstants.USER_KEY, user);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in loadUserPage method ", e);
            return new ModelAndView("redirect:/error");
        }
    }
}