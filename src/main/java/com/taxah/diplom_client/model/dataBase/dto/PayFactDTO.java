package com.taxah.diplom_client.model.dataBase.dto;

import lombok.Data;

/**
 * Data transfer object for PayFact
 */
@Data
public class PayFactDTO {
    Long tempUserId;
    Double amount;
    Long checkId;
}
