package trabalho2pdi;

import DCT.RunDCT;
import DCT.RunIDCT;
import Questoes.Questoes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.opencv.core.Mat;

public class Principal {

    //metodo principal onde as questoes sao respondidas
    public static void main(String[] args) {
        
        RunDCT runDct;
        RunIDCT runIDct;
        Filtros filtro = new Filtros();
        ImageIO imageIO = new ImageIO();
        
        new Questoes().questao2(10);
        
        /*
        Mat imagem = imageIO.getImage("lena.bmp");
        Mat imagemTransformada;
        
        imageIO.showImage("lena", imagem);

        double[][] data = imageIO.getData(imagem, 0);
        
        //Dominio da frequencia
        data = filtro.FrequenciaImg(data);
        imagemTransformada = imageIO.returnData(data, imagem);
        imageIO.showImage("Dominio da Frequencia", imagemTransformada);
        
        //Dominio da frequencia
        data = filtro.espacoImg(data);
        imagemTransformada = imageIO.returnData(data, imagem);
        imageIO.showImage("Dominio do Espaço", imagemTransformada);
        */
        
        
        
        
/*
        
        double[][] imgTeste = new double[8][8];

        imgTeste[0][0] = 10;        imgTeste[0][1] = 5;        imgTeste[0][2] = 2;        imgTeste[0][3] = 8.5;    imgTeste[0][4] = 1;        imgTeste[0][5] = 1.5;        imgTeste[0][6] = 0;    imgTeste[0][7] = 0.1;
        imgTeste[1][0] = 10;        imgTeste[1][1] = 5;        imgTeste[1][2] = 2;        imgTeste[1][3] = 8.5;    imgTeste[1][4] = 1;        imgTeste[1][5] = 1.5;        imgTeste[1][6] = 0;    imgTeste[1][7] = 0.1;
        imgTeste[2][0] = 10;        imgTeste[2][1] = 5;        imgTeste[2][2] = 2;        imgTeste[2][3] = 8.5;    imgTeste[2][4] = 1;        imgTeste[2][5] = 1.5;        imgTeste[2][6] = 0;    imgTeste[2][7] = 0.1;
        imgTeste[3][0] = 10;        imgTeste[3][1] = 5;        imgTeste[3][2] = 2;        imgTeste[3][3] = 8.5;    imgTeste[3][4] = 1;        imgTeste[3][5] = 1.5;        imgTeste[3][6] = 0;    imgTeste[3][7] = 0.1;
        
        imgTeste = filtro.FrequenciaImg(imgTeste);
        imgTeste = filtro.espacoImg(imgTeste);
        
        exibeMatriz(imgTeste);
        
        double[][] img2 = new double[8][8];

        img2[0][0] = 0;        img2[0][1] = 0;        img2[0][2] = 0;        img2[0][3] = 0;    img2[0][4] = 0;        img2[0][5] = 0;        img2[0][6] = 0;        img2[0][7] = 0;
        img2[1][0] = 0;        img2[1][1] = 1;        img2[1][2] = 0;        img2[1][3] = 1;    img2[1][4] = 0;        img2[1][5] = 1;        img2[1][6] = 0;        img2[1][7] = 1;
        img2[2][0] = 1;        img2[2][1] = 0;        img2[2][2] = 1;        img2[2][3] = 0;    img2[2][4] = 1;        img2[2][5] = 0;        img2[2][6] = 1;        img2[2][7] = 0;
        img2[3][0] = 0;        img2[3][1] = 1;        img2[3][2] = 0;        img2[3][3] = 1;    img2[3][4] = 0;        img2[3][5] = 1;        img2[3][6] = 0;        img2[3][7] = 1;

        
        System.out.println("\n\n**Original**\n\n");
        //exibeMatriz(img);
        
        System.out.println("\n\n**Indo para D(freq)**\n\n");
        img = filtro.FrequenciaImg(img);
        //exibeMatriz(img);
        
        System.out.println("\n\n**Indo para D(esp)**\n\n");
        img = filtro.espacoImg(img);
        //exibeMatriz(img);
         */
 /*
        img2[0] = new RunIDCT(img[0]).getResultadoFinal();
        exibeMatriz(img2);
        
        img2[0] = new RunDCT(img2[0]).getResultadoFinal();
        exibeMatriz(img2);
         */
 /*
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
         */
    }

    public static void exibe(double[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            System.err.print(vetor[i] + "\t");
        }
    }

    public static void exibeMatriz(double vetor[][]) {
        for (int i = 0; i < vetor.length; i++) {
            for (int j = 0; j < vetor[0].length; j++) {
                System.out.print(vetor[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
