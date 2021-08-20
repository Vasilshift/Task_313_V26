package web.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.User;
import web.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final UserService userService;
//    private final RoleService roleService;

    //private final InitServiceImpl initServiceImpl;

    @Autowired
    public MyRestController(UserService userService) {
        this.userService = userService;
//        this.roleService = roleService;

    }

//    @PostConstruct
//    public void firstInitialization() {
//        initServiceImpl.initServiceImpl();
//    }

    @GetMapping("/users")
    public List<User> apiGetAllUsers() {
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping("users/{id}")
    public User get(@PathVariable Long id) {
        User user = userService.findById(id);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(user);
            System.out.println("ResultingJSONstring = " + json);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        if(user == null){
//            throw new NoSuchUserException("There is no user with id "+ id);
//        }
        return user;
    }


}
