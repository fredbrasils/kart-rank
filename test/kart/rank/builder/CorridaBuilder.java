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

/**
 *
 * @author Fred Brasil
 */
public class CorridaBuilder {

    CorridaModel corrida;
    
    public CorridaBuilder() {
        corrida = new CorridaModel();
    }
    
    public CorridaBuilder addPiloto(PilotoModel piloto){
        if(corrida.getPilotos() == null){
            corrida.setPilotos(new ArrayList<PilotoModel>());
        }
        
        corrida.getPilotos().add(piloto);
        
        return this;
    }
   
    public CorridaModel builder(){
        return corrida;
    }
    
    public static CorridaModel getDefault(){
        
        PilotoModel p1 = new PilotoModel(1l, "Piloto 1");
        List<VoltaModel> voltas = new VoltaBuilder()
                            .addVolta(p1,"1:22.852", new BigDecimal(13.50))
                            .addVolta(p1,"1:22.170", new BigDecimal(13.60))
                            .addVolta(p1,"1:24.769", new BigDecimal(15.50))
                            .builder();
        
        p1.setVoltas(voltas);
        
        PilotoModel p2 = new PilotoModel(2l, "Piloto 2");
        voltas = new VoltaBuilder()
                                    .addVolta(p2,"1:02.852", new BigDecimal(35.55))
                                    .addVolta(p2,"1:03.170", new BigDecimal(36.55))
                                    .addVolta(p2,"1:02.769", new BigDecimal(35.25))
                                    .addVolta(p2,"1:02.787", new BigDecimal(35.27))
                                    .builder();
        p2.setVoltas(voltas);
        
        
        PilotoModel p3 = new PilotoModel(3l, "Piloto 3");
        voltas = new VoltaBuilder()
                                    .addVolta(p3,"1:01.852", new BigDecimal(34.55))
                                    .addVolta(p3,"1:01.170", new BigDecimal(35.15))
                                    .addVolta(p3,"1:01.769", new BigDecimal(34.25))
                                    .addVolta(p3,"1:01.787", new BigDecimal(34.27))
                                    .builder();
        p3.setVoltas(voltas);
        
        
        PilotoModel p4 = new PilotoModel(4l, "Piloto 4");
        voltas = new VoltaBuilder()
                                    .addVolta(p4,"1:02.852", new BigDecimal(35.55))
                                    .addVolta(p4,"1:02.170", new BigDecimal(35.15))
                                    .addVolta(p4,"1:02.769", new BigDecimal(35.25))
                                    .addVolta(p4,"1:02.787", new BigDecimal(35.27))
                                    .builder();
        p4.setVoltas(voltas);
        
        CorridaModel corrida = new CorridaBuilder()
                .addPiloto(p1).addPiloto(p2)
                .addPiloto(p3).addPiloto(p4)
                .builder();
        
        return corrida;
    }
}
