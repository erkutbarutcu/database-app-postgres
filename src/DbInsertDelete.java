import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class DbInsertDelete {

    Scanner scanner = new Scanner(System.in);


    public void insertData(Connection connection,String table_name,List<DbModel> list){
        Statement statement;
        String query=setRow(table_name,list);
        try {
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void deleteDataById(Connection connection,String table_name,Integer id){
        Statement statement;
        try{
            String query=String.format("delete from %s where id='%s'",table_name,id.toString());
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public String setRow(String table_name,List<DbModel> list){
        String query="INSERT INTO "+table_name+"(";
        for (int i=0;i<list.stream().count();i++) {
            if(i==0){
                query +=list.get(i).getName();
            }
            else {
                query += "," + list.get(i).getName();
            }
        }
        query+=") VALUES (";
        for (int i=0;i<list.stream().count();i++) {
            if(i==0){
                query+=setColumn(list.get(i));
            }
            else{
                query+=","+setColumn(list.get(i));

            }
        }
        query+=");";

        return query;
    }


    public String setColumn(DbModel model){
        System.out.println("*");
        System.out.println(model.getType());
        if (model.getType()=="varchar(200)"||model.getType()=="varchar"){
            System.out.println("Enter value of"+model.getName());
            String name = scanner.nextLine();
            return name;
        }else{
            System.out.println("Enter value of"+model.getName());
            int value = scanner.nextInt();
            scanner.nextLine();
            return String.valueOf(value);
        }
    }


}
