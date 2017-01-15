
package kart.rank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fred Brasil
 */
public class CorridaModel {
    
    private List<PilotoModel> pilotos;
    private RankModel rank;
    private VoltaModel melhorVolta;
    private Map<PilotoModel,VoltaModel> melhoresVoltasPorPiloto; 
    private Map<PilotoModel,BigDecimal> mediaVelocidadePorPiloto; 

    public CorridaModel() {
        this.pilotos = new ArrayList<>();
    }
    
    public List<PilotoModel> getPilotos() {
        return pilotos;
    }

    public void setPilotos(List<PilotoModel> pilotos) {
        this.pilotos = pilotos;
    }

    public RankModel getRank() {
        return rank;
    }

    public void setRank(RankModel rank) {
        this.rank = rank;
    }

    public VoltaModel getMelhorVolta() {
        return melhorVolta;
    }

    public void setMelhorVolta(VoltaModel melhorVolta) {
        this.melhorVolta = melhorVolta;
    }

    public Map<PilotoModel, VoltaModel> getMelhoresVoltasPorPiloto() {
        return melhoresVoltasPorPiloto;
    }

    public void setMelhoresVoltasPorPiloto(Map<PilotoModel, VoltaModel> melhoresVoltasPorPiloto) {
        this.melhoresVoltasPorPiloto = melhoresVoltasPorPiloto;
    }

    public Map<PilotoModel, BigDecimal> getMediaVelocidadePorPiloto() {
        return mediaVelocidadePorPiloto;
    }

    public void setMediaVelocidadePorPiloto(Map<PilotoModel, BigDecimal> mediaVelocidadePorPiloto) {
        this.mediaVelocidadePorPiloto = mediaVelocidadePorPiloto;
    }
    
}
