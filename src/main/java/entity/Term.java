package entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Term {
    private int id;
    private String name;
    private String duration;
    private int status;
    private ArrayList<Discipline> disciplines = new ArrayList<>();
}
