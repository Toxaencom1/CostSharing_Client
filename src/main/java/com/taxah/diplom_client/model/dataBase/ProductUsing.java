package com.taxah.diplom_client.model.dataBase;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

/**
 * Class for working with Rest API ProductUsing
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUsing {
    private Long id;
    private Long checkId;
    private String productName;
    private Double cost;
    private List<TempUser> users;
}