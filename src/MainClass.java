import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.HashSet;

/**
 * Created by GVG on 18.08.2017.
 */
public class MainClass {

    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) throws SQLException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        HashSet<String> commands = new HashSet<>();
        commands.add("/cost");
        commands.add("/productcost");
        commands.add("/changecost");

        String[] inComm;
        boolean doNext;

        initBase();

        System.out.println("Type command:");
        do {
            doNext = false;
            try {
                input = br.readLine();
                if (input.equals("/exit")) break;
                inComm = input.split(" ");
                if (commands.contains(inComm[0]) == false) break;
                doNext = true;
                getInfo(inComm[0], inComm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (doNext);

        disconnect();
    }

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:MainDb.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() throws SQLException {
        connection.close();
    }

    public static void getInfo(String com, String[] comArr) {
        ResultSet res;
        if (com.equals("/cost")) {
            if (comArr.length != 2) return;
            try {
                res = stmt.executeQuery("SELECT id, title FROM ProdTable WHERE title = '" + comArr[1].trim() + "'");
                while (res.next()) {
                    System.out.println(res.getString("title") + " " + res.getInt("id"));
                }
            } catch (SQLException e) {
                System.out.println("Product: " + comArr[1].trim() + " not found!");
            }
        } else if (com.equals("/changecost")) {
            if (comArr.length != 3) return;
            try {
                stmt.executeUpdate("UPDATE ProdTable SET cost = " + comArr[2] + " WHERE title = '" + comArr[1].trim() + "'");
                res = stmt.executeQuery("SELECT id, title, cost FROM ProdTable WHERE title = '" + comArr[1].trim() + "'");
                while (res.next()) {
                    System.out.println(res.getString("title") + " " + res.getInt("cost"));
                }
            } catch (SQLException e) {
                System.out.println("Product: " + comArr[1].trim() + " not found!");
            }
        } else if (com.equals("/productcost")) {
            if (comArr.length != 3) return;
            try {
                res = stmt.executeQuery("SELECT id, title, cost FROM ProdTable WHERE (cost > " + comArr[1] + " AND cost < " + comArr[2] + ")");
                while (res.next()) {
                    System.out.println(res.getInt("id") + " " + res.getString("title") + " " + res.getInt("cost"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void initBase() throws SQLException {
        connect();
        stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS ProdTable (\n" +
                "   id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "   prodid INTEGER NOT NULL,\n" +
                "   title TEXT,\n" +
                "   cost INTEGER);");

        stmt.execute("DELETE FROM ProdTable");
        int id = 1;
        int prodid = 0;
        String title = "";
        int cost = 0;

        for (int i = 0; i < 10000; i++) {
            id += 1;
            prodid += 1;
            cost = 100 * i;
            title = "product" + i;
            stmt.execute("INSERT INTO ProdTable (id, prodid, title, cost) VALUES (" + id + ", " + prodid + ", '" + title + "', " + cost + ")");
        }

//        ResultSet res = stmt.executeQuery("SELECT * FROM ProdTable");
//        while (res.next()) {
//            System.out.println(res.getInt("id") + " " + res.getString("title") + " " + res.getInt("cost"));
//        }
    }
}