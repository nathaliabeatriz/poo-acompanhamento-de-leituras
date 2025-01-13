package Controller;

import Model.Objetos.Livro;
import Model.DAO.LivroDAO;
import Model.Objetos.Autor;
import Model.Objetos.Editora;
import Model.Objetos.Genero;
import Model.Objetos.Leitor;
import java.util.List;

public class LivroController {
    
    LivroDAO lDAO;
    
    public LivroController() 
    {
        lDAO = new LivroDAO();
    }    
    
    public Livro salvar(Livro l)
    {
        return lDAO.salvar(l);
    }
    
    public void editar(Livro l)
    {
        lDAO.editar(l);
    }
    
    public void atualizarMedia(Livro l){
        lDAO.atualizarMedia(l);
    }
    
    public int excluir(Livro l)
    {
        return lDAO.excluir(l);
    }
    
    public List<Livro> getLivros()
    {
        return lDAO.getLivros();
    }
    
    public Livro getLivros(int id)
    {
        return lDAO.getLivros(id);
    }
    
    public List<Livro> getLivros(String nome)
    {
        return lDAO.getLivros(nome);
    }
    
    public List<Livro> getLivros(String nome, Autor autor, Editora editora, Genero genero)
    {
        return lDAO.getLivros(nome, autor, editora, genero);
    }
    
    public List<Livro> getLivros(Autor autor)
    {
        return lDAO.getLivros(autor);
    }
    
    public List<Livro> getLivros(Editora editora)
    {
        return lDAO.getLivros(editora);
    }
    
    public List<Livro> getLivros(Genero genero)
    {
        return lDAO.getLivros(genero);
    }
    
    public List<Livro> getLivrosEstante(String nome, Leitor leitor)
    {
        return lDAO.getLivrosEstante(nome, leitor);
    }
    
    public List<Livro> getLivrosForaEstante(String nome, Leitor leitor)
    {
        return lDAO.getLivrosForaEstante(nome, leitor);
    }
    
    public int quantidadeAvaliacoesPorLivro(Livro l){
        return lDAO.quantidadeAvaliacoesPorLivro(l);
    }
}
