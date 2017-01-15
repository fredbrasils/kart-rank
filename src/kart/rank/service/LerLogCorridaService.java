
package kart.rank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import kart.rank.model.VoltaModel;
import kart.rank.model.PilotoModel;
import kart.rank.model.CorridaModel;
import kart.rank.util.UtilCalendar;

/**
 *
 * @author Fred Brasil
 */
public class LerLogCorridaService {

    /**
   * Ler o log da corrida e retorna os dados da corrida  
   * @param logFile
   * @return CorridaModel.
   */
    public CorridaModel lerLogDaCorrida(String logFile) {
        
        CorridaModel corrida = new CorridaModel();
        FileReader fileReader;
        
        try {
            fileReader = new FileReader(logFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
                lerLinha(line,corrida);
            }
            fileReader.close();
            reader.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LerLogCorridaService.class.getName()).log(Level.SEVERE, "Arquivo não encontrado.", ex);
        } catch (IOException ex) {
            Logger.getLogger(LerLogCorridaService.class.getName()).log(Level.SEVERE, "Erro", ex);
        }
        
        return corrida;
    }

    
    /**
   * Ler a linha do log e adiciona no objeto corrida  
   * @param line
   * @param corrida
   */
    private void lerLinha(String line, CorridaModel corrida) {
    
        /* 
            0 - Hora
            1 - ID do Piloto 
            2 - "-"
            3 - Nome do Piloto 
            4 - Voltas
            5 - Tempo de volta 
            6 - Média de velocidade da volta  
        */
        String[] data = line.split("\\s+");
        
        PilotoModel piloto = new PilotoModel(Long.parseLong(data[1]), data[3]);
        VoltaModel volta = new VoltaModel(piloto, UtilCalendar.horaOuTempo(data[0]), 
                          Integer.parseInt(data[4]), 
                          UtilCalendar.horaOuTempo(data[5]), 
                          new BigDecimal(data[6].replace(",", ".")).setScale(3));
        
        // verifica se o piloto está na lista
        if(corrida.getPilotos().contains(piloto)){
            piloto = corrida.getPilotos().get(corrida.getPilotos().indexOf(piloto));
        }else{
            corrida.getPilotos().add(piloto);
        }
        piloto.addVolta(volta);
    }
}
