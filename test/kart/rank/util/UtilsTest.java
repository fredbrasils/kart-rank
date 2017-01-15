
package kart.rank.util;

import java.util.Calendar;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Fred Brasil
 */
public class UtilsTest {
    
    @Test
    public void retornaTempoFormatado() {
    
        String hora = "01:08.277";
        
        Calendar cal = UtilCalendar.horaOuTempo(hora);
        
        Assert.assertEquals("01:08.277",Utils.tempoFormatado(cal));
    }
}
