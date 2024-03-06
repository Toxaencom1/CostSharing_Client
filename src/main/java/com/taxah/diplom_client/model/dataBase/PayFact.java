package com.taxah.diplom_client.model.dataBase;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class PayFact {
    private Long id;
    private Long checkId;
    private TempUser tempUser;
    private double amount;
}
