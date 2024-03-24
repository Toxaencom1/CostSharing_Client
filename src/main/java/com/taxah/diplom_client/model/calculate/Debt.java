package com.taxah.diplom_client.model.calculate;


import com.taxah.diplom_client.model.dataBase.TempUser;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * Class for working with Rest API Calculate Debt
 */
@Data
@AllArgsConstructor
public class Debt {
    private TempUser toWhom;
    private Map<String, Double> debtors;
}
