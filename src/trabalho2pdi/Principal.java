
package trabalho2pdi;

import java.util.Scanner;


public class Principal {

    //metodo principal onde as questoes sao respondidas
    public static void main(String[] args) {
        RunDCT runDct;
        RunIDCT runIDct;
        DeslocaFrequencia deslocaFrequencia;
        CortaCossenos cortaCossenos;
        double[] resultadoTemp = null;
        double[] sinalSlides = {11.525440313875107, 5.928601339677928, 2.1516074520608317, 0.46931780058050926, -0.5441192338744232, 0.9595444035148329, 3.688168866104265, 4.10571030552285};
        Grafico grafico = new Grafico();
        
        AudioIO teste = new AudioIO("audios/audio.wav");
        double[][] samples = teste.readAudio();
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Qual questão? ");
        
        
        while(true){
            int questao = Integer.valueOf(scan.next());

            //TRATAMENTO DAS QUESTOES
            switch (questao) {
                //questao 1
                case 1:
                    System.out.print("Valor de n para corte dos cossenos: ");
                    int n = Integer.valueOf(scan.next());
                    runDct = new RunDCT(samples[0]);//passa pro dominio da frequencia
                    if (resultadoTemp == null) {//se a questao 1 ja tiver sido feita, nao precisa passar de novo pra frequencia o sinal original
                        runDct = new RunDCT(samples[0]);
                        resultadoTemp = runDct.getResultadoFinal();
                    }
                    cortaCossenos = new CortaCossenos(resultadoTemp, n);//aplica um corte dos n cossenos mais importantes e zera os demais
                    grafico.plotar(cortaCossenos.getSaida(), n + "cossenosmaisimportantes.png", "X[k] com " + n + " Cossenos mais Importantes", "k");//plota o grafico de X[k]
                    runIDct = new RunIDCT(cortaCossenos.getSaida());//dct inversa pra voltar da frequencia pro espaco
                    double[] sinalVolta = runIDct.getResultadoFinal();//pega o sinal modificado
                    grafico.plotar(sinalVolta, "voltacom" + n + "cossenosmaisimportantes.png", "x[n] com " + n + " Cossenos mais Importantes", "n");//plota o grafico de x[n]
                    break;
                //questao 2
                case 2:
                    //fazer
                    break;
                //questao 3
                case 3:
                    System.out.print("Valor de c para deslocamento: ");
                    int c = Integer.valueOf(scan.next());
                    if (resultadoTemp == null) {//se a questao 1 ja tiver sido feita, nao precisa passar de novo pra frequencia o sinal original
                        runDct = new RunDCT(samples[0]);
                        resultadoTemp = runDct.getResultadoFinal();
                    }
                    deslocaFrequencia = new DeslocaFrequencia(resultadoTemp, c);//aplica um deslocamento no array das frequencias
                    grafico.plotar(resultadoTemp, c + "cossenosdeslocados.png", "X[k] = X[k+" + c + "]", "k");//plota o grafico de X[k] deslocado
                    runIDct = new RunIDCT(resultadoTemp);//dct inversa para voltar da frequencia para o espaco os X[k] deslocado
                    double[] sinalDeslocado = runIDct.getResultadoFinal();//pega o array resultado da dct inversa
                    grafico.plotar(sinalDeslocado, "sinaldeslocadoem" + c + ".png", "x[n] após deslocamento " + c, "n");//plota grafico de x[n] deslocado
                    break;
            }
        }
    }
}
