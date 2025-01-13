package Controller;

import Model.DAO.HistoricoDAO;
import Model.Objetos.Estante;
import Model.Objetos.Historico;
import java.util.List;

public class HistoricoController {
    HistoricoDAO hDAO;
    
    public HistoricoController() 
    {
        hDAO = new HistoricoDAO();
    }    
    
    public Historico salvar(Historico h)
    {
        return hDAO.salvar(h);
    }
    
    public void editar(Historico h)
    {
        hDAO.editar(h);
    }
    
    public int excluir(Historico h)
    {
        return hDAO.excluir(h);
    }
    
    public List<Historico> getHistoricos()
    {
        return hDAO.getHistoricos();
    }
    
    public List<Historico> getHistoricos(Estante estante)
    {
        return hDAO.getHistoricos(estante);
    }
        
}
