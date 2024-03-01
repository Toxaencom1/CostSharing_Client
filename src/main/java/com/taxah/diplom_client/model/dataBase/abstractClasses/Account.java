package com.taxah.diplom_client.model.dataBase.abstractClasses;

import lombok.Data;

@Data
public abstract class Account {
    private Long id;
    private String firstname;
    private String lastname;
    public Account() {
    }
    protected Account(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
