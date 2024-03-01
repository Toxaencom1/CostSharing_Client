package com.taxah.diplom_client.service.feign;

import com.taxah.diplom_client.model.dataBase.*;
import com.taxah.diplom_client.model.dataBase.dto.PayFactDTO;
import com.taxah.diplom_client.model.dataBase.dto.ProductUsingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "DataBase", url = "http://localhost:8765/db/")
public interface DataBaseFeign {
    @GetMapping("/session/{id}")
    Session getSession(@PathVariable Long id);

    @PostMapping("/session/create/{id}")
    Long createNewSession(@RequestBody List<TempUser> accounts, @PathVariable Long id);

    @PostMapping("/session/add/payfact")
    List<PayFact> addPayFact(@RequestBody PayFactDTO p);

    @PostMapping("/session/add/check/{sessionId}")
    Long createCheck(@RequestParam String name, @PathVariable Long sessionId);

    @PostMapping("/session/add/productusing")
    List<ProductUsing> addProductUsing(@RequestBody ProductUsingDTO p);

    @PostMapping("/user/add")
    User addUser(@RequestBody User user);
}
