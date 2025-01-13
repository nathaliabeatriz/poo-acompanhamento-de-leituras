package Model.DAO;

import Model.Objetos.Autor;
import Util.Conexao;
import Util.ManipulaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    Connection conn;
    ManipulaData md;

    public AutorDAO(){
        Conexao conexao = Conexao.getInstancia();
        conn = conexao.getConnection();
        md = new ManipulaData();
    }
    
    public Autor salvar(Autor obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO autor(nome, dataNascimento, nacionalidade, sexo) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.setDate(2, md.string2Date(obj.getDataNascimento()));
            stmt.setString(3, obj.getNacionalidade());
            stmt.setString(4, obj.getSexo());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                obj.setId(rs.getInt("idautor"));
            } else{
                obj.setId(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    
    public void editar(Autor obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE autor SET nome = ?, dataNascimento = ?, nacionalidade = ?, sexo = ? WHERE idautor = ?");
            stmt.setString(1, obj.getNome());
            stmt.setDate(2, md.string2Date(obj.getDataNascimento()));
            stmt.setString(3, obj.getNacionalidade());
            stmt.setString(4, obj.getSexo());
            stmt.setInt(5, obj.getId());
            stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Autor obj){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM autor WHERE idautor = ?");
            stmt.setInt(1, obj.getId());
            verif = stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Autor>getAutores(){
        List<Autor>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM autor");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getAutor(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
     public List<Autor>getAutores(String nome){
        List<Autor>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM autor WHERE nome ILIKE ?");
            ppStmt.setString(1, nome+"%");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getAutor(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public Autor getAutores(int id){
        Autor obj = new Autor();
        ResultSet rs;
        
        try{
            PreparedStatement stmt = conn.prepareStatement("select * from autor where idautor = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                obj = getAutor(rs);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return obj;
    }
    
    public Autor getAutor(ResultSet rs) throws SQLException{
        Autor obj = new Autor();
        obj.setId(rs.getInt("idautor"));
        obj.setNome(rs.getString("nome"));
        obj.setDataNascimento(md.date2String(rs.getString("dataNascimento")));
        obj.setNacionalidade(rs.getString("nacionalidade"));
        obj.setSexo(rs.getString("sexo"));
        return obj;
    }
}