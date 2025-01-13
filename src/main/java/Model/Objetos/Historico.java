package Model.Objetos;

public class Historico {

    private int idHistorico;
    private Estante estante;
    private double porcentagemLeitura;
    private String comentario;
    
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
