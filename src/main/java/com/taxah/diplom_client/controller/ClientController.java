package com.taxah.diplom_client.controller;

import com.taxah.diplom_client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService service;

    @GetMapping
    public String createSession(){
        return "model";
    }
}
