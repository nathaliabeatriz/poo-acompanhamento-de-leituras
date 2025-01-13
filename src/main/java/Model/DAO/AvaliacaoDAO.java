package Model.DAO;

import Model.Objetos.Avaliacao;
import Model.Objetos.Estante;
import Model.Objetos.Livro;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {
    Connection conn;

    public AvaliacaoDAO(){
        Conexao conexao = Conexao.getInstancia();
        conn = conexao.getConnection();
    }
    
    public Avaliacao salvar(Avaliacao obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO avaliacao(estrelas, titulo, resenha, estante) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getEstrelas());
            stmt.setString(2, obj.getTitulo());
            stmt.setString(3, obj.getResenha());
            stmt.setInt(4, obj.getEstante().getIdEstante());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                obj.setIdAvaliacao(rs.getInt("idavaliacao"));
            } else{
                obj.setIdAvaliacao(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    
    public void editar(Avaliacao obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE avaliacao SET estrelas = ?, titulo = ?, resenha = ?, estante = ? WHERE idavaliacao = ?");
            stmt.setInt(1, obj.getEstrelas());
            stmt.setString(2, obj.getTitulo());
            stmt.setString(3, obj.getResenha());
            stmt.setInt(4, obj.getEstante().getIdEstante());
            stmt.setInt(5, obj.getIdAvaliacao());
            stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Avaliacao obj){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM avaliacao WHERE idavaliacao = ?");
            stmt.setInt(1, obj.getIdAvaliacao());
            verif = stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Avaliacao>getAvaliacoes(){
        List<Avaliacao>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM avaliacao");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getAvaliacao(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public List<Avaliacao>getAvaliacoes(Estante estante){
        List<Avaliacao>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM avaliacao WHERE estante = ?");
            ppStmt.setInt(1, estante.getIdEstante());
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getAvaliacao(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public int buscarLivros(Avaliacao obj){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM avaliacao WHERE idavaliacao = ?");
            stmt.setInt(1, obj.getIdAvaliacao());
            verif = stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    
    public Avaliacao getAvaliacao(ResultSet rs) throws SQLException{
        Avaliacao obj = new Avaliacao();
        
        obj.setIdAvaliacao(rs.getInt("idavaliacao"));
        obj.setEstrelas(rs.getInt("estrelas"));
        obj.setTitulo(rs.getString("titulo"));
        obj.setResenha(rs.getString("resenha"));
        
        int idEstante = rs.getInt("estante");
        
        EstanteDAO eDao = new EstanteDAO();
        Estante e = eDao.getEstantes(idEstante);
        obj.setEstante(e);
        
        return obj;
    }
}