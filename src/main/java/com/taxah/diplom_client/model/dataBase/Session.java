package com.taxah.diplom_client.model.dataBase;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private Long id;

    private Long adminId;

    private List<TempUser> membersList = new ArrayList<>();

    private List<Check> checkList = new ArrayList<>();;

    private boolean isClosed;

}
