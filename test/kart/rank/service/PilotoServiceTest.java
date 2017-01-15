
package kart.rank.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import kart.rank.builder.CorridaBuilder;
import kart.rank.model.CorridaModel;
import kart.rank.model.PilotoModel;
import kart.rank.util.Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Fred Brasil
 */
public class PilotoServiceTest {
    

    @Test
    public void retornaListaComAsMelhoresVoltasPorPiloto(){
    
        CorridaModel corrida = CorridaBuilder.getDefault();
        
        PilotoServiceImpl service = new PilotoServiceImpl();
        
        service.melhorVoltaPorPiloto(corrida);
        
        PilotoModel p1 = new PilotoModel(1l, "Piloto 1");
        PilotoModel p2 = new PilotoModel(2l, "Piloto 2");
        PilotoModel p3 = new PilotoModel(3l, "Piloto 3");
        PilotoModel p4 = new PilotoModel(4l, "Piloto 4");
        
        Assert.assertEquals("01:22.170",Utils.tempoFormatado(corrida.getMelhoresVoltasPorPiloto().get(p1).getTempo()));
        Assert.assertEquals("01:02.769", Utils.tempoFormatado(corrida.getMelhoresVoltasPorPiloto().get(p2).getTempo()));
        Assert.assertEquals("01:01.170",Utils.tempoFormatado(corrida.getMelhoresVoltasPorPiloto().get(p3).getTempo()));
        Assert.assertEquals("01:02.170",Utils.tempoFormatado(corrida.getMelhoresVoltasPorPiloto().get(p4).getTempo()));
        
    }
    
    @Test
    public void retornaListaComAsMediasDeVelocidadePorPiloto(){
    
        CorridaModel corrida = CorridaBuilder.getDefault();
        
        PilotoServiceImpl service = new PilotoServiceImpl();
        
        service.mediaVelocidadePorPiloto(corrida);
        
        PilotoModel p1 = new PilotoModel(1l, "Piloto 1");
        PilotoModel p2 = new PilotoModel(2l, "Piloto 2");
        PilotoModel p3 = new PilotoModel(3l, "Piloto 3");
        PilotoModel p4 = new PilotoModel(4l, "Piloto 4");
        
        Assert.assertEquals(new BigDecimal(14.200).setScale(3,RoundingMode.HALF_EVEN), corrida.getMediaVelocidadePorPiloto().get(p1));
        Assert.assertEquals(new BigDecimal(35.655).setScale(3,RoundingMode.HALF_EVEN), corrida.getMediaVelocidadePorPiloto().get(p2));
        Assert.assertEquals(new BigDecimal(34.555).setScale(3,RoundingMode.HALF_EVEN), corrida.getMediaVelocidadePorPiloto().get(p3));
        Assert.assertEquals(new BigDecimal(35.305).setScale(3,RoundingMode.HALF_EVEN), corrida.getMediaVelocidadePorPiloto().get(p4));
        
    }
}
