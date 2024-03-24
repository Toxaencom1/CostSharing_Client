package com.taxah.diplom_client.model.dataBase;

import com.taxah.diplom_client.model.dataBase.abstractClasses.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class for working with Rest API User
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends Account {
    public User(String firstname, String lastname) {
        super(firstname, lastname);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
