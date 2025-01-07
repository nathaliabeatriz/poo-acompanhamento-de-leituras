package Model.Objetos;

import Model.Objetos.Pessoa;

public class Autor extends Pessoa {   
    
    private static Autor instanciaUnica;
    
    public Autor(){
        
    }
    
    public static synchronized Autor getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Autor();
        }
        return instanciaUnica;
    }
}
