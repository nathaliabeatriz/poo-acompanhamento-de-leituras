package Model.DAO;

import Model.Objetos.Autor;
import Model.Objetos.Editora;
import Model.Objetos.Genero;
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

public class LivroDAO {
    Connection conn;

    public LivroDAO(){
        Conexao conexao = Conexao.getInstancia();
        conn = conexao.conectar();
    }
    
    public Livro salvar(Livro obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO livro(nome, isbn, numeroPags, resumo, ano, idioma, autor, genero, editora) values(?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getIsbn());
            stmt.setInt(3, obj.getNumeroPags());
            stmt.setString(4, obj.getResumo());
            stmt.setInt(5, obj.getAno());
            stmt.setString(6, obj.getIdioma());
            stmt.setInt(7, obj.getAutor().getId());
            stmt.setInt(8, obj.getGenero().getIdGenero());
            stmt.setInt(9, obj.getEditora().getIdEditora());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                obj.setIdLivro(rs.getInt("idlivro"));
            } else{
                obj.setIdLivro(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    
    public void editar(Livro obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE livro SET nome = ?, isbn = ?, numeroPags = ?, resumo = ?, ano = ?, idioma = ?, autor = ?, genero = ?, editora = ? WHERE idlivro = ?");
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getIsbn());
            stmt.setInt(3, obj.getNumeroPags());
            stmt.setString(4, obj.getResumo());
            stmt.setInt(5, obj.getAno());
            stmt.setString(6, obj.getIdioma());
            stmt.setInt(7, obj.getAutor().getId());
            stmt.setInt(8, obj.getGenero().getIdGenero());
            stmt.setInt(9, obj.getEditora().getIdEditora());
            stmt.setInt(10, obj.getIdLivro());
            stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int excluir(Livro obj){
        int verif = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM livro WHERE idlivro = ?");
            stmt.setInt(1, obj.getIdLivro());
            verif = stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Livro>getLivros(){
        List<Livro>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM livro");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lst.add(getLivro(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lst;
    }
    
    public Livro getLivros(int id){
        Livro obj = new Livro();
        ResultSet rs;
        
        try{
            PreparedStatement stmt = conn.prepareStatement("select * from livro where idlivro = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                obj = getLivro(rs);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return obj;
    }
    
    public Livro getLivro(ResultSet rs) throws SQLException{
        Livro obj = new Livro();
        
        obj.setIdLivro(rs.getInt("idlivro"));
        obj.setNome(rs.getString("nome"));
        obj.setIsbn(rs.getString("isbn"));
        obj.setNumeroPags(rs.getInt("numeroPags"));
        obj.setResumo(rs.getString("resumo"));
        obj.setAno(rs.getInt("ano"));
        obj.setIdioma(rs.getString("idioma"));
        
        int idAutor = rs.getInt("autor");
        int idGenero = rs.getInt("genero");
        int idEditora = rs.getInt("editora");
        
        AutorDAO aDao = new AutorDAO();
        Autor a = aDao.getAutores(idAutor);
        obj.setAutor(a);
        GeneroDAO gDao = new GeneroDAO();
        Genero g = gDao.getGeneros(idGenero);
        obj.setGenero(g);
        EditoraDAO eDao = new EditoraDAO();
        Editora e = eDao.getEditoras(idEditora);
        obj.setEditora(e);
        return obj;
    }
}