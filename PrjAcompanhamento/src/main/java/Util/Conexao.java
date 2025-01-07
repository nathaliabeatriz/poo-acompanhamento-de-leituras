package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    final private String driver="org.postgresql.Driver";
    final private String url="jdbc:postgresql://localhost:5432/"
            + "bd_acompanhamento";
    final private String usuario = "postgres";
    final private String senha = "123456";

    private static Conexao instanciaUnica;
    
    private Conexao(){
        
    }
    
    public static synchronized Conexao getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Conexao();
        }
        return instanciaUnica;
    }
       
    public Connection conectar(){
        Connection conn = null;
        
        try
        {  
            Class.forName(driver);
            conn = DriverManager.getConnection(url,usuario,senha);
        }

        catch(ClassNotFoundException ex)
        {  
            ex.printStackTrace();
        }

        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

        return conn;
    }
   
}
