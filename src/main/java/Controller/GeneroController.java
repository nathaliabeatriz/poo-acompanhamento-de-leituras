package Controller;

import Model.Objetos.Genero;
import Model.DAO.GeneroDAO;
import java.util.List;

public class GeneroController {
    
    GeneroDAO gDAO;
    
    public GeneroController() 
    {
        gDAO = new GeneroDAO();
    }    
    
    public Genero salvar(Genero g)
    {
        return gDAO.salvar(g);
    }
    
    public void editar(Genero g)
    {
        gDAO.editar(g);
    }
    
    public int excluir(Genero g)
    {
        return gDAO.excluir(g);
    }
    
    public List<Genero> getGeneros()
    {
        return gDAO.getGeneros();
    }
    
    public Genero getGeneros(int id)
    {
        return gDAO.getGeneros(id);
    }
    
    public List<Genero> getGeneros(String nome)
    {
        return gDAO.getGeneros(nome);
    }
}
