package com.taxah.diplom_client.model.dataBase;

import com.taxah.diplom_client.model.dataBase.abstractClasses.Account;
import lombok.*;

/**
 * Class for working with Rest API TempUser
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TempUser extends Account {

    private Long sessionId;

    public TempUser(Long sessionId, String firstName, String lastName) {
        super(firstName, lastName);
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return super.toString() +
                "sessionId=" + sessionId +
                "}";
    }
}
