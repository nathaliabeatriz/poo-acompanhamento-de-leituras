package Controller;

import Model.DAO.EstanteDAO;
import Model.Objetos.Estante;
import Model.Objetos.Leitor;
import Model.Objetos.Livro;
import java.util.List;

public class EstanteController {
    EstanteDAO eDAO;
    
    public EstanteController() 
    {
        eDAO = new EstanteDAO();
    }    
    
    public Estante salvar(Estante e)
    {
        return eDAO.salvar(e);
    }
    
    public void editar(Estante e)
    {
        eDAO.editar(e);
    }
    
    public int excluir(Estante e)
    {
        return eDAO.excluir(e);
    }
    
    public Estante getEstantes(int e)
    {
        return eDAO.getEstantes(e);
    }
    
    public Estante getEstantes(Leitor leitor, Livro livro)
    {
        return eDAO.getEstantes(leitor, livro);
    }
    
    public List<Estante> getEstantes(Leitor leitor)
    {
        return eDAO.getEstantes(leitor);
    }
    
    public List<Estante> getEstantes(Livro livro)
    {
        return eDAO.getEstantes(livro);
    }
}
