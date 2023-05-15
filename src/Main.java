import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection=null;

        DbUI UI=new DbUI();
        while (connection==null){
           connection=UI.loginMenu();
        }

        System.out.println("Welcome");

        while(connection!=null){
            connection=UI.mainMenu(connection);
        }

        System.out.println("Have a good day!");
    }
}