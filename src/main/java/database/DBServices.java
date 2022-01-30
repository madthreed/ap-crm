package database;

import constants.DbConfig;
import entity.Discipline;
import entity.Student;
import entity.Term;

import java.sql.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public String dateToDB(String date) {
        DateFormat format = new SimpleDateFormat("mm/dd/yy", Locale.ENGLISH);
        Date dateFromUser = null;

        try {
            dateFromUser = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(dateFromUser);
    }

    @Override
    public String dateFromDB(String date) {
        return null;
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
    public void modifyDisciplineById(String id, String name) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("update discipline set `discipline`= '" + name + "' where (`id`=" + id + ")");

        stmt.close();
    }

    //TODO Change STATUS=0, not DELETE
    @Override
    public void deleteDisciplineById(String id) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("update `students24`.`discipline` set `status` = '0' where id = " + id + "");
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

        return terms;
    }

    @Override
    public Term getFirstActiveTerm() throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from term where status = '1' order by id limit 1");

        Term term = new Term();

        while (rs.next()) {
            term.setId(rs.getInt("id"));
            term.setName(rs.getString("term"));
            term.setDuration(rs.getString("duration"));
            term.setStatus(rs.getInt("status"));
            term.setDisciplines((ArrayList<Discipline>) getDisciplinesByTerm(String.valueOf(rs.getInt("id"))));
        }

        return term;
    }

    @Override
    public Term getLastActiveTerm() throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from term where status = '1' order by id desc limit 1");

        Term term = new Term();

        while (rs.next()) {
            term.setId(rs.getInt("id"));
            term.setName(rs.getString("term"));
            term.setDuration(rs.getString("duration"));
            term.setStatus(rs.getInt("status"));
            term.setDisciplines((ArrayList<Discipline>) getDisciplinesByTerm(String.valueOf(rs.getInt("id"))));
        }

        return term;
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

        return disciplines;
    }


    @Override
    public void createTerm(String duration, String idsDisc) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();

        Term lastActiveTerm = getLastActiveTerm();
        int nextTerm = Integer.parseInt(lastActiveTerm.getName().split(" ")[1]) + 1;
        String nextTermName = "Семестр " + nextTerm;

        stmt.execute("insert into term (`term`, `duration`) values ('" + nextTermName + "', '" + duration + "')");

        lastActiveTerm = getLastActiveTerm();

        for (String idDisc : idsDisc.split(" ")) {
            stmt.addBatch("insert into term_discipline (id_term, id_discipline) values ('"+lastActiveTerm.getId()+"','"+idDisc+"');");
        }

        stmt.executeBatch();
    }

    @Override
    public void modifyTermById(String idTerm, String newDuration, String newIdsDisc) {

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
        stmt.execute("insert into  student (`surname`, `name`, `group`, `date`) values ('" + surname + "', '" + name + "', '" + group + "', '" + dateToDB(date) + "');");
    }

    @Override
    public void deleteStudentById(String id) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("update student set `status` = '0' where id = " + id + "");
    }

    @Override
    public Student getStudentById(String id) throws SQLException {
        Student student = new Student();

        createConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from student where status='1' and id='" + id + "'");
        while (rs.next()) {
            student.setId(rs.getInt("id"));
            student.setSurname(rs.getString("surname"));
            student.setName(rs.getString("name"));
            student.setGroup(rs.getString("group"));
            student.setDate(rs.getDate("date"));
        }

        return student;
    }

    @Override
    public void modifyStudentById(String id, String surname, String name, String group, String date) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("update student set `surname` = '" + surname + "', `name` = '" + name + "', `group` = '" + group + "', `date` = '" + dateToDB(date) + "' where (`id` = '" + id + "')");
    }
}
