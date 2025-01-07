
package Model.Objetos;

public class Genero {
    private int idGenero;
    private String descricao;

    private static Genero instanciaUnica;
    
    public Genero(){
        
    }
    
    public static synchronized Genero getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Genero();
        }
        return instanciaUnica;
    }

     
    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
