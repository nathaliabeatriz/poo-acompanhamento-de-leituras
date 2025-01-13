package Controller;

import Model.Objetos.Autor;
import Model.DAO.AutorDAO;
import java.util.List;

public class AutorController {
    
    AutorDAO aDAO;
    
    public AutorController() 
    {
        aDAO = new AutorDAO();
    }    
    
    public Autor salvar(Autor a)
    {
        return aDAO.salvar(a);
    }
    
    public void editar(Autor a)
    {
        aDAO.editar(a);
    }
    
    public int excluir(Autor a)
    {
        return aDAO.excluir(a);
    }
    
    public List<Autor> getAutores()
    {
        return aDAO.getAutores();
    }
    
    public Autor getAutores(int id)
    {
        return aDAO.getAutores(id);
    }
    
    public List<Autor> getAutores(String nome)
    {
        return aDAO.getAutores(nome);
    }
}
