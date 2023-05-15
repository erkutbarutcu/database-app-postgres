import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public static Connection connectDatabase(String dbname, String user, String pass){
        Connection connection=null;
        String connectionURL="jdbc:postgresql://localhost:5432/";
        try{
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection(connectionURL,user,pass);
            if(connection!=null){
                System.out.println("Connection Established");
            }
            else{
                System.out.println("Connection Failed");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }
}
