/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.model;

import java.io.Serializable;

/**
 *
 * @author emerson
 */
public class Trecho implements Serializable{
    
    private String companhia;
    private Aeroporto destino;
    private int numPass;

    /**
     * Construtor da classe
     * @param companhia
     * @param destino 
     */
    public Trecho(String companhia, Aeroporto destino, int numPass) {
        this.companhia = companhia;
        this.destino = destino;
        this.numPass = numPass;
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
     * Retorna o numero de passagens
     * @return 
     */
    public int getNumPass() {
        return numPass;
    }

    /**
     * Altera o numero de passagens
     * @param numPass 
     */
    public void setNumPass(int numPass) {
        this.numPass = numPass;
    }
    
    /**
     * Verifica se esse trecho é igual a outro recebido por parametro
     * @param t
     * @return 
     */
    @Override
    public boolean equals(Object t){
        if(t instanceof Trecho){
            if(this.getCompanhia().equals(((Trecho)t).getCompanhia())){
                if(this.getDestino().equals(((Trecho)t).getDestino())){
                    return true;
                }
            }
        }
        return false;
    }
}
