import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbTable {

    Scanner scanner = new Scanner(System.in);


    public void deleteTable(Connection connection, String table_name){
        Statement statement;
        String query= String.format("drop table %s",table_name);
        try {
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public List<DbModel> createTable(Connection connection, String table_name){
        Statement statement;
        System.out.println("How many columns will there be in your table?(WITHOUT ID)");
        int tableValue = scanner.nextInt();
        List<DbModel> list=createQueryForTable(tableValue);
        String query=createQuery(list,table_name);
        System.out.println(query);
        try{
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

    public List<DbModel> createQueryForTable(int value){
        List<DbModel> list=new ArrayList<>();
        for (int i=0;i<value;i++) {
            list.add(printTable());
        }
        return list;
    }

    public String createQuery(List<DbModel> list,String table_name){
        String query="CREATE TABLE "+table_name+" ( id INT";
        for (int i=0;i<list.stream().count();i++) {
            query+=","+list.get(i).getName()+" "+list.get(i).getType();
        }
        query+=" );";
        return query;
    }
    public DbModel printTable(){
        System.out.println("*");
        System.out.println("Select 1 for String");
        System.out.println("*");
        System.out.println("Select 2 for Int");
        System.out.println("*");
        System.out.println("Your Select:");
        Integer select = scanner.nextInt();
        System.out.println("Enter Column name");
        scanner.nextLine();
        String name = scanner.nextLine();
        return createColumn(name,select);
    }

    public DbModel createColumn(String columnName,Integer columnIndex){
        if(columnIndex==1){
            //////////STRING
            return new DbModel(columnName,"varchar(200)");
        }
        else{
            ////////////////INT
            return new DbModel(columnName,"INT");
        }
    }
}
