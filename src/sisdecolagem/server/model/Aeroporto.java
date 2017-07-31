/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emerson
 */
public class Aeroporto {
    private String cidade;
    private boolean visitado;
    private List <Aeroporto> destinos;

    /**
     * Construtor da classe
     * @param cidade 
     */
    public Aeroporto(String cidade) {
        this.cidade = cidade;
        this.destinos = new ArrayList<>();
    }
    
    /**
     * Retorna a cidade do aeroporto
     * @return 
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Modifica a cidade do aeroporto
     * @param cidade 
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Verifica se está sendo vizitado
     * @return 
     */
    public boolean isVisitado() {
        return visitado;
    }

    /**
     * Muda o status de vizitado
     * @param visitado 
     */
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    
    /**
     * Verifica se o aeroporto é igual tomando como base a cidade
     * @param a
     * @return 
     */
    public boolean equals(Aeroporto a){
        return a.getCidade().equals(this.getCidade()); //compara se o aeroporto é igual, pelo nome da cidade
    }
    
    /**
     * Adiciona um destino se ainda não existir
     * @param a 
     */
    public void addDestino(Aeroporto a){
        if(!this.destinos.contains(a)){ //derifica se o destino não existe
            this.destinos.add(a); //adiciona o destino
        }
    }

}
