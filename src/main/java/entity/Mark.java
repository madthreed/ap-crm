package entity;

import lombok.Data;

public class Mark {
    private int id;
    private Student student;
    private Term term;
    private Discipline discipline;
    private int mark;

    public Mark() {
    }

    public Mark(Student student, Term term, Discipline discipline, int mark) {
        this.student = student;
        this.term = term;
        this.discipline = discipline;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
