package com.taxah.diplom_client.controller;

import com.taxah.diplom_client.model.calculate.Debt;
import com.taxah.diplom_client.model.dataBase.*;
import com.taxah.diplom_client.model.dataBase.dto.PayFactDTO;
import com.taxah.diplom_client.model.dataBase.dto.ProductUsingDTO;
import com.taxah.diplom_client.service.feign.CalculateFeign;
import com.taxah.diplom_client.service.feign.DataBaseFeign;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for working with the client page.
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/client")
public class ClientController {
    private final DataBaseFeign apiDbService;
    private final CalculateFeign apiCalculateService;

    // region Session

    /**
     * Home page.
     *
     * @return the home page
     */
    @GetMapping
    public String home() {
        return "home";
    }

    /**
     * Get session by id.
     *
     * @param id    the session id
     * @param model the model
     * @return the session page
     */
    @GetMapping("/session/{id}")
    public String getSession(@PathVariable Long id, Model model) {
        Session mySession = apiDbService.getSession(id);
        if (mySession != null) {
            model.addAttribute("mySession", mySession);
            return "model";
        }
        return "home";
    }

    /**
     * Find session by id.
     *
     * @param id the session id
     * @return the session page
     */
    @PostMapping("/session/find")
    public String findSession(@RequestParam("id") Long id) {
        if (id == null) {
            return "redirect:/error";
        }
        return "redirect:/client/session/" + id;
    }

    /**
     * Find session by name. Ignore case.
     *
     * @param sessionName the session name
     * @param model       the model
     * @return the session list page
     */
    @PostMapping("/session/findByName")
    public String findByName(@RequestParam("sessionName") String sessionName, Model model) {
        List<Session> sessions = apiDbService.findByName(sessionName);
        model.addAttribute("sessions", sessions);
        return "sessionList";
    }

    /**
     * Create session.
     *
     * @param firstname   the first name
     * @param lastname    the last name
     * @param sessionName the session name
     * @param model       the model
     * @return the session page
     */
    @PostMapping("/session/create")
    public String createSession(@RequestParam("firstname") String firstname,
                                @RequestParam("lastname") String lastname,
                                @RequestParam("sessionName") String sessionName,
                                Model model) {
        Session session = apiDbService.createSession(firstname, lastname, sessionName);
        model.addAttribute("mySession", session);
        return "redirect:/client/session/" + session.getId();
    }

    /**
     * Add member to meeting members list.
     *
     * @param tempUser the member
     * @return the session page
     */
    @PostMapping("/session/member/add")
    public String addMember(TempUser tempUser) {
        apiDbService.addMember(tempUser);
        return "redirect:/client/session/" + tempUser.getSessionId();
    }

    /**
     * Delete member from meeting members list.
     *
     * @param id the member id
     * @return the session page
     */
    @DeleteMapping("/session/member/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        Long sessionId = apiDbService.deleteMember(id);
        return "redirect:/client/session/" + sessionId;
    }
//endregion

    // region Check

    /**
     * Create check with the specified name and session id.
     *
     * @param checkName check name
     * @param sessionId session id
     * @return the session page
     */
    @PostMapping("/check")
    public String checkCreate(@RequestParam("checkName") String checkName,
                              @RequestParam("sessionId") Long sessionId) {
        apiDbService.createCheck(checkName, sessionId);
        return "redirect:/client/session/" + sessionId;
    }

    /**
     * Delete check by id.
     *
     * @param id check id
     * @return the session page
     */
    @DeleteMapping("/check/{id}")
    public String deleteCheck(@PathVariable Long id) {
        Long sessionId = apiDbService.deleteCheck(id);
        return "redirect:/client/session/" + sessionId;
    }
//endregion

    // region Pay fact

    /**
     * Represents the add pay fact page.
     *
     * @param sessionId session id
     * @param checkId   check id
     * @param model     the model
     * @return the add pay fact page
     */
    @PostMapping("/payFact")
    public String payFactFormAdd(@RequestParam("sessionId") Long sessionId,
                                 @RequestParam("checkId") Long checkId,
                                 Model model) {
        Session mySession = apiDbService.getSession(sessionId);
        model.addAttribute("mySession", mySession);
        model.addAttribute("checkId", checkId);
        return "payFact/addPayFact";
    }

