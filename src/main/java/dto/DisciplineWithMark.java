package dto;

import entity.Discipline;
import entity.Mark;

/**
 * Created by MadThreeD on 2022.
 */

public class DisciplineWithMark {
    private Discipline discipline;
    private Mark mark;

    public DisciplineWithMark() {
    }

    public DisciplineWithMark(Discipline discipline, Mark mark) {
        this.discipline = discipline;
        this.mark = mark;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
