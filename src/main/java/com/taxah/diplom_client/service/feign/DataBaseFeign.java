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

    @GetMapping("/session/findByName")
    List<Session> findByName(@RequestParam("sessionName") String sessionName);

    @GetMapping("/check/{id}")
    Check getCheck(@PathVariable Long id);

    @GetMapping("/payfact/{id}")
    PayFact getPayFact(@PathVariable Long id);

    @GetMapping("/session/tempuser/{id}")
    TempUser getTempUser(@PathVariable Long id);

    @GetMapping("/product/{id}")
    ProductUsing getProductUsing(@PathVariable Long id);

    @PostMapping("/session/create/{id}")
    Long createNewSession(@RequestBody List<TempUser> accounts, @PathVariable Long id);

    @PostMapping("/session/create/")
    Session createSession(@RequestParam("firstname") String firstname,
                          @RequestParam("lastname") String lastname,
                          @RequestParam("sessionName") String sessionName);

    @PostMapping("/session/add/payfact")
    PayFact addPayFact(@RequestBody PayFactDTO p);

    @PostMapping("/session/add/check/{sessionId}")
    Long createCheck(@RequestParam String name, @PathVariable Long sessionId);

    @PostMapping("/session/add/productusing")
    ProductUsing addProductUsing(@RequestBody ProductUsingDTO p);

    @PostMapping("/user/add")
    User addUser(@RequestBody User user);

    @PostMapping("/users/add/temp_user")
    TempUser addGuestMember(@RequestBody TempUser tempUser);

    @PostMapping("/productusing/add/{productUsingId}")
    Long addTempUserToProduct(@PathVariable Long productUsingId, @RequestBody TempUser tempUser);

    @DeleteMapping("/users/delete/{id}")
    Long deleteMember(@PathVariable Long id);

    @DeleteMapping("/payfact/delete/{id}")
    Long deletePayFact(@PathVariable Long id);

    @DeleteMapping("/check/delete/{id}")
    Long deleteCheck(@PathVariable Long id);

    @DeleteMapping("/productusing/delete/{id}")
    void deleteProductUsing(@PathVariable(name = "id") Long productUsingId);

    @DeleteMapping("/productusing/delete/user/{productUsingId}")
    void deleteTempUserFromProduct(@PathVariable Long productUsingId, @RequestBody TempUser tempUser);

    @PutMapping("/payfact/update")
    PayFact updatePayFact(@RequestBody PayFact payFact);

    @PutMapping("/product/update")
    ProductUsing updateProductUsing(@RequestBody ProductUsing productUsing);
}