    /**
     * Add pay fact.
     *
     * @param payFactDTO pay fact data
     * @param sessionId  session id
     * @return the session page
     */
    @PostMapping("/payFact/add")
    public String addPayFact(@ModelAttribute PayFactDTO payFactDTO, @RequestParam("sessionId") Long sessionId) {
        apiDbService.addPayFact(payFactDTO);
        return "redirect:/client/session/" + sessionId;
    }

    /**
     * Represents the update pay fact page.
     *
     * @param sessionId session id
     * @param payFactId pay fact id
     * @param checkId   check id
     * @param model     the model
     * @return the update pay fact page
     */
    @PostMapping("/payFact/update")
    public String payFactFormUpdate(@RequestParam("sessionId") Long sessionId,
                                    @RequestParam("payFactId") Long payFactId,
                                    @RequestParam("checkId") Long checkId, Model model) {
        Session session = apiDbService.getSession(sessionId);
        PayFact payFact = apiDbService.getPayFact(payFactId);
        payFact.setCheckId(checkId);
        model.addAttribute("mySession", session);
        model.addAttribute("payFact", payFact);
        return "payFact/updatePayFact";
    }

    /**
     * Update pay fact.
     *
     * @param payFact    pay fact data
     * @param sessionId  session id
     * @param tempUserid temp user id
     * @return the session page
     */
    @PutMapping("/payFact/update")
    public String updatePayFact(@ModelAttribute("payFact") PayFact payFact,
                                @RequestParam("sessionId") Long sessionId,
                                @RequestParam("tempUserid") Long tempUserid) {
        TempUser tempUser = apiDbService.getTempUser(tempUserid);
        payFact.setTempUser(tempUser);
        apiDbService.updatePayFact(payFact);
        return "redirect:/client/session/" + sessionId;
    }

    /**
     * Delete pay fact by id.
     *
     * @param id pay fact id
     * @return the session page
     */
    @DeleteMapping("/payFact/{id}")
    public String deletePayFact(@PathVariable Long id) {
        Long sessionId = apiDbService.deletePayFact(id);
        return "redirect:/client/session/" + sessionId;
    }
//endregion

    // region Product using

    /**
     * Represents the create product using page.
     *
     * @param checkId   check id
     * @param sessionId session id
     * @param model     the model
     * @return the create product using page
     */
    @PostMapping("/productUsing/create")
    public String productCreateButton(@RequestParam("checkId") Long checkId,
                                      @RequestParam("sessionId") Long sessionId,
                                      Model model) {
        ProductUsing productUsing = new ProductUsing();
        productUsing.setCheckId(checkId);
        model.addAttribute("productUsing", productUsing);
        model.addAttribute("checkId", checkId);
        model.addAttribute("sessionId", sessionId);
        return "productUsing/addProductUsing";
    }

    /**
     * Create product using.
     *
     * @param productUsing product using data
     * @param sessionId    session id
     * @return the session page
     */
    @PostMapping("/productUsing/add")
    public String productCreate(@ModelAttribute ProductUsing productUsing,
                                @RequestParam("sessionId") Long sessionId) {
        ProductUsingDTO pDTO = new ProductUsingDTO();
        pDTO.setCheckId(productUsing.getCheckId());
        pDTO.setProductName(productUsing.getProductName());
        pDTO.setCost(productUsing.getCost());
        pDTO.setTempUsers(new ArrayList<>());
        ProductUsing newProductUsing = apiDbService.addProductUsing(pDTO);
        newProductUsing.setCheckId(productUsing.getCheckId());
        return "redirect:/client/session/" + sessionId;
    }

    /**
     * Represents the update product using page.
     *
     * @param sessionId      session id
     * @param productUsingId product using id
     * @param checkId        check id
     * @param model          the model
     * @return the update product using page
     */
    @PostMapping("/productUsing/update")
    public String updateProductUsing(@RequestParam("sessionId") Long sessionId,
                                     @RequestParam("productUsingId") Long productUsingId,
                                     @RequestParam("checkId") Long checkId,
                                     Model model) {
        ProductUsing productUsing = apiDbService.getProductUsing(productUsingId);
        productUsing.setCheckId(checkId);
        model.addAttribute("productUsing", productUsing);
        model.addAttribute("sessionId", sessionId);
        return "productUsing/updateProductUsing";
    }

