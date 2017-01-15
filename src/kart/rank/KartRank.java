package kart.rank;

import java.io.File;
import java.io.IOException;
import kart.rank.view.CorridaView;

/**
 *
 * @author Fred Brasil
 */
public class KartRank {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        CorridaView view = new CorridaView();
        File htmlFile = view.montarResultadoCorrida();
        Runtime rt = Runtime.getRuntime();
        rt.exec( "rundll32 url.dll,FileProtocolHandler " + htmlFile.toURI());
    }
    
}
