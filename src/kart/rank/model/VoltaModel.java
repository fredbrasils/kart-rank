
package kart.rank.model;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 *
 * @author Fred Brasil
 */
public class VoltaModel {
    
    private final PilotoModel piloto;
    private final Calendar hora;
    private final Integer numero;
    private final Calendar tempo;
    private final BigDecimal mediaVelocidade;

    public VoltaModel(PilotoModel piloto,Calendar hora, Integer numero, Calendar tempo, BigDecimal mediaVelocidade) {
        this.piloto = piloto;
        this.hora = hora;
        this.numero = numero;
        this.tempo = tempo;
        this.mediaVelocidade = mediaVelocidade;
    }

    public Calendar getHora() {
        return hora;
    }

    public PilotoModel getPiloto() {
        return piloto;
    }

    public Integer getNumero() {
        return numero;
    }

    public Calendar getTempo() {
        return tempo;
    }

    public BigDecimal getMediaVelocidade() {
        return mediaVelocidade;
    }
    
    @Override
    public String toString() {
        return "Volta{" + "hora=" + hora + ", numero=" + numero + ", tempo=" + tempo + ", mediaVelocidade=" + mediaVelocidade + '}';
    }
    
}
