
package Model.Objetos;

public class Avaliacao {

    public static Avaliacao getInstanciaUnica() {
        return instanciaUnica;
    }

    public static void setInstanciaUnica(Avaliacao aInstanciaUnica) {
        instanciaUnica = aInstanciaUnica;
    }
    private int idAvaliacao;
    private int estrelas;
    private String titulo;
    private String resenha;
    private Estante estante;
    
    private static Avaliacao instanciaUnica;
    
    public Avaliacao(){
        
    }
    
    public static synchronized Avaliacao getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Avaliacao();
        }
        return instanciaUnica;
    }
    
    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public Estante getEstante() {
        return estante;
    }

    public void setEstante(Estante estante) {
        this.estante = estante;
    }
    
    
}
