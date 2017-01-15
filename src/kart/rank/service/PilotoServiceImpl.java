/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kart.rank.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import kart.rank.model.CorridaModel;
import kart.rank.model.PilotoModel;
import kart.rank.model.VoltaModel;

/**
 *
 * @author Fred Brasil
 */
public class PilotoServiceImpl implements PilotoService{
    
   /**
   * Definir as melhores voltas dos pilotos na corrida
   * @param corrida
   */
    @Override
    public void melhorVoltaPorPiloto(CorridaModel corrida){
        
        Map<PilotoModel,VoltaModel> melhorVoltaPorPiloto = new HashMap<>();
        
        // percorre a lista de pilotos da corrida
        for(PilotoModel piloto : corrida.getPilotos()){
            
            VoltaModel melhorVolta = null;
            
            // percorre as voltas do piloto
            for(VoltaModel volta: piloto.getVoltas()){
                
                if(melhorVolta == null 
                        || melhorVolta.getTempo().getTimeInMillis() > volta.getTempo().getTimeInMillis()){
                    melhorVolta = volta;
                }
            }
            
            //adiciona o piloto e sua melhor volta
            melhorVoltaPorPiloto.put(piloto, melhorVolta);
        }
        
        // adiciona à corrida as melhores voltas por piloto
        corrida.setMelhoresVoltasPorPiloto(melhorVoltaPorPiloto);
    }
   
    /**
   * Definir a média de velocidade dos pilotos na corrida
   * @param corrida
   */
    @Override
    public void mediaVelocidadePorPiloto(CorridaModel corrida){
        
        Map<PilotoModel,BigDecimal> mediaVelocidadePorPiloto = new HashMap<>();
        
        // percorre a lista de pilotos da corrida
        for(PilotoModel piloto : corrida.getPilotos()){
            
            BigDecimal mediaVelocidade = BigDecimal.ZERO;
            
            // percorre as voltas do piloto
            for(VoltaModel volta: piloto.getVoltas()){
                // soma as médias
                mediaVelocidade = mediaVelocidade.add(volta.getMediaVelocidade()).setScale(3,RoundingMode.HALF_EVEN);
            }
            
            // divide a soma das médias pelo total de voltas
            //mediaVelocidade = mediaVelocidade.divide(new BigDecimal(piloto.getVoltas().size()));
            mediaVelocidade = mediaVelocidade.divide(new BigDecimal(piloto.getVoltas().size()),3,RoundingMode.HALF_EVEN);
            
            //adiciona o piloto e sua melhor volta
            mediaVelocidadePorPiloto.put(piloto, mediaVelocidade);
        }
        
        // adiciona à corrida as melhores voltas por piloto
        corrida.setMediaVelocidadePorPiloto(mediaVelocidadePorPiloto);
    }
}
