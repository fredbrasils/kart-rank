package kart.rank.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import kart.rank.service.LerLogCorridaService;

/**
 *
 * @author Fred Brasil
 */
public class Utils {

    /**
     * Retorna o tempo formatado
     *
     * @param tempo
     * @return String.
     */
    public static String tempoFormatado(Calendar tempo) {
        String minuto = tempo.get(Calendar.MINUTE) < 10 ? "0" + tempo.get(Calendar.MINUTE) : ""+tempo.get(Calendar.MINUTE); 
        String segundos = tempo.get(Calendar.SECOND) < 10 ? "0" + tempo.get(Calendar.SECOND) : ""+tempo.get(Calendar.SECOND); 

        return  minuto+ ":" + segundos + "." + tempo.get(Calendar.MILLISECOND);
    }
    
    
    /**
     * Escreveo conteudo no arquivo
     * @param arquivo
     * @param conteudo
     */
    public static void escreverArquivo(String conteudo, File arquivo){
        
        try{
    	    BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));
    	    bw.write(conteudo);
    	    bw.close();
    	}catch(IOException e){
    	    e.printStackTrace();
    	}
    }
    
    /**
     * Ler arquivo e retorna o conteudo
     *
     * @param file
     * @return Retorna conteudo do arquivo lido
     */
    public static StringBuilder lerArquivo(File file){
    
        FileReader fileReader;
        
        try {
            fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuilder  arquivo = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                arquivo.append(line);
            }
            fileReader.close();
            reader.close();
            
            return arquivo;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LerLogCorridaService.class.getName()).log(Level.SEVERE, "Arquivo não encontrado.", ex);
        } catch (IOException ex) {
            Logger.getLogger(LerLogCorridaService.class.getName()).log(Level.SEVERE, "Erro", ex);
        }
        
        return null;
    }
    
}