    /**
     * Update product using.
     *
     * @param productUsing product using data
     * @param sessionId    session id
     * @return the session page
     */
    @PutMapping("/productUsing/update")
    public String updateProductUsing(@ModelAttribute("productUsing") ProductUsing productUsing,
                                     @RequestParam("sessionId") Long sessionId) {
        apiDbService.updateProductUsing(productUsing);
        return "redirect:/client/session/" + sessionId;
    }

    /**
     * Delete product using by id.
     *
     * @param productUsingId product using id
     * @param sessionId      session id
     * @return the session page
     */
    @DeleteMapping("/productUsing/delete/{id}")
    public String deleteProductUsing(@PathVariable(name = "id") Long productUsingId,
                                     @RequestParam("sessionId") Long sessionId) {
        apiDbService.deleteProductUsing(productUsingId);
        return "redirect:/client/session/" + sessionId;
    }

    // region Temp user Product using

    /**
     * Add or delete temp user to/from product using list.
     *
     * @param action         action
     * @param sessionId      session id
     * @param productUsingId product using id
     * @param userId         user id
     * @return the session page
     */
    @PostMapping("/productUsing/tempUser/addAndDelete")
    public String addAndDeleteTempUser(@RequestParam("action") String action,
                                       @RequestParam("sessionId") Long sessionId,
                                       @RequestParam("productUsingId") Long productUsingId,
                                       @RequestParam("userId") Long userId) {
        if ("add".equals(action)) {
            TempUser tempUser = apiDbService.getTempUser(userId);
            apiDbService.addTempUserToProduct(productUsingId, tempUser);
        } else if ("delete".equals(action)) {
            TempUser tempUser = apiDbService.getTempUser(userId);
            apiDbService.deleteTempUserFromProduct(productUsingId, tempUser);
        }
        return "redirect:/client/session/" + sessionId;
    }

    /**
     * Add all members to product using list.
     *
     * @param productUsingId product using id
     * @param sessionId      session id
     * @return the session page
     */
    @PostMapping("/productUsing/tempUser/addAll")
    public String addAllMembersToProductUsing(@RequestParam("productUsingId") Long productUsingId,
                                              @RequestParam("sessionId") Long sessionId) {
        apiDbService.addAllMembersToProduct(productUsingId, sessionId);
        return "redirect:/client/session/" + sessionId;
    }

    /**
     * Represents the add temp user to product using page.
     *
     * @param tempUser the temp user
     * @param model    the model
     * @return the add temp user to product using page
     */
    @PostMapping("/tempUser/update")
    public String updateTempUserButton(TempUser tempUser,
                                       Model model) {
        model.addAttribute("tempUser", tempUser);
        return "updateTempUser";
    }

    /**
     * Update temp user.
     *
     * @param tempUser temp user data
     * @return the session page
     */
    @PutMapping("/tempUser/update")
    public String updateTempUser(TempUser tempUser) {
        apiDbService.updateMember(tempUser.getId(), tempUser);
        return "redirect:/client/session/" + tempUser.getSessionId();
    }
    //endregion
//endregion

    // region Other

    /**
     * This method get and check session and then Calculate debts.
     *
     * @param sessionId session id
     * @param model     the model
     * @return the result page
     */
    @PostMapping("/calc/execute")
    public String calculateSession(@RequestParam("sessionId") Long sessionId, Model model) {
        try {
            Session session = apiDbService.getSession(sessionId);
            apiCalculateService.validateSession(session);
            List<Debt> debtList = apiCalculateService.calculate(session);
            model.addAttribute("mySession", session);
            model.addAttribute("debtList", debtList);
            return "result";
        } catch (FeignException e) {
            model.addAttribute("error", extractBodyMessage(e.getMessage()));
            return "error";
        }
    }

    /**
     * This is auxiliary method for extracting the message from the exception.
     *
     * @param exceptionMessage exception message
     * @return the extracted message
     */
    private String extractBodyMessage(String exceptionMessage) {
        String[] parts = exceptionMessage.split(":");
        String finalErrorMessage = parts[parts.length - 1].trim();
        return finalErrorMessage.substring(1, finalErrorMessage.length() - 1);
    }
//endregion
}
