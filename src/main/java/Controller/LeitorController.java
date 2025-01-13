package Controller;

import Model.DAO.LeitorDAO;
import Model.Objetos.Leitor;
import java.util.List;

public class LeitorController {
    LeitorDAO lDAO;
    
    public LeitorController() 
    {
        lDAO = new LeitorDAO();
    }    
    
    public Leitor salvar(Leitor l)
    {
        return lDAO.salvar(l);
    }
    
    public void editar(Leitor l)
    {
        lDAO.editar(l);
    }
    
    public int excluir(Leitor l)
    {
        return lDAO.excluir(l);
    }
    
    public List<Leitor> getLeitores()
    {
        return lDAO.getLeitores();
    }
    
    public Leitor getLeitores(int id)
    {
        return lDAO.getLeitores(id);
    }
    
    public List<Leitor> getLeitores(String nome)
    {
        return lDAO.getLeitores(nome);
    }    
}
