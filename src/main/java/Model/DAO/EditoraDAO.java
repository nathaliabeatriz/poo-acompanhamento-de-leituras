package Model.DAO;

import Model.Objetos.Editora;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {
    Connection conn;

    public EditoraDAO(){
        Conexao conexao = Conexao.getInstancia();
        conn = conexao.getConnection();
    }
    
    public Editora salvar(Editora obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO editora(nome, sede) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getSede());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                obj.setIdEditora(rs.getInt("ideditora"));
            } else{
                obj.setIdEditora(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    
    public void editar(Editora obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE editora SET nome = ?, sede = ? WHERE ideditora = ?");
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getSede());
            stmt.setInt(3, obj.getIdEditora());
            stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Editora obj){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM editora WHERE ideditora = ?");
            stmt.setInt(1, obj.getIdEditora());
            verif = stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Editora>getEditoras(){
        List<Editora>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM editora");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getEditora(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public List<Editora>getEditoras(String nome){
        List<Editora>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM editora WHERE nome ILIKE ?");
            ppStmt.setString(1, nome+"%");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getEditora(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public Editora getEditoras(int id){
        Editora obj = new Editora();
        ResultSet rs;
        
        try{
            PreparedStatement stmt = conn.prepareStatement("select * from editora where ideditora = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                obj = getEditora(rs);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return obj;
    }
    
    public Editora getEditora(ResultSet rs) throws SQLException{
        Editora obj = new Editora();
        obj.setIdEditora(rs.getInt("ideditora"));
        obj.setNome(rs.getString("nome"));
        obj.setSede(rs.getString("sede"));
        return obj;
    }
}