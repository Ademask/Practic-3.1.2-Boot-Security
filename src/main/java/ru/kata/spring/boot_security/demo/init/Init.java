package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Init {
    private final RoleServiceImpl roleServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public Init(RoleServiceImpl roleServiceImpl, UserServiceImpl userServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @PostConstruct
    private void postConstruct() {
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        List<Role> userList = new ArrayList<>();
        List<Role> adminList = new ArrayList<>();
        userList.add(roleUser);
        adminList.add(roleUser);
        adminList.add(roleAdmin);

        if (roleServiceImpl.getAll().isEmpty()) {
            roleServiceImpl.add(roleUser);
            roleServiceImpl.add(roleAdmin);
        }

        User user = new User("user", 1999, "user");
        User admin = new User("admin", 1999, "admin");
        user.setRoles(userList);
        admin.setRoles(adminList);
        userServiceImpl.save(user);
        userServiceImpl.save(admin);
    }
}
