package ru.osipov.springsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class MyController {

    final String LINKS = "<br><br><a href=http://localhost:8080>Доступ всем</a>" +
            "<br><br><a href=http://localhost:8080/onlyAdminRead>Только Админиы и Чтение</a>" +
            "<br><br><a href=http://localhost:8080/onlyAdminWrite>Только Админиы и Запись</a>" +
            "<br><br><a href=http://localhost:8080/onlyUserDelete>Только Юзеры и Удаляторы</a>" +
            "<br><br><a href=http://localhost:8080/logout>Логаут</a>";


    @GetMapping()
    public String getAll() {
        return "Привет всем!" + LINKS;
    }

    @GetMapping("onlyAdminRead")
    @Secured({"ROLE_ADMIN", "ROLE_READ"})
    public String getOnlyAdminRead() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var rights = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return "Привет <b>" + username + "</b> ты имеешь роли: " + rights +
                "<br>метод имеет права: Only ADMIN and READ!" + LINKS +
                "<br><br><a href=http://localhost:8080/another?username=" + username + ">Запустить еще один метод под своим именем</a>" +
                "<br><a href=http://localhost:8080/another?username=Glafira>Запустить еще один метод под именем \"Glafira\"</a>";
    }

    @GetMapping("onlyAdminWrite")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_WRITE"})
    public String getOnlyAdminWrite() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var rights = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return "Привет <b>" + username + "</b> ты имеешь роли: " + rights +
                "<br>метод имеет права: Only ADMIN and WRITE!" + LINKS +
                "<br><br><a href=http://localhost:8080/another?username=" + username + ">Запустить еще один метод под своим именем</a>" +
                "<br><a href=http://localhost:8080/another?username=Glafira>Запустить еще один метод под именем \"Glafira\"</a>";

    }

    @GetMapping("onlyUserDelete")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DELETE')")
    public String getOnlyUsersDelete() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var rights = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return "Привет <b>" + username + "</b> ты имеешь роли: " + rights +
                "<br>метод имеет права: Only USER and DELETE!" + LINKS +
                "<br><br><a href=http://localhost:8080/another?username=" + username + ">Запустить еще один метод под своим именем</a>" +
                "<br><a href=http://localhost:8080/another?username=Glafira>Запустить еще один метод под именем \"Glafira\"</a>";
    }

    @GetMapping("another")
    @PreAuthorize("#username == authentication.principal.username")
    public String getAnother(@RequestParam String username) {
        var uname = SecurityContextHolder.getContext().getAuthentication().getName();
        var rights = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return "Привет <b>" + username + "</b> ты имеешь роли: " + rights +
                "<br>метод имеет права: Only " + uname + LINKS +
                "<br><br><a href=http://localhost:8080/another?username=" + username + ">Запустить еще один метод под своим именем</a>" +
                "<br><a href=http://localhost:8080/another?username=Glafira>Запустить еще один метод под именем \"Glafira\"</a>";
    }
}