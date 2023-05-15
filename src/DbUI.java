import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbUI {
    Scanner scanner = new Scanner(System.in);

    DbTable tableRepository=new DbTable();
    DbReadSearch tableRead=new DbReadSearch();
    DbInsertDelete tableCRUD=new DbInsertDelete();

    public Connection loginMenu(){
        String dbname="homework";
        System.out.println("*");
        System.out.println("Enter name:");
        String name=scanner.nextLine();
        System.out.println("Enter pass:");
        String pass=scanner.nextLine();
        System.out.println("Connecting to database...");
        Connection connection=DbConnection.connectDatabase(dbname,name,pass);
        return connection;
    }
    public Connection mainMenu(Connection connection) throws SQLException {

        System.out.println("Select operation.");
        System.out.println("1 - Create Table");
        System.out.println("2 - Delete Table");
        System.out.println("3 - Show Table");
        System.out.println("4 - Insert Data");
        System.out.println("5 - Delete Data");
        System.out.println("6 - Search Data");
        System.out.println("-1 - Connection Out!");
        int select=scanner.nextInt();
        scanner.nextLine();

        if (select==1){
            System.out.println("Enter Table name:");
            String table_name=scanner.nextLine();
            tableRepository.createTable(connection,table_name);
            return connection;
        }
        else if(select==2){
            System.out.println("Enter Table name:");
            String table_name=scanner.nextLine();
            tableRepository.deleteTable(connection,table_name);
            return connection;
        }
        else if(select==3){
            System.out.println("Enter Table name:");
            String table_name=scanner.nextLine();
            tableRead.getTable(connection,table_name,tableRead.getTableList(connection,table_name));
            return connection;
        }
        else if(select==4){
            System.out.println("Enter Table name:");
            String table_name=scanner.nextLine();
            tableCRUD.insertData(connection,table_name,tableRead.getTableList(connection,table_name));
            return connection;
        }
        else if(select==5){
            System.out.println("Enter Table name:");
            String table_name=scanner.nextLine();
            System.out.println("Enter data id will delete :");
            int id=scanner.nextInt();
            tableCRUD.deleteDataById(connection,table_name,id);
            return connection;
        }
        else if(select==6){
            System.out.println("Enter Table name:");
            String table_name=scanner.nextLine();
            tableRead.searchData(connection,table_name,tableRead.getTableList(connection,table_name));
            return connection;
        }
        else if(select==-1){
            return null;
        }
        else {
            return connection;
        }
    }

}


