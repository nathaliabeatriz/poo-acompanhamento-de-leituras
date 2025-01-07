package Model.Objetos;

public class Estante {
    private int idEstante;
    private Leitor leitor;
    private Livro livro;
    private String status;

    private static Estante instanciaUnica;
    
    public Estante(){
        
    }

    public static synchronized Estante getInstancia(){
        if (instanciaUnica == null){
            instanciaUnica = new Estante();
        }
        return instanciaUnica;
    }
    
    public int getIdEstante() {
        return idEstante;
    }

    public void setIdEstante(int idEstante) {
        this.idEstante = idEstante;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    } 
}
