package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection getConexion(){
        Connection conexion = null;
        String baseDatos = "test";
        String url = "jdbc:mysql://localhost:3306/" + baseDatos;
        String usuario = "root";
        String password = "hawai50";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usuario,password);
        }catch(Exception e){
            e.printStackTrace();
        }

        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if(conexion != null)
            System.out.println("Conexion existosa a bbdd!!");
        else
            System.out.println("!ERROR¡ -> Conexion a bbdd");
    }
}
