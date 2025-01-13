package Controller;

import Model.DAO.AvaliacaoDAO;
import Model.Objetos.Avaliacao;
import Model.Objetos.Estante;
import java.util.List;

public class AvaliacaoController {
    AvaliacaoDAO aDAO;
    
    public AvaliacaoController() 
    {
        aDAO = new AvaliacaoDAO();
    }    
    
    public Avaliacao salvar(Avaliacao a)
    {
        return aDAO.salvar(a);
    }
    
    public void editar(Avaliacao a)
    {
        aDAO.editar(a);
    }
    
    public int excluir(Avaliacao a)
    {
        return aDAO.excluir(a);
    }
    
    public List<Avaliacao> getAvaliacoes()
    {
        return aDAO.getAvaliacoes();
    }
    
     public List<Avaliacao> getAvaliacoes(Estante estante)
    {
        return aDAO.getAvaliacoes(estante);
    }
}
