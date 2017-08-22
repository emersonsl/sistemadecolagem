/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdecolagem.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emerson
 */
public class Aeroporto implements Serializable{
    private String cidade;
    private boolean visitado;
    private List <Trecho> trechos;

    /**
     * Construtor da classe
     * @param cidade 
     */
    public Aeroporto(String cidade) {
        this.cidade = cidade;
        this.visitado = false;
        this.trechos = new ArrayList<>();
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
    @Override
    public boolean equals(Object a){
        if(a instanceof Aeroporto)
            return ((Aeroporto)a).getCidade().equals(this.getCidade()); //compara se o aeroporto é igual, pelo nome da cidade
        return false;
    }
    
    /**
     * Adiciona um thecho se ainda não existir
     * @param a 
     */
    public void addTrecho(Trecho a){
        if(this.trechos.isEmpty() || !this.trechos.contains(a)){ //verifica se o destino não existe
            System.out.println("add: "+this.cidade+"->"+a.getDestino().cidade);
            this.trechos.add(a); //adiciona o destino
        }
    }

    /**
     * Retorna os trechos deste aeroporto
     * @return 
     */
    public List<Trecho> getTrechos() {
        return trechos;
    }
    
    
    public Trecho getTrecho(Aeroporto a){
        for(Trecho t: trechos){
            if(t.getDestino().equals(a)){
                return t;
            }
        }
        return null;
    }
    
    /**
     * Retorna texto para impressão do objeto
     * @return 
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Cidade: ").append(this.cidade);
        return s.toString();
    }
}
