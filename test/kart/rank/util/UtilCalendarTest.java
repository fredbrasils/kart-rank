
package kart.rank.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import junit.framework.Assert;
import kart.rank.builder.VoltaBuilder;
import kart.rank.model.VoltaModel;
import org.junit.Test;

/**
 *
 * @author Fred Brasil
 */
public class UtilCalendarTest {
    
    @Test
    public void retornaHora() {
    
        String hora = "23:49:08.277";
        
        Calendar cal = UtilCalendar.horaOuTempo(hora);
        
        Assert.assertEquals(cal.get(Calendar.HOUR_OF_DAY),23);
        Assert.assertEquals(cal.get(Calendar.MINUTE),49);
        Assert.assertEquals(cal.get(Calendar.SECOND),8);
        Assert.assertEquals(cal.get(Calendar.MILLISECOND),277);
    }
    
    @Test
    public void retornaTempo() {
    
        String time = "1:02.852";
        
        Calendar cal = UtilCalendar.horaOuTempo(time);
        
        Assert.assertEquals(cal.get(Calendar.MINUTE),1);
        Assert.assertEquals(cal.get(Calendar.SECOND),2);
        Assert.assertEquals(cal.get(Calendar.MILLISECOND),852);
    }
    
    @Test
    public void retornaSomaDoTempo() {
    
        List<VoltaModel> voltas = new VoltaBuilder()
                .addVolta(null,"1:02.852", new BigDecimal(0.0))
                .addVolta(null,"1:03.170", new BigDecimal(0.0))
                .addVolta(null,"1:02.769", new BigDecimal(0.0))
                .addVolta(null,"1:02.787", new BigDecimal(0.0))
                .builder();
        
        Calendar cal = UtilCalendar.somaTempo(voltas);
        
        Assert.assertEquals(cal.get(Calendar.MINUTE),4);
        Assert.assertEquals(cal.get(Calendar.SECOND),11);
        Assert.assertEquals(cal.get(Calendar.MILLISECOND),578);
    }
    
    @Test
    public void retornaSubtracaoDoTempo() {
        
        Calendar tempo1 = Calendar.getInstance();
        tempo1.set(Calendar.MINUTE, 1);
        tempo1.set(Calendar.SECOND, 2);
        tempo1.set(Calendar.MILLISECOND, 787);
        
        Calendar tempo2 = Calendar.getInstance();
        tempo2.set(Calendar.MINUTE, 1);
        tempo2.set(Calendar.SECOND, 4);
        tempo2.set(Calendar.MILLISECOND, 10);

        Calendar cal = UtilCalendar.subtraiTempo(tempo1, tempo2);
        
        Assert.assertEquals(0,cal.get(Calendar.MINUTE));
        Assert.assertEquals(1,cal.get(Calendar.SECOND));
        Assert.assertEquals(223,cal.get(Calendar.MILLISECOND));
    }
    
    @Test
    public void retornaSubtracaoDoTempo2() {
        
        Calendar tempo1 = Calendar.getInstance();
        tempo1.set(Calendar.MINUTE, 1);
        tempo1.set(Calendar.SECOND, 4);
        tempo1.set(Calendar.MILLISECOND, 787);
        
        Calendar tempo2 = Calendar.getInstance();
        tempo2.set(Calendar.MINUTE, 2);
        tempo2.set(Calendar.SECOND, 2);
        tempo2.set(Calendar.MILLISECOND, 100);

        Calendar cal = UtilCalendar.subtraiTempo(tempo1, tempo2);
        
        Assert.assertEquals(0,cal.get(Calendar.MINUTE));
        Assert.assertEquals(59,cal.get(Calendar.SECOND));
        Assert.assertEquals(313,cal.get(Calendar.MILLISECOND));
    }
    
    @Test
    public void retornaSubtracaoDoTempo3() {
        
        Calendar tempo1 = Calendar.getInstance();
        tempo1.set(Calendar.MINUTE, 1);
        tempo1.set(Calendar.SECOND, 4);
        tempo1.set(Calendar.MILLISECOND, 787);
        
        Calendar tempo2 = Calendar.getInstance();
        tempo2.set(Calendar.MINUTE, 2);
        tempo2.set(Calendar.SECOND, 2);
        tempo2.set(Calendar.MILLISECOND, 900);

        Calendar cal = UtilCalendar.subtraiTempo(tempo1, tempo2);
        
        Assert.assertEquals(0,cal.get(Calendar.MINUTE));
        Assert.assertEquals(58,cal.get(Calendar.SECOND));
        Assert.assertEquals(113,cal.get(Calendar.MILLISECOND));
    }
    
    @Test
    public void retornaSubtracaoDoTempo4() {
        
        Calendar tempo1 = Calendar.getInstance();
        tempo1.set(Calendar.MINUTE, 1);
        tempo1.set(Calendar.SECOND, 2);
        tempo1.set(Calendar.MILLISECOND, 787);
        
        Calendar tempo2 = Calendar.getInstance();
        tempo2.set(Calendar.MINUTE, 2);
        tempo2.set(Calendar.SECOND, 2);
        tempo2.set(Calendar.MILLISECOND, 787);

        Calendar cal = UtilCalendar.subtraiTempo(tempo1, tempo2);
        /*
        java.text.DateFormat dfo = new java.text.SimpleDateFormat("mm:ss.SSS");
        dfo.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        System.out.println(dfo.format(cal.getTime()));
        */
        Assert.assertEquals(1,cal.get(Calendar.MINUTE));
        Assert.assertEquals(0,cal.get(Calendar.SECOND));
        Assert.assertEquals(000,cal.get(Calendar.MILLISECOND));
    }
    
    @Test
    public void retornaSubtracaoDoTempo5() {
        
        Calendar tempo1 = Calendar.getInstance();
        tempo1.set(Calendar.MINUTE, 1);
        tempo1.set(Calendar.SECOND, 2);
        tempo1.set(Calendar.MILLISECOND, 787);
        
        Calendar tempo2 = Calendar.getInstance();
        tempo2.set(Calendar.MINUTE, 2);
        tempo2.set(Calendar.SECOND, 2);
        tempo2.set(Calendar.MILLISECOND, 900);

        Calendar cal = UtilCalendar.subtraiTempo(tempo1, tempo2);
        
        Assert.assertEquals(1,cal.get(Calendar.MINUTE));
        Assert.assertEquals(0,cal.get(Calendar.SECOND));
        Assert.assertEquals(113,cal.get(Calendar.MILLISECOND));
    }
    
    @Test
    public void retornaSubtracaoDoTempo6() {
        
        Calendar tempo1 = Calendar.getInstance();
        tempo1.set(Calendar.MINUTE, 1);
        tempo1.set(Calendar.SECOND, 10);
        tempo1.set(Calendar.MILLISECOND, 800);
        
        Calendar tempo2 = Calendar.getInstance();
        tempo2.set(Calendar.MINUTE, 1);
        tempo2.set(Calendar.SECOND, 20);
        tempo2.set(Calendar.MILLISECOND, 700);

        Calendar cal = UtilCalendar.subtraiTempo(tempo1, tempo2);
                
        Assert.assertEquals(0,cal.get(Calendar.MINUTE));
        Assert.assertEquals(9,cal.get(Calendar.SECOND));
        Assert.assertEquals(900,cal.get(Calendar.MILLISECOND));
    }
    
}
