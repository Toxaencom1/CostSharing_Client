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
    @JsonIdentityReference(alwaysAsId = true)
    @ToString.Exclude
    private Session session;
    private String userData;
    private Long userId;
    private double amount;
}
