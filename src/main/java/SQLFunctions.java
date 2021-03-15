
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class SQLFunctions extends javax.swing.JFrame {


    //private final static String DATABASE_URL = "jdbc:sqlite:D:/DB.sqlite";
    private final static String DATABASE_URL = "jdbc:sqlite:C:/Program Files (x86)/KnightsApps/DB.sqlite";
    private EmailSender emailSender = new EmailSender();

    public Connection connect() {




        Connection conn = null;
        try {
            //Class.forName("org.sqlite.JDBC");
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DATABASE_URL);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public void selectAllGratters(String table, DefaultTableModel model){
        String sql = "SELECT * FROM "+table;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("text") + "\t");
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("text") });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int maxId(String table){
        int maxid=0;
        String sql = "SELECT id FROM "+table + " WHERE id = (SELECT MAX(id) FROM "+table+")";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            if (!rs.isBeforeFirst())
            {
                selectRandomGratter(table);

            }
            else {
                // loop through the result set
                while (rs.next()) {
                    maxid = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return maxid;
    }

    public String selectRandomGratter(String table){
        int random = 1 + (int) (Math.random() * maxId(table));
        String sql = "SELECT * FROM "+ table + " WHERE id="+ random;
        String text = new String();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            if (!rs.isBeforeFirst())
            {
                selectRandomGratter(table);

            }
            else {
                // loop through the result set
                while (rs.next()) {
                    text = rs.getString("text");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return text;
    }

    public void delete(String table, String id){
        String sql = "DELETE FROM "+table+" WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setInt(1, Integer.parseInt(id));
            // execute the delete statement
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addGratter(String table, String text){
        String sql = "INSERT INTO "+table+"(text) VALUES (?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1, text);
            // execute the delete statement
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectBirthday(String table, DefaultTableModel model, int day, int month){
        String sql = "SELECT first_name, last_name, father_name FROM "+ table +" WHERE day = "+ day +" AND month ="+month;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("last_name"), rs.getString("first_name"), rs.getString("father_name") });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAllPeople(String table, DefaultTableModel model){
        String sql = "SELECT * FROM "+ table;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("father_name"),
                        rs.getString("gender"),
                        rs.getInt("day"),
                        rs.getInt("month"),
                        rs.getInt("year"),
                        rs.getString("email")
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addPeople(String table,
                          String name,
                          String last,
                          String father,
                          String gender,
                          int day,
                          int month,
                          int year,
                          String email){
        String sql = "INSERT INTO "+table+"(first_name, last_name, father_name, gender, day, month, year, email) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1, name);
            pstmt.setString(2, last);
            pstmt.setString(3, father);
            pstmt.setString(4, gender);
            pstmt.setInt(5, day);
            pstmt.setInt(6, month);
            pstmt.setInt(7, year);
            pstmt.setString(8, email);

            // execute the delete statement
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void sendGreetings(int day, int month){
        String sql = "SELECT id, first_name, last_name, father_name, gender, email FROM people WHERE day = "+ day +" AND month ="+month;
        String subject = new String("ППФК вітає вас з Днем народження!");
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                String message = new String();
                //System.out.println(rs.getString("gender"));
                //System.out.println("M");
                System.out.println(rs.getString("gender").equals("M"));
                String gender = new String(rs.getString("gender"));
                message = gender.equals("M") ?"Шановний, ":"Шановна, ";
                message += rs.getString("last_name") + " " + rs.getString("first_name") + " " + rs.getString("father_name")+ "! ";
                message += this.selectRandomGratter(rs.getString("gender").equals("M")?"m_gratters":"w_gratters");
                emailSender.sendMessage(message, rs.getString("email"), subject);
                sendNotify(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("father_name"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendNotify(int id, String name, String last, String father){
        String listEmail = new String("");
        String subject = new String("У колеги День народження!");
        String sql = "SELECT email FROM people WHERE id != "+ id;
        int count =0;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                if(count != 0) {
                    listEmail += ", ";
                }
                listEmail+= rs.getString("email");
                ++count;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String message = new String(last + " " + name + " " + father + ", сьогодні святкує День народження! Привітайте колегу!:)");
        emailSender.sendMessage(message, listEmail, subject);
    }

}
