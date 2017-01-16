
package kart.rank.service;

import kart.rank.builder.CorridaBuilder;
import kart.rank.model.CorridaModel;
import kart.rank.model.RankModel;
import kart.rank.util.Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Fred Brasil
 */
public class CorridaServiceTest {
    
    @Test
    public void retornaRank(){
    
        CorridaModel corrida = CorridaBuilder.getDefault();
        
        CorridaServiceImpl service = new CorridaServiceImpl();
        
        service.rank(corrida);
        RankModel rank = corrida.getRank();
        
        Assert.assertEquals(new Long(3), rank.getPiloto().getId());
        Assert.assertEquals(new Long(4), rank.getProximo().getPiloto().getId());
        Assert.assertEquals(new Long(2), rank.getProximo().getProximo().getPiloto().getId());
        Assert.assertEquals(new Long(1), rank.getProximo().getProximo().getProximo().getPiloto().getId());
    }
    
    @Test
    public void retornaAMelhorVoltaDaCorrida(){
    
        CorridaModel corrida = CorridaBuilder.getDefault();
        
        CorridaServiceImpl service = new CorridaServiceImpl();
        
        service.melhorVolta(corrida);
        
        Assert.assertEquals(new Long(3), corrida.getMelhorVolta().getPiloto().getId());
        Assert.assertEquals("01:01.170", Utils.tempoFormatado(corrida.getMelhorVolta().getTempo()));
        
    }
    
    @Test
    public void retornaAsDiferecaDeTempoParaLider(){
    
        CorridaModel corrida = CorridaBuilder.getDefault();
        
        CorridaServiceImpl service = new CorridaServiceImpl();
        
        service.rank(corrida);
        RankModel rank = corrida.getRank();
        service.diferencaTempoVencedor(rank);
        
        Assert.assertEquals(new Long(4), corrida.getRank().getProximo().getPiloto().getId());
        Assert.assertEquals("00:04.0", Utils.tempoFormatado(corrida.getRank().getProximo().getTempoParaLider()));
        
        Assert.assertEquals(new Long(2), corrida.getRank().getProximo().getProximo().getPiloto().getId());
        Assert.assertEquals("00:05.0", Utils.tempoFormatado(corrida.getRank().getProximo().getProximo().getTempoParaLider()));
        
        Assert.assertEquals(new Long(1), corrida.getRank().getProximo().getProximo().getProximo().getPiloto().getId());
        Assert.assertNull(corrida.getRank().getProximo().getProximo().getProximo().getTempoParaLider());
        
    }
}
