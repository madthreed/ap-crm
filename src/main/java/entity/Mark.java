package entity;

import lombok.Data;

@Data
public class Mark {
    private int id;
    private Student student;
    private Term term;
    private Discipline discipline;
    private int mark;
}
