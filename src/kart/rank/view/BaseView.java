
package kart.rank.view;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import kart.rank.util.Utils;

/**
 *
 * @author Fred Brasil
 */

public abstract class BaseView {

    /**
     * Gera a página HTML com os dados
     * @param mapTarget
     * @return File.
     */
    public final File geraPaginaExibicao(Map<String,String> mapTarget) {
        
        File temp = null;
        
        try {
            temp = File.createTempFile("tempfile", ".html");
            String html = preencherDados(Utils.lerArquivo(new File(getTemplate())), mapTarget);
            Utils.escreverArquivo(html, temp);
            
        } catch (IOException ex) {
            Logger.getLogger(CorridaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temp;
    }
    
    /**
     * Pega o template HTML e substitui os target pelos valores
     * @param conteudo
     * @param mapTarget
     * @return file.
     */
    protected String preencherDados(StringBuilder conteudo,  Map<String,String> mapTarget) {
        
        for(String key : mapTarget.keySet()){
            String s = conteudo.toString().replace(key, mapTarget.get(key));
            conteudo = new StringBuilder(s);
        }
        
        return conteudo.toString();
    }
    
    /**
     * Retorna o template HTML referente a página
     * @return Caminho do template.
     */
    protected abstract String getTemplate();
}

