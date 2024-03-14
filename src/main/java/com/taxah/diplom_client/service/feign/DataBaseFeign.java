package com.taxah.diplom_client.service.feign;

import com.taxah.diplom_client.model.dataBase.*;
import com.taxah.diplom_client.model.dataBase.dto.PayFactDTO;
import com.taxah.diplom_client.model.dataBase.dto.ProductUsingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "DataBase", url = "http://localhost:8765/db/")
public interface DataBaseFeign {
    // region Session
    @GetMapping("/session/{id}")
    Session getSession(@PathVariable Long id);

    @GetMapping("/session/findByName")
    List<Session> findByName(@RequestParam("sessionName") String sessionName);

    @PostMapping("/session/create/{id}")
    Long createNewSession(@RequestBody List<TempUser> accounts, @PathVariable Long id);

    @PostMapping("/session/create")
    Session createSession(@RequestParam("firstname") String firstname,
                          @RequestParam("lastname") String lastname,
                          @RequestParam("sessionName") String sessionName);

    @PostMapping("/users/add")
    User addUser(@RequestBody User user);
//endregion

    // region Check
    @GetMapping("/check/{id}")
    Check getCheck(@PathVariable Long id);

    @PostMapping("/check/add/{sessionId}")
    Long createCheck(@RequestParam String name, @PathVariable Long sessionId);

    @DeleteMapping("/check/delete/{checkId}")
    Long deleteCheck(@PathVariable Long checkId);
//endregion

    // region Pay fact
    @GetMapping("/payFact/{id}")
    PayFact getPayFact(@PathVariable Long id);

    @PostMapping("/payFact/add")
    PayFact addPayFact(@RequestBody PayFactDTO p);

    @DeleteMapping("/payFact/delete/{id}")
    Long deletePayFact(@PathVariable Long id);

    @PutMapping("/payFact/update")
    PayFact updatePayFact(@RequestBody PayFact payFact);

//endregion

    // region Product using
    @GetMapping("/productUsing/{id}")
    ProductUsing getProductUsing(@PathVariable Long id);

    @PostMapping("/productUsing/add")
    ProductUsing addProductUsing(@RequestBody ProductUsingDTO p);

    @DeleteMapping("/productUsing/delete/{id}")
    void deleteProductUsing(@PathVariable(name = "id") Long productUsingId);

    @PutMapping("/productUsing/update")
    ProductUsing updateProductUsing(@RequestBody ProductUsing productUsing);

    // region Product using Temp user
    @GetMapping("/tempUser/{id}")
    TempUser getTempUser(@PathVariable Long id);

    @PostMapping("/tempUser/add")
    TempUser addMember(@RequestBody TempUser tempUser);

    @DeleteMapping("/tempUser/member/delete/{id}")
    Long deleteMember(@PathVariable Long id);

    @PostMapping("/tempUser/add/{productUsingId}")
    Long addTempUserToProduct(@PathVariable Long productUsingId, @RequestBody TempUser tempUser);

    @DeleteMapping("/tempUser/delete/{productUsingId}")
    void deleteTempUserFromProduct(@PathVariable Long productUsingId,
                                   @RequestBody TempUser tempUser);
    //endregion
//endregion
}
