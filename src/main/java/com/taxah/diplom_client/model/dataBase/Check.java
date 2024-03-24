package com.taxah.diplom_client.model.dataBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for working with Rest API check
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Check {
    private Long id;
    private Long sessionId;
    private PayFact payFact;
    private String name;
    private List<ProductUsing> productUsingList = new ArrayList<>();

}
