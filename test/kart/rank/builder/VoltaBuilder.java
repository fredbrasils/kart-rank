/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kart.rank.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import kart.rank.model.CorridaModel;
import kart.rank.model.PilotoModel;
import kart.rank.model.VoltaModel;
import kart.rank.util.UtilCalendar;

/**
 *
 * @author Fred Brasil
 */
public class VoltaBuilder {

    List<VoltaModel> voltas;
    
    public VoltaBuilder() {
        voltas = new ArrayList<>();
    }
    
    public VoltaBuilder addVolta(PilotoModel piloto,String tempo, BigDecimal media){
        VoltaModel volta = new VoltaModel(piloto, null, voltas.size(), UtilCalendar.horaOuTempo(tempo), media);
        voltas.add(volta);
        return this;
    }
   
    public List<VoltaModel> builder(){
        return voltas;
    }
}
