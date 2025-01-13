package Controller;

import Model.Objetos.Editora;
import Model.DAO.EditoraDAO;
import java.util.List;

public class EditoraController {
    
    EditoraDAO eDAO;
    
    public EditoraController() 
    {
        eDAO = new EditoraDAO();
    }    
    
    public Editora salvar(Editora e)
    {
        return eDAO.salvar(e);
    }
    
    public void editar(Editora e)
    {
        eDAO.editar(e);
    }
    
    public int excluir(Editora e)
    {
        return eDAO.excluir(e);
    }
    
    public List<Editora> getEditoras()
    {
        return eDAO.getEditoras();
    }
    
    public Editora getEditoras(int id)
    {
        return eDAO.getEditoras(id);
    }
    
    public List<Editora> getEditoras(String nome)
    {
        return eDAO.getEditoras(nome);
    }
}
