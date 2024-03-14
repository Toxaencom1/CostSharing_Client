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

@RequiredArgsConstructor
@Controller
@RequestMapping("/client")
public class ClientController {
    private final DataBaseFeign apiDbService;
    private final CalculateFeign apiCalculateService;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/session/{id}")
    public String getSession(@PathVariable Long id, Model model) {
        Session mySession = apiDbService.getSession(id);
        if (mySession != null) {
            model.addAttribute("mySession", mySession);
            return "model";
        }
        return "home";
    }

    @PostMapping("/session/find")
    public String findSession(@RequestParam("id") Long id) {
        if (id == null) {
            return "redirect:/error";
        }
        return "redirect:/client/session/" + id;
    }

    @PostMapping("/session/findByName")
    public String findByName(@RequestParam("sessionName") String sessionName,Model model){
        List<Session> sessions = apiDbService.findByName(sessionName);
        model.addAttribute("sessions", sessions);
        return "sessionList";
    }

    @PostMapping("/session/create")
    public String createSession(@RequestParam("firstname") String firstname,
                                @RequestParam("lastname") String lastname,
                                @RequestParam("sessionName") String sessionName,
                                Model model) {
        Session session = apiDbService.createSession(firstname,lastname,sessionName);
        model.addAttribute("mySession", session);
        return "model";
    }

    @PostMapping("session/check")
    public String checkCreate(@RequestParam("checkName") String checkName,
                              @RequestParam("sessionId") Long sessionId) {
        apiDbService.createCheck(checkName, sessionId);
        return "redirect:/client/session/" + sessionId;
    }

    @PostMapping("/session/add/temp_user")
    public String addMember(TempUser tempUser) {
        apiDbService.addGuestMember(tempUser);
        return "redirect:/client/session/" + tempUser.getSessionId();
    }

    @PostMapping("/session/payfact")
    public String payFactFormAdd(@RequestParam("sessionId") Long sessionId,
                                 @RequestParam("checkId") Long checkId,
                                 Model model) {
        Session mySession = apiDbService.getSession(sessionId);
        model.addAttribute("mySession", mySession);
        model.addAttribute("checkId", checkId);
        return "/payFact/addPayFact";
    }

    @PostMapping("/session/add/payfact")
    public String addPayFact(@ModelAttribute PayFactDTO payFactDTO, @RequestParam("sessionId") Long sessionId) {
        apiDbService.addPayFact(payFactDTO);
        return "redirect:/client/session/" + sessionId;
    }

    @PostMapping("/session/update/payfact")
    public String payFactFormUpdate(@RequestParam("sessionId") Long sessionId,
                                    @RequestParam("payFactId") Long payFactId,
                                    @RequestParam("checkId") Long checkId, Model model) {
        Session session = apiDbService.getSession(sessionId);
        PayFact payFact = apiDbService.getPayFact(payFactId);
        payFact.setCheckId(checkId);
        model.addAttribute("mySession", session);
        model.addAttribute("payFact", payFact);
        return "/payFact/updatePayFact";
    }

    @PostMapping("/calc/execute")
    public String calculateSession(@RequestParam("sessionId") Long sessionId, Model model) {
        try {
            Session session = apiDbService.getSession(sessionId);
            apiCalculateService.validateSession(session);
            List<Debt> debtList = apiCalculateService.calculate(session);
            model.addAttribute("mySession", session);
            model.addAttribute("debtList", debtList);
            return "result";
        }catch (FeignException e){
            model.addAttribute("error", extractBodyMessage(e.getMessage()));
            return "error";
        }
    }

    private String extractBodyMessage(String exceptionMessage){
        String[] parts = exceptionMessage.split(":");
        String finalErrorMessage = parts[parts.length - 1].trim();;
        return finalErrorMessage.substring(1, finalErrorMessage.length() - 1);
    }

    @PostMapping("/product/create")
    public String productCreateButton(@RequestParam("checkId") Long checkId,
                                      @RequestParam("sessionId") Long sessionId,
                                      Model model) {
        ProductUsing productUsing = new ProductUsing();
        productUsing.setCheckId(checkId);
        model.addAttribute("productUsing", productUsing);
        model.addAttribute("checkId", checkId);
        model.addAttribute("sessionId", sessionId);
        return "/productUsing/addProductUsing";
    }

    @PostMapping("/addProduct")
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

    @PostMapping("/updateProduct")
    public String updateProductUsing(@RequestParam("sessionId") Long sessionId,
                                     @RequestParam("productUsingId") Long productUsingId,
                                     @RequestParam("checkId") Long checkId,
                                     Model model) {
        ProductUsing productUsing = apiDbService.getProductUsing(productUsingId);
        productUsing.setCheckId(checkId);
        model.addAttribute("productUsing", productUsing);
        model.addAttribute("sessionId", sessionId);
        return "/productUsing/updateProductUsing";
    }

    @PutMapping("/session/update/payfact/up")
    public String updatePayFact(@ModelAttribute("payFact") PayFact payFact,
                                @RequestParam("sessionId") Long sessionId,
                                @RequestParam("tempUserid") Long tempUserid) {
        TempUser tempUser = apiDbService.getTempUser(tempUserid);
        payFact.setTempUser(tempUser);
        apiDbService.updatePayFact(payFact);
        return "redirect:/client/session/" + sessionId;
    }

    @PutMapping("/updateProduct/up")
    public String updateProductUsing(@ModelAttribute("productUsing") ProductUsing productUsing,
                                     @RequestParam("sessionId") Long sessionId) {
        apiDbService.updateProductUsing(productUsing);
        return "redirect:/client/session/" + sessionId;
    }

    @DeleteMapping("/session/member/{id}")
    public String deleteMember(@PathVariable Long id) {
        Long sessionId = apiDbService.deleteMember(id);
        return "redirect:/client/session/" + sessionId;
    }

    @DeleteMapping("/session/check/{id}")
    public String deleteCheck(@PathVariable Long id) {
        Long sessionId = apiDbService.deleteCheck(id);
        return "redirect:/client/session/" + sessionId;
    }

    @DeleteMapping("/session/payfact/{id}")
    public String deletePayFact(@PathVariable Long id) {
        Long sessionId = apiDbService.deletePayFact(id);
        return "redirect:/client/session/" + sessionId;
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProductUsing(@PathVariable(name = "id") Long productUsingId,
                                     @RequestParam("sessionId") Long sessionId) {
        apiDbService.deleteProductUsing(productUsingId);
        return "redirect:/client/session/" + sessionId;
    }

    @PostMapping("/productusing/addAndDelete")
    public String handleForm(@RequestParam("action") String action,
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
}
