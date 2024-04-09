package com.taxah.diplom_client.service.feign;

import com.taxah.diplom_client.model.calculate.Debt;
import com.taxah.diplom_client.model.dataBase.Session;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Feign Interface for working with the CalculateSession service
 */
@FeignClient(name = "CalculateSession", url = "http://${path.calculate}:8765/calc/")
public interface CalculateFeign {

    @PostMapping("/execute")
    List<Debt> calculate(@RequestBody Session mySession);

    @PostMapping("/validate")
    ResponseEntity<String> validateSession(@RequestBody Session mySession);
}
