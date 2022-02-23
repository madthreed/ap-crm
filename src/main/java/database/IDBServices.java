package database;

import entity.*;

import java.sql.SQLException;
import java.util.List;

public interface IDBServices {
    String dateToDB(String date);

    String dateFromDB(String date);

    List<Discipline> getAllActiveDisciplines() throws SQLException;

    void createDiscipline(String name) throws SQLException;

    void modifyDisciplineById(String id, String newName) throws SQLException;

    void deleteDisciplineById(String id) throws SQLException;

    Discipline getDisciplineById(String id) throws SQLException;

    List<Term> getAllActiveTerms() throws SQLException;

    Term getFirstActiveTerm() throws SQLException;

    Term getLastActiveTerm() throws SQLException;

    Term getTermById(String id) throws SQLException;

    void deleteTermById(String id) throws SQLException;

    List<Discipline> getDisciplinesByTerm(String idTerm) throws SQLException;

    void createTerm(String duration, String idsDisc) throws SQLException;

    void modifyTermById(String idTerm, String newDuration, String newIdsDisc) throws SQLException;

    List<Student> getAllActiveStudents() throws SQLException;

    void createStudent(String surname, String name, String group, String date) throws SQLException;

    void deleteStudentById(String id) throws SQLException;

    Student getStudentById(String id) throws SQLException;

    void modifyStudentById(String id, String surname, String name, String group, String date) throws SQLException;

    List<Role> getAllRoles() throws SQLException;

    boolean authUser(String login, String password, String role) throws SQLException;

    List<Mark> getMarksByStudentAndTermId(String studentId, String termId) throws SQLException;

//    List<Mark> getAllMarksByTermId(String studentId) throws SQLException;
    //TODO еще не все
}
