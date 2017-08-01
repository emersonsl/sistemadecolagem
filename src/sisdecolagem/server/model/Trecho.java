/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.model;

/**
 *
 * @author emerson
 */
public class Trecho {
    
    private String companhia;
    private Aeroporto destino;

    /**
     * Construtor da classe
     * @param companhia
     * @param destino 
     */
    public Trecho(String companhia, Aeroporto destino) {
        this.companhia = companhia;
        this.destino = destino;
    }

    /**
     * Retorna a companhia 
     * @return 
     */
    public String getCompanhia() {
        return companhia;
    }

    /**
     * Altera a companhia
     * @param companhia 
     */
    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    /**
     * Retorna o destino do techo
     * @return 
     */
    public Aeroporto getDestino() {
        return destino;
    }

    /**
     * Altera o destino do trecho
     * @param destino 
     */
    public void setDestino(Aeroporto destino) {
        this.destino = destino;
    }
    
    /**
     * Verifica se esse trecho é igual a outro recebido por parametro
     * @param t
     * @return 
     */
    public boolean equals(Trecho t){
        if(this.getCompanhia().equals(t.getCompanhia())){
            if(this.getDestino().equals(t.getDestino())){
                return true;
            }
        }
        return false;
    }
}
