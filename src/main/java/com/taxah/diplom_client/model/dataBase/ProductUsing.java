package com.taxah.diplom_client.model.dataBase;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


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