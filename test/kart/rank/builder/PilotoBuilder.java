/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kart.rank.builder;

import java.util.List;
import kart.rank.model.PilotoModel;
import kart.rank.model.VoltaModel;

/**
 *
 * @author Fred Brasil
 */
public class PilotoBuilder {

    PilotoModel piloto;
    
    public PilotoBuilder(Long id, String nome) {
        piloto = new PilotoModel(id, nome);
    }
    
    public PilotoBuilder addVolta(VoltaModel volta){
        piloto.addVolta(volta);
        return this;
    }
    
    public PilotoBuilder addVoltas(List<VoltaModel> voltas){
        piloto.setVoltas(voltas);
        return this;
    }
   
    public PilotoModel builder(){
        return piloto;
    }
}
