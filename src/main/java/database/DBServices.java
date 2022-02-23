package database;

import constants.DbConfig;
import entity.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

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
        return date;
//        DateFormat format = new SimpleDateFormat("mm/dd/yy", Locale.ENGLISH);
//        Date dateFromUser = null;
//
//        try {
//            dateFromUser = format.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        return formatter.format(dateFromUser);
    }

    @Override
    public String dateFromDB(String date) {
        return date;
    }

    @Override
    public List<Discipline> getAllActiveDisciplines() throws SQLException {
        ArrayList<Discipline> disciplines = new ArrayList<>();

        createConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from discipline where status = '1'");
        ResultSet rs = stmt.executeQuery();

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

        PreparedStatement stmt = connection.prepareStatement("insert into `discipline` (`discipline`,`status`) values (?,'1')");
        stmt.setString(1, name);
        stmt.execute();
    }


    @Override
    public void modifyDisciplineById(String id, String name) throws SQLException {
        createConnection();

        PreparedStatement stmt = connection.prepareStatement("update discipline set discipline=? where id=?");
        stmt.setString(1, name);
        stmt.setString(2, id);
        stmt.execute();
    }

    //TODO Change STATUS=0, not DELETE
    @Override
    public void deleteDisciplineById(String id) throws SQLException {
        createConnection();

        PreparedStatement stmt = connection.prepareStatement("update discipline set status = '0' where id = ?");
        stmt.setString(1, id);
        stmt.execute();
    }

    @Override
    public Discipline getDisciplineById(String id) throws SQLException {
        Discipline discipline = new Discipline();

        createConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from discipline where id=?");
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            discipline.setId(rs.getInt("id"));
            discipline.setName(rs.getString("discipline"));
            discipline.setStatus(rs.getInt("status"));
        }

        return discipline;
    }


    @Override
    public List<Term> getAllActiveTerms() throws SQLException {
        ArrayList<Term> terms = new ArrayList<>();

        createConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from term where status='1'");
        ResultSet rs = stmt.executeQuery();

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

        PreparedStatement stmt = connection.prepareStatement("select * from term where status = '1' order by id limit 1");
        ResultSet rs = stmt.executeQuery();

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
        Term term = null;

        createConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from term where status = '1' order by id desc limit 1");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            term = new Term();

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

        PreparedStatement stmt = connection.prepareStatement("select * from term where id=?");
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

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
    public void deleteTermById(String id) throws SQLException {
        createConnection();

        PreparedStatement stmt = connection.prepareStatement("update term set `status` = '0' where id = ?");
        stmt.setString(1, id);
        stmt.execute();
    }


    @Override
    public List<Discipline> getDisciplinesByTerm(String idTerm) throws SQLException {
        ArrayList<Discipline> disciplines = new ArrayList<>();

        createConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT discipline.id,discipline.discipline,discipline.status FROM discipline left join term_discipline on discipline.id=term_discipline.id_discipline left join term on term_discipline.id_term=term.id where term.id=? and discipline.status = '1';");
        stmt.setString(1, idTerm);
        ResultSet rs = stmt.executeQuery();

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
    public List<Discipline> getDisciplinesWithMarksByStudentAndTerm(String studentId, String termId) throws SQLException {
        return null;
    }


    @Override
    public void createTerm(String duration, String idsDisc) throws SQLException {
        createConnection();
        Statement stmt = connection.createStatement();

        Term lastActiveTerm = getLastActiveTerm();

        int nextTerm;
//        int nextTerm = Integer.parseInt(lastActiveTerm.getName().split(" ")[1]) + 1;
        if (lastActiveTerm == null) {
            nextTerm = 1;
        } else {
            nextTerm = Integer.parseInt(lastActiveTerm.getName()) + 1;
        }
//        String nextTermName = "Семестр " + nextTerm;
        String nextTermName = nextTerm + "";


//        PreparedStatement stmt = connection.prepareStatement("insert into term (`term`, `duration`) values (?,?);");
//        stmt.setString(1, nextTermName);
//        stmt.setString(2, duration);
//        stmt.executeQuery();

        stmt.execute("insert into term (`term`, `duration`) values ('" + nextTermName + "', '" + duration + "')");

        lastActiveTerm = getLastActiveTerm();

        for (String idDisc : idsDisc.split(" ")) {
            stmt.addBatch("insert into term_discipline (id_term, id_discipline) values ('" + lastActiveTerm.getId() + "','" + idDisc + "');");
        }

        stmt.executeBatch();
    }

    @Override
    public void modifyTermById(String idTerm, String newDuration, String newIdsDisc, String unblockedIdsTD) throws SQLException {
        createConnection();

        PreparedStatement updateDuration = connection.prepareStatement("UPDATE term SET duration = ? WHERE id = ?;");
        updateDuration.setString(1, newDuration);
        updateDuration.setString(2, idTerm);

        Statement stmt = connection.createStatement();

        if (unblockedIdsTD.length() != 0) {
            for (String idTd : unblockedIdsTD.split(" ")) {
                stmt.addBatch("DELETE FROM term_discipline WHERE id = '" + idTd + "'");
            }
        }

        if (newIdsDisc.length() != 0) {
            for (String idDisc : newIdsDisc.split(" ")) {
                stmt.addBatch("insert into term_discipline (id_term, id_discipline) values ('" + idTerm + "','" + idDisc + "');");
            }
        }

        updateDuration.executeUpdate();
//        deleteTD.executeUpdate();
        stmt.executeBatch();
    }

    @Override
    public List<Discipline> getMarkBlockedDisciplinesByTerm(String id) throws SQLException {
        ArrayList<Discipline> disciplines = new ArrayList<>();

        createConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT d.id,d.discipline,d.status FROM discipline d\n" +
                "left join term_discipline td on td.id_discipline = d.id\n" +
                "join mark m on m.id_term_discipline = td.id\n" +
                "where td.id_term = ? and d.status = '1';");

        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Discipline discipline = new Discipline();
            discipline.setId(rs.getInt("id"));
            discipline.setName(rs.getString("discipline"));
            discipline.setStatus(rs.getInt("status"));
            disciplines.add(discipline);
        }

        return disciplines;
    }


