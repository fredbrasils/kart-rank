
package kart.rank.model;

import java.util.Calendar;

/**
 *
 * @author Fred Brasil
 */
public class RankModel {
    
    private final PilotoModel piloto;
    private final Calendar tempo;
    private final int qntVoltas;
    private Calendar tempoParaLider;
    private RankModel proximo;

    public RankModel(PilotoModel piloto, Calendar tempo, int qntVoltas) {
        this.piloto = piloto;
        this.tempo = tempo;
        this.qntVoltas = qntVoltas;
    }

    public void setProximo(RankModel proximo) {
        this.proximo = proximo;
    }
    
    public PilotoModel getPiloto() {
        return piloto;
    }

    public Calendar getTempo() {
        return tempo;
    }

    public int getQntVoltas() {
        return qntVoltas;
    }

    public RankModel getProximo() {
        return proximo;
    }

    public Calendar getTempoParaLider() {
        return tempoParaLider;
    }

    public void setTempoParaLider(Calendar tempoParaLider) {
        this.tempoParaLider = tempoParaLider;
    }
    
    
}