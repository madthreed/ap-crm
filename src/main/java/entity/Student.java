package entity;

import lombok.Data;

import java.sql.Date;

/**
 * Created by MadThreeD on 2022.
 */

@Data
public class Student {
    private int id;
    private String name;
    private String surname;
    private String group;
    private Date date;
    private int status = 1;
}
