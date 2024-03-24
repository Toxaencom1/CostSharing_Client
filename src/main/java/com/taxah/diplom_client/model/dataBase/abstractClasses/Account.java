package com.taxah.diplom_client.model.dataBase.abstractClasses;

import lombok.Data;

/**
 * Abstract class for all accounts for working with Rest API Database
 */
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
