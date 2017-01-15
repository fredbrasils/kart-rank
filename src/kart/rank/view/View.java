/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kart.rank.view;

import java.io.File;
import java.util.Map;

/**
 *
 * @author Fred Brasil
 */
public interface View {
    
    File geraPaginaExibicao(StringBuilder conteudo, Map<String,String> mapTarget);
    
}
