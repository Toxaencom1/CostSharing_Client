package com.taxah.diplom_client.service.feign;

import com.taxah.diplom_client.model.dataBase.*;
import com.taxah.diplom_client.model.dataBase.dto.PayFactDTO;
import com.taxah.diplom_client.model.dataBase.dto.ProductUsingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign Interface for working with the DataBase service
 * <p>
 * getSession(Long id): Retrieves a session with the given ID.
 * findByName(String sessionName): Finds all sessions with the given name.
 * createNewSession(List<TempUser> accounts, Long id): Creates a new session with the given list of temporary users and ID.
 * createSession(String firstname, String lastname, String sessionName): Creates a new session with the given first name, last name, and session name.
 * addUser(User user): Adds a new user to the database.
 * getCheck(Long id): Retrieves a check with the given ID.
 * createCheck(String name, Long sessionId): Creates a new check with the given name and session ID.
 * deleteCheck(Long checkId): Deletes a check with the given ID.
 * getPayFact(Long id): Retrieves a pay fact with the given ID.
 * addPayFact(PayFactDTO p): Adds a new pay fact to the database.
 * deletePayFact(Long id): Deletes a pay fact with the given ID.
 * updatePayFact(PayFact payFact): Updates an existing pay fact in the database.
 * getProductUsing(Long id): Retrieves a product usage record with the given ID.
 * addProductUsing(ProductUsingDTO p): Adds a new product usage record to the database.
 * deleteProductUsing(Long productUsingId): Deletes a product usage record with the given ID.
 * updateProductUsing(ProductUsing productUsing): Updates an existing product usage record in the database.
 * getTempUser(Long id): Retrieves a temporary user with the given ID.
 * addMember(TempUser tempUser): Adds a new temporary user to the database.
 * deleteMember(Long id): Deletes a temporary user with the given ID.
 * updateMember(Long id, TempUser tempUser): Updates an existing temporary user in the database.
 * addTempUserToProduct(Long productUsingId, TempUser tempUser): Adds a temporary user to a product usage record.
 * addAllMembersToProduct(Long productUsingId, Long sessionId): Adds all temporary users from a session to a product usage record.
 * deleteTempUserFromProduct(Long productUsingId, TempUser tempUser): Removes a temporary user from a product usage record.
 */
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

    @PutMapping("/tempUser/member/update/{id}")
    Long updateMember(@PathVariable Long id, @RequestBody TempUser tempUser);

    @PostMapping("/tempUser/add/{productUsingId}")
    Long addTempUserToProduct(@PathVariable Long productUsingId, @RequestBody TempUser tempUser);

    @PostMapping("/tempUser/addAll/{productUsingId}")
    Long addAllMembersToProduct(@PathVariable Long productUsingId,
                                @RequestParam("sessionId") Long sessionId);

    @DeleteMapping("/tempUser/delete/{productUsingId}")
    void deleteTempUserFromProduct(@PathVariable Long productUsingId,
                                   @RequestBody TempUser tempUser);
    //endregion
//endregion
}
