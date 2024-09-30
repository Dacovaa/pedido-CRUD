package Pck_Dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao_DAO {
	public Connection obj_connection = null;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_pedido";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public Connection getConnection() {
    	try {
    		Class.forName(DRIVER);
            obj_connection =  DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			System.out.println(e);
		}
    	return obj_connection;	
    }
}
