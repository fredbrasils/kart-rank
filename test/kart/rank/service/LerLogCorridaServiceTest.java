
package kart.rank.service;

import junit.framework.Assert;
import kart.rank.model.CorridaModel;
import org.junit.Test;

/**
 *
 * @author Fred Brasil
 */
public class LerLogCorridaServiceTest {
    
    private static final String LOG_FILE = "./RaceLogTest.txt";
    
    @Test
    public void lerArquivo() {
    
        LerLogCorridaService rrl = new LerLogCorridaService();
        CorridaModel race = rrl.lerLogDaCorrida(LOG_FILE);
        
        Assert.assertEquals(6, race.getPilotos().size());
        
    }
}
