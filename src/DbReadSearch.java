import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbReadSearch {

    Scanner scanner = new Scanner(System.in);



    public List<DbModel> getTableList(Connection connection,String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getColumns(null, null, tableName, null);

        List<DbModel> list=new ArrayList<>();
        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");
            String dataType = resultSet.getString("TYPE_NAME");
            if(dataType=="varchar"){
                list.add(new DbModel(columnName,"varchar(200)"));
            }
            else{
                list.add(new DbModel(columnName,"INT"));
            }
        }

        return list;
    }

    public void getTable(Connection connection, String table_name, List<DbModel> list){
        Statement statement;
        ResultSet rs=null;
        try {
            String query=String.format("select * from %s",table_name);
            statement=connection.createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()){
                for (int i=0;i<list.stream().count();i++) {
                    System.out.print(list.get(i).getName()+":"+rs.getString(list.get(i).getName()) + " ");
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    public void searchData(Connection connection,String table_name,List<DbModel> list){
        Statement statement;
        String query=getStringQuery(list,table_name);
        try {
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public String getStringQuery(List<DbModel> list,String table_name){
        DbModel model=printSearchData(list);
        System.out.println(model.getName()+": Please Enter Search Param.");
        String searchparam=scanner.nextLine();
        String query=String.format("select * from %s where "+model.getName()+"= '%s'",table_name,searchparam);
        return query;
    }

    public DbModel printSearchData(List<DbModel> list){
        System.out.println("Select data you want to search");
        for (int i=0;i<list.stream().count();i++) {
            System.out.print(i+" - "+list.get(i).getName() +"\n");
        }
        int select= scanner.nextInt();
        scanner.nextLine();
        return list.get(select);
    }
}
