package Model.Objetos;

public class Historico {

    public static Historico getInstanciaUnica() {
        return instanciaUnica;
    }

    public static void setInstanciaUnica(Historico aInstanciaUnica) {
        instanciaUnica = aInstanciaUnica;
    }
    private int idHistorico;
    private Estante estante;
    private double porcentagemLeitura;
    private String comentario;
    
    private static Historico instanciaUnica;
    
    public Historico(){
        
    }
    
    public static synchronized Historico getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Historico();
        }
        return instanciaUnica;
    }
    
    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public double getPorcentagemLeitura() {
        return porcentagemLeitura;
    }

    public void setPorcentagemLeitura(double porcentagemLeitura) {
        this.porcentagemLeitura = porcentagemLeitura;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Estante getEstante() {
        return estante;
    }

    public void setEstante(Estante estante) {
        this.estante = estante;
    }
       
}
