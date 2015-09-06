package br.com.simple.tests.springboot.services;


import br.com.simple.tests.springboot.model.User;
import br.com.simple.tests.springboot.model.UserRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        userRepository.save(new User("Joao", "Silva"));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<User> listUsers() {
        return IteratorUtils.toList(userRepository.findAll().iterator());
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User findUser(@PathVariable BigInteger id) {
        return userRepository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable BigInteger id) {
        userRepository.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public void updateUser(@PathVariable BigInteger id, @RequestBody User user) {
        user.setId(id);
        userRepository.save(user);
    }

}
