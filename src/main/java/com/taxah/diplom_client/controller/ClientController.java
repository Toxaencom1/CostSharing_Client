package com.taxah.diplom_client.controller;

import com.taxah.diplom_client.model.dataBase.*;
import com.taxah.diplom_client.service.ClientService;
import com.taxah.diplom_client.service.feign.DataBaseFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService service;
    private final DataBaseFeign apiService;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/session/{id}")
    public String getSession(@PathVariable Long id, Model model) {
        Session mySession = apiService.getSession(id);
        model.addAttribute("mySession", mySession);
        return "model";
    }


    @PostMapping("/session/find")
    public String findSession(@RequestParam("id") Long id) {
        if (id == null) {
            return "redirect:/error";
        }
        return "redirect:/client/session/" + id;
    }

    @GetMapping("/session/create")
    public String createSession(@RequestBody List<TempUser> users) {
        Long id = null;
        if (users.get(0) != null) {
            id = users.get(0).getId();
        }
        apiService.createNewSession(users, id);
        return "home";
    }

    @PostMapping("/session/add/temp_user")
    public String addMember(TempUser tempUser) {
        System.out.println(apiService.addGuestMember(tempUser));
        return "redirect:/client/session/" + tempUser.getSessionId();
    }
}
