
package kart.rank.service;

import java.util.Calendar;
import kart.rank.model.CorridaModel;
import kart.rank.model.PilotoModel;
import kart.rank.model.VoltaModel;
import kart.rank.model.RankModel;
import kart.rank.util.UtilCalendar;

/**
 *
 * @author Fred Brasil
 */
public class CorridaServiceImpl implements CorridaService{
    
   /**
   * Mostra o rank da corrida
   * @param corrida
   */
    @Override
    public void rank(CorridaModel corrida){
        
        RankModel rank = null;
        
        // percorre a lista de pilotos da corrida
        for(PilotoModel piloto : corrida.getPilotos()){
            
            if(rank == null){
                rank = new RankModel(piloto, UtilCalendar.somaTempo(piloto.getVoltas()), piloto.getVoltas().size());
            }else{
                
                RankModel rank_proximo = new RankModel(piloto, UtilCalendar.somaTempo(piloto.getVoltas()), piloto.getVoltas().size());
                ranquear(rank,rank_proximo,true);
                
                /*
                if(rank_proximo.getQntVoltas() < rank.getQntVoltas()
                    || (rank_proximo.getQntVoltas() == rank.getQntVoltas() 
                        && rank.getTempo().getTimeInMillis() < rank_proximo.getTempo().getTimeInMillis()) ){
                    
                    //verifica se tem o próximo do rank
                    if(rank.getProximo() != null){
                        ranquear(rank,rank_proximo);
                    }else{
                        rank.setProximo(rank_proximo);
                    }
                }else{
                    rank_proximo.setProximo(rank);
                    rank = rank_proximo;
                }
                */
            }
        }
        
        corrida.setRank(rank);
        
    }
    
    /*
    private void ranquear(RankModel rank, RankModel rank_proximo){

        // verifica se tem menos volta de quem tá no rank ou 
        // se tem a mesma quantidade de voltas mais o tempo é maior
        if(rank_proximo.getQntVoltas() < rank.getProximo().getQntVoltas()
            || (rank_proximo.getQntVoltas() == rank.getProximo().getQntVoltas() 
                && rank.getProximo().getTempo().getTimeInMillis() < rank_proximo.getTempo().getTimeInMillis()) ){

            //verifica se tem o próximo do rank
            if(rank.getProximo().getProximo() != null){
                ranquear(rank.getProximo(),rank_proximo);
            }else{
                rank.getProximo().setProximo(rank_proximo);
            }
        }else{
            rank_proximo.setProximo(rank.getProximo());
            rank.setProximo(rank_proximo);
        }
    }
    */
    
   /**
   * Ranquea os pilotos da corrida
   * @param rank rank atual
   * @param rank_proximo piloto a ser adicionado no rank
   * @param primeiroRank flag para validar o primeiro do rank
   */
    private void ranquear(RankModel rank, RankModel rank_proximo,boolean primeiroRank){

        RankModel rankAux;
        
        if(primeiroRank){
            rankAux = rank;
        }else{
            rankAux = rank.getProximo();
        }
        
        // verifica se tem menos volta de quem tá no rank ou 
        // se tem a mesma quantidade de voltas mais o tempo é maior
        if(rank_proximo.getQntVoltas() < rankAux.getQntVoltas()
            || (rank_proximo.getQntVoltas() == rankAux.getQntVoltas() 
                && rankAux.getTempo().getTimeInMillis() < rank_proximo.getTempo().getTimeInMillis()) ){

            //verifica se tem o próximo do rank
            if(rankAux.getProximo() != null){
                ranquear(rankAux,rank_proximo,false);
            }else{
                rankAux.setProximo(rank_proximo);
            }
        }else{
            rank_proximo.setProximo(rankAux);
            if(primeiroRank){
                rankAux = rank_proximo;
                rank = rankAux;
            }
            rank.setProximo(rank_proximo);
        }
    }
    
   /**
   * Definir a melhor voltas da corrida
   * @param corrida
   */
    @Override
    public void melhorVolta(CorridaModel corrida){
    
        //busca as melhores voltas da corrida por piloto
        PilotoServiceImpl pilotoService = new PilotoServiceImpl();
        pilotoService.melhorVoltaPorPiloto(corrida);
        
        // percorre as melhores voltas da corrida
        for(PilotoModel piloto : corrida.getMelhoresVoltasPorPiloto().keySet()){
            
            //obtem a melhor volta do piloto
            VoltaModel volta = corrida.getMelhoresVoltasPorPiloto().get(piloto);
            
            if(corrida.getMelhorVolta() == null 
                        || corrida.getMelhorVolta().getTempo().getTimeInMillis() > volta.getTempo().getTimeInMillis()){
                corrida.setMelhorVolta(volta);
            }
        }
    }
   
    /**
   * Define o tempo para o lider
   * @param rank
   */
    @Override
    public void diferencaTempoVencedor(RankModel rank){
        
        Calendar tempoLider = rank.getTempo();
        int qntVoltas = rank.getQntVoltas();
        RankModel rank_aux = rank.getProximo();
        
        while(rank_aux != null){

            if(qntVoltas == rank_aux.getQntVoltas()){
                rank_aux.setTempoParaLider(UtilCalendar.subtraiTempo(tempoLider, rank_aux.getTempo()));
            }
            
            rank_aux = rank_aux.getProximo();
        }
    }
    
}
