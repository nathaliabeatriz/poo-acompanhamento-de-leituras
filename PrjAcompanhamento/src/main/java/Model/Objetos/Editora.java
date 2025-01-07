package Model.Objetos;

public class Editora {
    private int idEditora;
    private String nome;
    private String sede;
    
    private static Editora instanciaUnica;
    
    public Editora(){
        
    }
    
    public static synchronized Editora getInstancia(){
        if (instanciaUnica == null){
            instanciaUnica = new Editora();
        }
        return instanciaUnica;
    }
    
    public int getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(int idEditora) {
        this.idEditora = idEditora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
    
    
}
