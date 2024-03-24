package com.taxah.diplom_client.model.dataBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for working with Rest API Session
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private Long id;

    private String name;

    private LocalDate date;

    private Long adminId;

    private List<TempUser> membersList = new ArrayList<>();

    private List<Check> checkList = new ArrayList<>();

    private boolean isClosed;

}
