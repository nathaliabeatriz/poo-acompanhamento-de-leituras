package Model.DAO;

import Model.Objetos.Autor;
import Model.Objetos.Editora;
import Model.Objetos.Genero;
import Model.Objetos.Leitor;
import Model.Objetos.Livro;
import Util.Conexao;
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
        conn = conexao.getConnection();
    }
    
    public Livro salvar(Livro obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO livro(nome, isbn, numeroPags, resumo, ano, idioma, autor, genero, editora, mediaavaliacao) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getIsbn());
            stmt.setInt(3, obj.getNumeroPags());
            stmt.setString(4, obj.getResumo());
            stmt.setInt(5, obj.getAno());
            stmt.setString(6, obj.getIdioma());
            stmt.setInt(7, obj.getAutor().getId());
            stmt.setInt(8, obj.getGenero().getIdGenero());
            stmt.setInt(9, obj.getEditora().getIdEditora());
            stmt.setDouble(10, obj.getMediaAvaliacao());
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
    
    public void atualizarMedia(Livro obj){
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE livro SET mediaavaliacao = ? WHERE idlivro = ?");
            stmt.setDouble(1, obj.getMediaAvaliacao());
            stmt.setInt(2, obj.getIdLivro());
            
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
    
    public List<Livro>getLivros(String nome){
        List<Livro>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM livro WHERE nome ILIKE ?");
            ppStmt.setString(1, nome+"%");
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
    
    public List<Livro>getLivros(String nome, Autor autor, Editora editora, Genero genero){
        List<Livro>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM livro WHERE nome ILIKE ? AND autor = ? AND editora = ? AND genero = ?");
            ppStmt.setString(1, nome+"%");
            ppStmt.setInt(2, autor.getId());
            ppStmt.setInt(3, editora.getIdEditora());
            ppStmt.setInt(4, genero.getIdGenero());
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
    
    public List<Livro>getLivros(Autor autor){
        List<Livro>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM livro WHERE autor = ?");
            ppStmt.setInt(1, autor.getId());
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
    
    public List<Livro>getLivros(Editora editora){
        List<Livro>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM livro WHERE editora = ?");
            ppStmt.setInt(1, editora.getIdEditora());
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
    
    public List<Livro>getLivros(Genero genero){
        List<Livro>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM livro WHERE genero = ?");
            ppStmt.setInt(1, genero.getIdGenero());
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
        obj.setMediaAvaliacao(rs.getDouble("mediaavaliacao"));
        
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
    
    public List<Livro>getLivrosEstante(String nome, Leitor leitor){
        List<Livro>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM livro l join estante e on l.idlivro = e.livro WHERE l.nome ILIKE ? AND e.leitor = ?");
            ppStmt.setString(1, nome+"%");
            ppStmt.setInt(2, leitor.getId());
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
    
    public List<Livro>getLivrosForaEstante(String nome, Leitor leitor){
        List<Livro>lst = new ArrayList<>();
        ResultSet rs;
        
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM livro WHERE idlivro not in (select idlivro from livro join estante on livro.idlivro = estante.livro where estante.leitor = ?) AND nome ILIKE ?");
            ppStmt.setInt(1, leitor.getId());
            ppStmt.setString(2, nome+"%");
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
    
    public int quantidadeAvaliacoesPorLivro(Livro l){
        ResultSet rs;
        int quant = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT count(idlivro) FROM livro JOIN estante ON livro.idlivro = estante.livro JOIN avaliacao ON estante.idestante = avaliacao.estante WHERE idlivro = ? GROUP BY(idlivro)");
            stmt.setInt(1, l.getIdLivro());
            rs = stmt.executeQuery();
            if(rs.next()){
                quant = rs.getInt("count");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return quant;
    }
}