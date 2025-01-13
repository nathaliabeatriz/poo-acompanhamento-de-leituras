
package Util;

import Controller.LivroController;
import Model.Objetos.Livro;


public class ObserverMediaAvaliacao {
    public void atualizarMedia(double avaliacao, Livro livro){
        LivroController livroController = new LivroController();
        double media = livro.getMediaAvaliacao() + avaliacao;
        int quantAva = livroController.quantidadeAvaliacoesPorLivro(livro);
        double novaMedia = media/(double)quantAva;
        livro.setMediaAvaliacao(novaMedia);
        livroController.atualizarMedia(livro);
    }
}
