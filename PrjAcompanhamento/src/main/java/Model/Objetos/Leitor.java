package Model.Objetos;

public class Leitor extends Pessoa {
    private String email;
    
    private static Leitor instanciaUnica;
    
    public Leitor(){
        
    }
    
    public static synchronized Leitor getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Leitor();
        }
        return instanciaUnica;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
