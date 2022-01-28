package database;

import constants.DbConfig;
import entity.Discipline;
import entity.Student;
import entity.Term;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBServices implements IDBServices {
    private Connection connection;

    private void createConnection() {
        if (connection != null) return;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DbConfig.DB_URL, DbConfig.DB_LOGIN, DbConfig.DB_PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Discipline> getAllActiveDisciplines() throws SQLException {
        ArrayList<Discipline> disciplines = new ArrayList<>();

        createConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from discipline where status='1'");
        while (rs.next()) {
            Discipline discipline = new Discipline();
            discipline.setId(rs.getInt("id"));
            discipline.setName(rs.getString("discipline"));
            discipline.setStatus(rs.getInt("status"));
            disciplines.add(discipline);
        }

        stmt.close();

        return disciplines;
    }


    @Override
    public void createDiscipline(String name) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("insert into `discipline` (`discipline`,`status`) values ('" + name + "','1')");

        stmt.close();
    }


    @Override
    public void modifyDiscipline(String id, String newName) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("update discipline set `discipline`= '" + newName + "' where (`id`=" + id + ")");

        stmt.close();
    }

    //TODO Change STATUS=0, not DELETE
    @Override
    public void deleteDiscipline(String id) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("delete from discipline where (`id`=" + id + ")");

        stmt.close();
    }

    @Override
    public Discipline getDisciplineById(String id) throws SQLException {
        Discipline discipline = new Discipline();

        createConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from discipline where id=" + id);

        while (rs.next()) {
            discipline.setId(rs.getInt("id"));
            discipline.setName(rs.getString("discipline"));
            discipline.setStatus(rs.getInt("status"));
        }

        stmt.close();

        return discipline;
    }


    @Override
    public List<Term> getAllActiveTerms() throws SQLException {
        ArrayList<Term> terms = new ArrayList<>();

        createConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from term where status='1'");
        while (rs.next()) {
            Term term = new Term();

            term.setId(rs.getInt("id"));
            term.setName(rs.getString("term"));
            term.setDuration(rs.getString("duration"));
            term.setStatus(rs.getInt("status"));
            term.setDisciplines((ArrayList<Discipline>) getDisciplinesByTerm(String.valueOf(rs.getInt("id"))));
            terms.add(term);
        }

        stmt.close();

        return terms;
    }


    @Override
    public Term getTermById(String id) throws SQLException {
        Term term = new Term();

        createConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from term where id=" + id);

        while (rs.next()) {
            term.setId(rs.getInt("id"));
            term.setName(rs.getString("term"));
            term.setDuration(rs.getString("duration"));
            term.setStatus(rs.getInt("status"));
            term.setDisciplines((ArrayList<Discipline>) getDisciplinesByTerm(String.valueOf(rs.getInt("id"))));
        }

        stmt.close();

        return term;
    }


    @Override
    public List<Discipline> getDisciplinesByTerm(String idTerm) throws SQLException {
        ArrayList<Discipline> disciplines = new ArrayList<>();

        createConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT discipline.id,discipline.discipline,discipline.status FROM discipline\n" +
                "left join term_discipline on discipline.id=term_discipline.id_discipline\n" +
                "left join term on term_discipline.id_term=term.id\n" +
                "where term.id=" + idTerm);
        while (rs.next()) {
            Discipline discipline = new Discipline();
            discipline.setId(rs.getInt("id"));
            discipline.setName(rs.getString("discipline"));
            discipline.setStatus(rs.getInt("status"));
            disciplines.add(discipline);
        }

        stmt.close();

        return disciplines;
    }


    @Override
    public void createTerm(String duration, String idsDisc) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select term from term order by id desc limit 1");

        stmt.close();
    }


    @Override
    public void modifyTerm(String idTerm, String newDuration, String newIdsDisc) {

    }


    @Override
    public List<Student> getAllActiveStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        createConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from student where status='1'");
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setSurname(rs.getString("surname"));
            student.setName(rs.getString("name"));
            student.setGroup(rs.getString("group"));
            student.setDate(rs.getDate("date"));
//            student.setStatus(rs.getInt("status"));
            students.add(student);
        }

        return students;
    }

    @Override
    public void createStudent(String surname, String name, String group, String date) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("insert into  `student` (`surname`, `name`, `group`, `date`) values ('" + surname + "', '" + name + "', '" + group + "', '" + date + "');");
    }

    @Override
    public void deleteStudentById(String id) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("update `students24`.`student` set `status` = '0' where id = " + id + "");
    }
}
