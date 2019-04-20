/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2pdi;

/**
 *
 * @author Felipe
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RunDCT runDct;
        RunIDCT runIDct;
        double[] cossenosPosCorte;
        double[] sinalSlides = {11.525440313875107, 5.928601339677928, 2.1516074520608317, 0.46931780058050926, -0.5441192338744232, 0.9595444035148329, 3.688168866104265, 4.10571030552285};
        
        runDct = new RunDCT(sinalSlides);
        double[] cossenos = runDct.getResultadoFinal();
        CortaCossenos teste = new CortaCossenos(cossenos, 4);
        cossenosPosCorte = teste.getSaida();
        runIDct = new RunIDCT(cossenosPosCorte);
        double[] sinalProcessado = runIDct.getResultadoFinal();
        
        for(int i=0;i<sinalProcessado.length; ++i) System.out.println(sinalProcessado[i]);
        /*AudioIO teste = new AudioIO("audio.wav");
        double[][] samples = teste.readAudio();
        //for(int i=0; i<samples[0].length;++i){
        //    System.out.println(samples[0][i]);
        //}
        
        runDct = new RunDCT(samples[0]);
        double[] cossenos = runDct.getResultadoFinal();
        
        System.out.println("Cossenos:");
        for(int i = 0; i < cossenos.length; ++i){
            System.out.println(cossenos[i]);
        }
        
        runIDct = new RunIDCT(cossenos);
        double[] sinalVolta = runIDct.getResultadoFinal();
        
        System.out.println("Sinal de volta:");
        for(int i = 0; i < sinalVolta.length; ++i){
            System.out.println(sinalVolta[i]);
        }*/
        
        
    } 
   
}
