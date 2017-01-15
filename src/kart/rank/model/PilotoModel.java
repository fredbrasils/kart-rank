package kart.rank.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Fred Brasil
 */
public class PilotoModel {
    
    private Long id;
    private String nome;
    private List<VoltaModel> voltas;

    public PilotoModel(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public List<VoltaModel> getVoltas() {
        return voltas;
    }

    public void addVolta(VoltaModel lap) {
        
        if(voltas == null){
            voltas = new ArrayList<VoltaModel>();
            voltas.add(lap);
        }else{
            
            boolean achou = false;
            
            for(int index = 0; index < voltas.size();index++){
                if(voltas.get(index).getNumero().compareTo(lap.getNumero()) > 0){
                    voltas.add(index, lap);
                    achou = true;
                    break;
                }
            }
            
            if(!achou){
                voltas.add(lap);
            }
        }
    }
    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setVoltas(List<VoltaModel> voltas) {
        this.voltas = voltas;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PilotoModel other = (PilotoModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Piloto{" + "id=" + id + ", nome=" + nome + "}";
    }
    
}
