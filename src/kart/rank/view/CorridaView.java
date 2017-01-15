
package kart.rank.view;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import kart.rank.model.CorridaModel;
import kart.rank.model.RankModel;
import kart.rank.service.CorridaServiceImpl;
import kart.rank.service.LerLogCorridaService;
import kart.rank.service.PilotoServiceImpl;
import kart.rank.util.Utils;

/**
 *
 * @author Fred Brasil
 */
public class CorridaView extends BaseView{
    
    private static final String LOG_FILE = "./RaceLog.txt";
    final String TEMPLATE_HTML = "./resultado-corrida.html";

    /**
     * Metodo principal: 
     *  Gera os resultados, monta a estrutura dos dados e depois 
     * monta o arquivo HTML
     * @return File.
     */
    public File montarResultadoCorrida(){
        
    	CorridaModel corrida = obterResultadoDaCorrida();
        Map<String,String> mapTarget = obterDadosFormatado(corrida);
        
        return geraPaginaExibicao(mapTarget);
    }

    /**
     * Monta os dados e retorna o map com os valores respectivos aos target do template
     * @param corrida
     * @return HashMap.
     */
    private HashMap<String,String> obterDadosFormatado(CorridaModel corrida) {
        
        HashMap<String,String> mapTarget = new HashMap<>();
        mapTarget.put("#{rank}", obterRank(corrida));
        mapTarget.put("#{campeao}", corrida.getRank().getPiloto().getNome());
        
        String melhorVolta = Utils.tempoFormatado(corrida.getMelhorVolta().getTempo()) + " - " 
                                                + corrida.getMelhorVolta().getPiloto().getNome();
        mapTarget.put("#{melhorVolta}", melhorVolta); 

        return mapTarget;
    }
    
    /**
     * Monta o resultado do rank para enviar para o template html
     * @param corrida
     * @return file.
     */
    private String obterRank(CorridaModel corrida){
                                   
        RankModel rank = corrida.getRank();
        
        StringBuilder rankTable = new StringBuilder();
        int colocacao = 1;
        
        while(rank != null){
            rankTable.append("<tr>");
            rankTable.append("<td>").append(colocacao).append("</td>");
            rankTable.append("<td>").append(rank.getPiloto().getId()).append("</td>");
            rankTable.append("<td>").append(rank.getPiloto().getNome()).append("</td>");
            rankTable.append("<td>").append(rank.getQntVoltas()).append("</td>");
            rankTable.append("<td>").append(Utils.tempoFormatado(rank.getTempo())).append("</td>");
            
            if(rank.getTempoParaLider() != null){
                rankTable.append("<td>").append("+").append(Utils.tempoFormatado(rank.getTempoParaLider())).append("</td>");
            }else{
                int qntVoltas = corrida.getRank().getQntVoltas() - rank.getQntVoltas();
                if( qntVoltas == 0){
                    rankTable.append("<td>").append(" -- ").append("</td>");
                }else{
                    rankTable.append("<td>").append("+").append(qntVoltas).append(" volta(s) ").append("</td>");
                }
            }
            
            rankTable.append("<td>").append(Utils.tempoFormatado(corrida.getMelhoresVoltasPorPiloto().get(rank.getPiloto()).getTempo())).append("</td>");
            rankTable.append("<td>").append(corrida.getMediaVelocidadePorPiloto().get(rank.getPiloto()).toString()).append("</td>");
            rankTable.append("</tr>");
            
            colocacao++;
            rank = rank.getProximo();
        }
      
        return rankTable.toString();
    }
    
    /**
     * Monta os dados da corrida
     *
     * @return CorridaModel.
     */
    private CorridaModel obterResultadoDaCorrida(){
        
        LerLogCorridaService rrl = new LerLogCorridaService();
        CorridaModel corrida = rrl.lerLogDaCorrida(LOG_FILE);
        
        CorridaServiceImpl corridaService = new CorridaServiceImpl();
        corridaService.rank(corrida);
        corridaService.diferencaTempoVencedor(corrida.getRank());
        corridaService.melhorVolta(corrida);
    
        PilotoServiceImpl pilotoService = new PilotoServiceImpl();
        pilotoService.mediaVelocidadePorPiloto(corrida);
        
        return corrida;
    }

    @Override
    protected String getTemplate(){
        return TEMPLATE_HTML;
    }
}
