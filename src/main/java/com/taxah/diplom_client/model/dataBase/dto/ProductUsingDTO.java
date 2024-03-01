package com.taxah.diplom_client.model.dataBase.dto;


import com.taxah.diplom_client.model.dataBase.TempUser;
import lombok.Data;

import java.util.List;


@Data
public class ProductUsingDTO {
    private Long checkId;
    private String productName;
    private Double cost;
    private List<TempUser> tempUsers;
}
