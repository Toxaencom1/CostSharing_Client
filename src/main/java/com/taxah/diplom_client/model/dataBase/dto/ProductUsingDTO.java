package com.taxah.diplom_client.model.dataBase.dto;


import com.taxah.diplom_client.model.dataBase.TempUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data transfer object for ProductUsing
 */
@Data
@NoArgsConstructor
public class ProductUsingDTO {
    private Long checkId;
    private String productName;
    private Double cost;
    private List<TempUser> tempUsers;

    public ProductUsingDTO(Long checkId, String productName, Double cost, List<TempUser> tempUsers) {
        this.checkId = checkId;
        this.productName = productName;
        this.cost = cost;
        this.tempUsers = tempUsers;
    }
}
