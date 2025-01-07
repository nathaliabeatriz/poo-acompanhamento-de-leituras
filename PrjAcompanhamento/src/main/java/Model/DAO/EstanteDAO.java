package Model.DAO;

import Model.Objetos.Estante;
import Model.Objetos.Leitor;
import Model.Objetos.Livro;
import Util.Conexao;
import Util.ManipulaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstanteDAO {
    Connection conn;

    public EstanteDAO(){
        Conexao conexao = Conexao.getInstancia();
        conn = conexao.conectar();
    }
    
    public Estante salvar(Estante obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO estante(leitor, livro, status) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getLeitor().getId());
            stmt.setInt(2, obj.getLivro().getIdLivro());
            stmt.setString(3, obj.getStatus());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                obj.setIdEstante(rs.getInt("idestante"));
            } else{
                obj.setIdEstante(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    
    public void editar(Estante obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE estante SET leitor = ?, livro = ?, status = ? WHERE idestante = ?");
            stmt.setInt(1, obj.getLeitor().getId());
            stmt.setInt(2, obj.getLivro().getIdLivro());
            stmt.setString(3, obj.getStatus());
            stmt.setInt(4, obj.getIdEstante());
            stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Estante obj){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM estante WHERE idestante = ?");
            stmt.setInt(1, obj.getIdEstante());
            verif = stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Estante>getEstantes(){
        List<Estante>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM estante");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getEstante(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public Estante getEstantes(int id){
        Estante obj = new Estante();
        ResultSet rs;
        
        try{
            PreparedStatement stmt = conn.prepareStatement("select * from estante where idestante = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                obj = getEstante(rs);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return obj;
    }
    
    public Estante getEstante(ResultSet rs) throws SQLException{
        Estante obj = new Estante();
        
        obj.setIdEstante(rs.getInt("idestante"));
        obj.setStatus(rs.getString("status"));
        
        int idLeitor = rs.getInt("leitor");
        int idLivro = rs.getInt("livro");
        
        LeitorDAO leitorDao = new LeitorDAO();
        Leitor leitor = leitorDao.getLeitores(idLeitor);
        obj.setLeitor(leitor);
        LivroDAO livroDao = new LivroDAO();
        Livro livro = livroDao.getLivros(idLivro);
        obj.setLivro(livro);
        
        return obj;
    }
}