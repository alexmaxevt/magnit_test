import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnect {

    private String url;
    private String login;
    private String password;
    private String sql;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public void queryDeleteAndInsert(int number) {
        int [] value = new int[number];
        try {
            this.setUrl("jdbc:mysql://localhost:3306/testdb");
            this.setLogin("root");
            this.setPassword("DecFebMar1217");
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                Connection conn = DriverManager.getConnection(this.getUrl(), this.getLogin(), this.getPassword());
                System.out.println("Connection to TestDB succesfull!");
                Statement statement = conn.createStatement();
                this.setSql("DELETE FROM test");
                statement.executeUpdate(this.getSql());
                for (int i = 1; i <= value.length; i++) {
                    value[i-1] = i;
                    this.setSql("INSERT INTO test (FIELD) VALUES (" + value[i-1] + ")");
                    statement.executeUpdate(this.getSql());
                }
                System.out.println("Добавление элементов в базу данных закончено");
            }
            catch (Exception ex) {
                System.out.println("Connection failed");
                ex.printStackTrace();
            }
        }
        catch (Exception ex) {
            System.out.println("Connection failed");
            ex.printStackTrace();
        }
    }

    public int [] querySelect() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            this.setUrl("jdbc:mysql://localhost:3306/testdb");
            this.setLogin("root");
            this.setPassword("DecFebMar1217");
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                Connection conn = DriverManager.getConnection(this.getUrl(), this.getLogin(), this.getPassword());
                System.out.println("Connection to TestDB succesfull!");
                Statement statement = conn.createStatement();
                this.setSql("SELECT * FROM test");
                ResultSet resultSet = statement.executeQuery(this.getSql());
                while (resultSet.next()){
                    arrayList.add(resultSet.getInt("FIELD"));
                }
                System.out.println("Выборка элементов из базы данных завершена");
            }
            catch (Exception ex) {
                System.out.println("Connection failed");
                ex.printStackTrace();
            }
        }
        catch (Exception ex) {
            System.out.println("Connection failed");
            ex.printStackTrace();
        }
        int [] result = new int[arrayList.size()];
        for (int i = 1; i <= result.length; i++) {
            result[i-1] = arrayList.get(i-1);
        }
        System.out.println("Массив из элементов базы данных создан");
        return result;
    }
}
