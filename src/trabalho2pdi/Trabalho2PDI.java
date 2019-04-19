/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2pdi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class Trabalho2PDI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("saida.txt");
        double[] dados = new double[125204];
        int contador = 0;
        DCT dct = new DCT();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while (contador < 125204) {
                line = br.readLine();
                dados[contador] = Double.parseDouble(line);
                ++contador;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Trabalho2PDI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Trabalho2PDI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        double[] sinalSlides = {11.525440313875107, 5.928601339677928, 2.1516074520608317, 0.46931780058050926, -0.5441192338744232, 0.9595444035148329, 3.688168866104265, 4.10571030552285};
                
        RunDCT teste = new RunDCT(dados);
        double[] resultadoFinal = teste.getResultadoFinal();
        
        for(int i = 0; i<resultadoFinal.length; ++i) System.out.println(resultadoFinal[i]);
    } 
   
}
