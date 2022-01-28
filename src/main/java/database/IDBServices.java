package database;

import entity.Discipline;
import entity.Student;
import entity.Term;

import java.sql.SQLException;
import java.util.List;

public interface IDBServices {
    List<Discipline> getAllActiveDisciplines() throws SQLException;

    void createDiscipline(String name) throws SQLException;

    void modifyDisciplineById(String id, String newName) throws SQLException;

    void deleteDisciplineById(String id) throws SQLException;

    Discipline getDisciplineById(String id) throws SQLException;

    List<Term> getAllActiveTerms() throws SQLException;

    Term getTermById(String id) throws SQLException;

    List<Discipline> getDisciplinesByTerm(String idTerm) throws SQLException;

    void createTerm(String duration, String idsDisc) throws SQLException;

    void modifyTermById(String idTerm, String newDuration, String newIdsDisc);

    List<Student> getAllActiveStudents() throws SQLException;

    void createStudent(String surname, String name, String group, String date) throws SQLException;

    void deleteStudentById(String id) throws SQLException;
    //TODO еще не все


}
