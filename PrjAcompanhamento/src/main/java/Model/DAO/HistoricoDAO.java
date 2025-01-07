package Model.DAO;

import Model.Objetos.Historico;
import Model.Objetos.Estante;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDAO {
    Connection conn;

    public HistoricoDAO(){
        Conexao conexao = Conexao.getInstancia();
        conn = conexao.conectar();
    }
    
    public Historico salvar(Historico obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO historico(estante, porcentagemLeitura, comentario) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getEstante().getIdEstante());
            stmt.setDouble(2, obj.getPorcentagemLeitura());
            stmt.setString(3, obj.getComentario());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                obj.setIdHistorico(rs.getInt("idhistorico"));
            } else{
                obj.setIdHistorico(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    
    public void editar(Historico obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE historico SET estante = ?, porcentagemLeitura = ?, comentario = ? WHERE idhistorico = ?");
            stmt.setInt(1, obj.getEstante().getIdEstante());
            stmt.setDouble(2, obj.getPorcentagemLeitura());
            stmt.setString(3, obj.getComentario());
            stmt.setInt(4, obj.getIdHistorico());
            stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Historico obj){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM historico WHERE idhistorico = ?");
            stmt.setInt(1, obj.getIdHistorico());
            verif = stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Historico>getAvaliacoes(){
        List<Historico>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM historico");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getHistorico(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public Historico getHistorico(ResultSet rs) throws SQLException{
        Historico obj = new Historico();
        
        obj.setIdHistorico(rs.getInt("idhistorico"));
        obj.setPorcentagemLeitura(rs.getDouble("porcentagemLeitura"));
        obj.setComentario(rs.getString("comentario"));
        
        int idEstante = rs.getInt("estante");
        
        EstanteDAO eDao = new EstanteDAO();
        Estante e = eDao.getEstantes(idEstante);
        obj.setEstante(e);
        
        return obj;
    }
}