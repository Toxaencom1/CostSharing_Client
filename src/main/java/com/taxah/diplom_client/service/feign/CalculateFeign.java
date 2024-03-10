package com.taxah.diplom_client.service.feign;

import com.taxah.diplom_client.model.calculate.Debt;
import com.taxah.diplom_client.model.dataBase.Session;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "CalculateSession", url = "http://localhost:8765/calc/")
public interface CalculateFeign {

    @PostMapping("/execute")
    List<Debt> calculate(@RequestBody Session mySession);
}
