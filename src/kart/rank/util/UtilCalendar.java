package kart.rank.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import kart.rank.model.VoltaModel;

/**
 *
 * @author Fred Brasil
 */
public class UtilCalendar {

    /**
     * Transforma o tempo (String) em um objeto do tipo Calendar
     *
     * @param tempoOuHora
     * @return Calendar.
     */
    public static Calendar horaOuTempo(String tempoOuHora) {

        if (tempoOuHora.split(":").length > 2) {
            return horaOuTempoNoFormato(tempoOuHora, "HH:mm:ss.SSS");
        } else {
            return horaOuTempoNoFormato(tempoOuHora, "mm:ss.SSS");
        }
    }

    /**
     * Transforma o tempo (String) em um objeto do tipo Calendar a partir da
     * formatação
     *
     * @param tempoOuHora
     * @param formato
     * @return Calendar.
     */
    private static Calendar horaOuTempoNoFormato(String tempoOuHora, String formato) {

        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            cal.setTime(sdf.parse(tempoOuHora));

            return cal;
        } catch (ParseException ex) {
            Logger.getLogger(UtilCalendar.class.getName()).log(Level.SEVERE, "Problema na conversão", ex);
        }

        return null;
    }

    /**
     * Retorna a soma dos tempos
     *
     * @param voltas Lista de voltas
     * @return Calendar.
     */
    public static Calendar somaTempo(List<VoltaModel> voltas) {

        long tempo = 0;
        for (VoltaModel volta : voltas) {
            tempo += volta.getTempo().getTimeInMillis();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(tempo);

        return cal;
    }

    /**
     * Retorna a subtração dos tempos
     *
     * @param tempoLider
     * @param tempo
     * @return Calendar.
     */
    public static Calendar subtraiTempo(Calendar tempoLider, Calendar tempo) {

        int milisegundos = 0;
        int segundos = 0;
        int minutos = 0;
        
        if(tempoLider.get(Calendar.MILLISECOND) >= tempo.get(Calendar.MILLISECOND)){
            milisegundos = tempoLider.get(Calendar.MILLISECOND) - tempo.get(Calendar.MILLISECOND);
            
            if(tempoLider.get(Calendar.MILLISECOND) > tempo.get(Calendar.MILLISECOND)){
                milisegundos = 1000 - milisegundos;
                segundos--;
            }
            
        }else{
            milisegundos = tempo.get(Calendar.MILLISECOND) - tempoLider.get(Calendar.MILLISECOND);
        }
        
        if(tempoLider.get(Calendar.SECOND) > tempo.get(Calendar.SECOND)){
            segundos += tempoLider.get(Calendar.SECOND) - tempo.get(Calendar.SECOND);
            segundos = 60 - segundos;
            minutos--;
        }else{
            segundos += tempo.get(Calendar.SECOND) - tempoLider.get(Calendar.SECOND);
        }
        
        if(tempoLider.get(Calendar.SECOND) == tempo.get(Calendar.SECOND)){
            minutos = tempo.get(Calendar.MINUTE) - tempoLider.get(Calendar.MINUTE);
        }else{
            minutos += tempo.get(Calendar.MINUTE) - tempoLider.get(Calendar.MINUTE);
        }
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, minutos);
        cal.set(Calendar.SECOND, segundos);
        cal.set(Calendar.MILLISECOND, milisegundos);
       
        return cal;
    }

}
