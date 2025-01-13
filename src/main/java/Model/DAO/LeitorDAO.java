package Model.DAO;

import Model.Objetos.Leitor;
import Util.Conexao;
import Util.ManipulaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LeitorDAO {
    Connection conn;
    ManipulaData md;

    public LeitorDAO(){
        Conexao conexao = Conexao.getInstancia();
        conn = conexao.getConnection();
        md = new ManipulaData();
    }
    
    public Leitor salvar(Leitor obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO leitor(nome, dataNascimento, nacionalidade, sexo, email) values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.setDate(2, md.string2Date(obj.getDataNascimento()));
            stmt.setString(3, obj.getNacionalidade());
            stmt.setString(4, obj.getSexo());
            stmt.setString(5, obj.getEmail());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                obj.setId(rs.getInt("idleitor"));
            } else{
                obj.setId(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    
    public void editar(Leitor obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE leitor SET nome = ?, dataNascimento = ?, nacionalidade = ?, sexo = ?, email = ? WHERE idleitor = ?");
            stmt.setString(1, obj.getNome());
            stmt.setDate(2, md.string2Date(obj.getDataNascimento()));
            stmt.setString(3, obj.getNacionalidade());
            stmt.setString(4, obj.getSexo());
            stmt.setString(5, obj.getEmail());
            stmt.setInt(6, obj.getId());
            stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Leitor obj){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM leitor WHERE idleitor = ?");
            stmt.setInt(1, obj.getId());
            verif = stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Leitor>getLeitores(){
        List<Leitor>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM leitor");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getLeitor(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public Leitor getLeitores(int id){
        Leitor obj = new Leitor();
        ResultSet rs;
        
        try{
            PreparedStatement stmt = conn.prepareStatement("select * from leitor where idleitor = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                obj = getLeitor(rs);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return obj;
    }
    
        public List<Leitor> getLeitores(String nome){
            List<Leitor>lst = new ArrayList<>();
            ResultSet rs;
        
        try{
            PreparedStatement stmt = conn.prepareStatement("select * from leitor where nome ILIKE ?");
            stmt.setString(1, nome+"%");
            rs = stmt.executeQuery();
           while(rs.next()){
                lst.add(getLeitor(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public Leitor getLeitor(ResultSet rs) throws SQLException{
        Leitor obj = new Leitor();
        obj.setId(rs.getInt("idleitor"));
        obj.setNome(rs.getString("nome"));
        obj.setDataNascimento(md.date2String(rs.getString("dataNascimento")));
        obj.setNacionalidade(rs.getString("nacionalidade"));
        obj.setSexo(rs.getString("sexo"));
        obj.setEmail(rs.getString("email"));
        return obj;
    }
}