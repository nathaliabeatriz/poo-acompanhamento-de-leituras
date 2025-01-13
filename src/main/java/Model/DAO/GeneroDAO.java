package Model.DAO;

import Model.Objetos.Genero;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {
    Connection conn;

    public GeneroDAO(){
        Conexao conexao = Conexao.getInstancia();
        conn = conexao.getConnection();
    }
    
    public Genero salvar(Genero obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO genero(descricao) values(?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getDescricao());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                obj.setIdGenero(rs.getInt("idgenero"));
            } else{
                obj.setIdGenero(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    
    public void editar(Genero obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE genero SET descricao = ? WHERE idgenero = ?");
            stmt.setString(1, obj.getDescricao());
            stmt.setInt(2, obj.getIdGenero());
            stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Genero obj){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM genero WHERE idgenero = ?");
            stmt.setInt(1, obj.getIdGenero());
            verif = stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Genero>getGeneros(){
        List<Genero>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM genero");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getGenero(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
     public List<Genero>getGeneros(String nome){
        List<Genero>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM genero WHERE descricao ILIKE ?");
            ppStmt.setString(1, nome+"%");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getGenero(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public Genero getGeneros(int id){
        Genero obj = new Genero();
        ResultSet nbls_Rs;
        
        try{
            PreparedStatement stmt = conn.prepareStatement("select * from genero where idgenero = ?");
            stmt.setInt(1, id);
            nbls_Rs = stmt.executeQuery();
            if(nbls_Rs.next()){
                obj = getGenero(nbls_Rs);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return obj;
    }
    
    public Genero getGenero(ResultSet rs) throws SQLException{
        Genero obj = new Genero();
        obj.setIdGenero(rs.getInt("idgenero"));
        obj.setDescricao(rs.getString("descricao"));
        return obj;
    }
}