//    @Override
//    public List<Discipline> getDisciplinesWithoutMarksByTerm(String id) throws SQLException {
//        ArrayList<Discipline> disciplines = new ArrayList<>();
//
//        createConnection();
//
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM discipline d\n" +
//                "left join term_discipline td on td.id_discipline = d.id\n" +
//                "left join mark m on m.id_term_discipline = td.id\n" +
//                "where td.id_term = ? and m.mark is null;");
//
//        stmt.setString(1, id);
//        ResultSet rs = stmt.executeQuery();
//
//        while (rs.next()) {
//            Discipline discipline = new Discipline();
//            discipline.setId(rs.getInt("id"));
//            discipline.setName(rs.getString("discipline"));
//            discipline.setStatus(rs.getInt("status"));
//            disciplines.add(discipline);
//        }
//
//        return disciplines;
//    }

    @Override
    public String getTermDisciplinesWithoutMarksByTerm(String id) throws SQLException {
        StringBuilder termDisciplines = new StringBuilder();

        createConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT td.id FROM discipline d\n" +
                "left join term_discipline td on td.id_discipline = d.id\n" +
                "left join mark m on m.id_term_discipline = td.id\n" +
                "where td.id_term = ? and d.status = '1' and m.mark is null;");

        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            termDisciplines.append(rs.getInt("id")).append(" ");
        }


        return termDisciplines.toString().trim();
    }

    @Override
    public List<Student> getAllActiveStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        createConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from student where status='1'");
        ResultSet rs = stmt.executeQuery();

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

        PreparedStatement stmt = connection.prepareStatement("insert into  student (`surname`, `name`, `group`, `date`) values (?,?,?,?);");
        stmt.setString(1, surname);
        stmt.setString(2, name);
        stmt.setString(3, group);
        stmt.setString(4, dateToDB(date));
        stmt.execute();
    }

    @Override
    public void deleteStudentById(String id) throws SQLException {
        createConnection();

        PreparedStatement stmt = connection.prepareStatement("update student set `status` = '0' where id = ?");
        stmt.setString(1, id);
        stmt.execute();
    }

    @Override
    public Student getStudentById(String id) throws SQLException {
        Student student = new Student();

        createConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from student where status='1' and id=?");
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

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

        PreparedStatement stmt = connection.prepareStatement("update student set `surname`=?, `name`=?, `group`=?, `date`=? where `id`=?");
        stmt.setString(1, surname);
        stmt.setString(2, name);
        stmt.setString(3, group);
        stmt.setString(4, dateToDB(date));
        stmt.setString(5, id);
        stmt.execute();
    }

    @Override
    public List<Role> getAllRoles() throws SQLException {
        List<Role> roles = new LinkedList<>();

        createConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from role");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setRole(rs.getString("role"));
            roles.add(role);
        }

        return roles;
    }

    @Override
    public boolean authUser(String login, String password, String role) throws SQLException {
        createConnection();
        PreparedStatement stmt = connection.prepareStatement("select * from user_role ur left join user u on ur.id_user = u.id where login = ? and u.password =?and ur.id_role =? ");
        stmt.setString(1, login);
        stmt.setString(2, password);
        stmt.setString(3, role);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            return true;
        }

        return false;
    }

    @Override
    public List<Mark> getMarksByStudentAndTermId(String studentId, String termId) throws SQLException {
        List<Mark> marks = new LinkedList<>();

//        Map<Discipline, Mark> disciplineMarkMap = new HashMap<>();

        createConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT m.id, m.id_student, td.id_term, td.id_discipline, m.mark FROM discipline d left join term_discipline td on d.id=td.id_discipline left join term t on td.id_term=t.id left join mark m on m.id_term_discipline = td.id where d.status='1' and t.status='1' and m.id_student = ? and td.id_term = ?;");

        stmt.setString(1, studentId);
        stmt.setString(2, termId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Mark mark = new Mark();

            mark.setId(rs.getInt("id"));
            mark.setDiscipline(getDisciplineById(rs.getString("id_discipline")));
            mark.setStudent(getStudentById(rs.getString("id_student")));
            mark.setTerm(getTermById(rs.getString("id_term")));
            mark.setMark(rs.getInt("mark"));
            marks.add(mark);
        }

        return marks;
    }

//    @Override
//    public List<Mark> getAllMarksByTermId(String studentId) throws SQLException {
//        List<Mark> marks = new LinkedList<>();
//
//        createConnection();
////        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM mark m left join term_discipline td on td.id = m.id_term_discipline where m.id_student = ?;");
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM term t left join term_discipline td on td.id_term = t.id left join discipline d on d.id = td.id_discipline left join mark m on m.id_term_discipline = td.id where t.status = '1' and d.status = '1';");
//
//        stmt.setString(1, studentId);
//        ResultSet rs = stmt.executeQuery();
//
//        while (rs.next()) {
//            Mark mark = new Mark();
//
//            mark.setId(rs.getInt("id"));
//            mark.setStudent(getStudentById(rs.getString("id_student")));
//            mark.setTerm(getTermById(rs.getString("id_term")));
//            mark.setDiscipline(getDisciplineById(rs.getString("id_discipline")));
//            mark.setMark(rs.getInt("mark"));
//            marks.add(mark);
//        }
//
//        return marks.stream().sorted((m1,m2)->m1.getMark()- m2.getMark()).collect(Collectors.toList());
//    }
}
