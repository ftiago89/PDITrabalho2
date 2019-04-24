
import filters.CortaCossenos;
import filters.DeslocaFrequencia;
import filters.Filtros;
import filters.RunDCT;
import filters.RunIDCT;
import java.util.Scanner;
import org.opencv.core.Mat;


public class Questoes {

    private Filtros filtro;
    private ImageIO imageIO;
    private CortaCossenos cortaCos;
    private DeslocaFrequencia deslocaFrequencia;
    private AudioIO audio;
    private double[][] samples;

    public Questoes(){
        this.audio = new AudioIO("src/samplesAudio/audio.wav");
        this.samples = audio.readAudio();
    }
    
    /*1. Dado um sinal de aúdio x com N amostras,
    desenvolva uma aplicação para encontrar uma
    aproximação de x obtida preservando-se os n
    coeficientes DCT mais importantes de x e
    zerando-se os demais. O parâmetro n é um
    inteiro no intervalo [1, N]. O sinal
    resultante deve ser salvo em arquivo
    com o mesmo tipo de x.*/
    
    public void questao1(int n) {
        
        RunDCT runDct = new RunDCT(this.samples[0]);//passa pro dominio da frequencia

        double[] resultadoTemp = runDct.getResultadoFinal();

        this.cortaCos = new CortaCossenos(resultadoTemp, n);//aplica um corte dos n cossenos mais importantes e zera os demais
        new Grafico().plotar(this.cortaCos.getSaida(), n + "cossenosmaisimportantes.png", "X[k] com " + n + " Cossenos mais Importantes", "k");//plota o grafico de X[k]
        RunIDCT runIDct = new RunIDCT(this.cortaCos.getSaida());//dct inversa pra voltar da frequencia pro espaco
        double[] sinalVolta = runIDct.getResultadoFinal();//pega o sinal modificado
        new Grafico().plotar(sinalVolta, "voltacom" + n + "cossenosmaisimportantes.png", "x[n] com " + n + " Cossenos mais Importantes", "n");//plota o grafico de x[n]
        audio.writeAudio(sinalVolta);
    }

 /*
    2. Dado uma imagem I de dimensões RxC,
    desenvolva uma aplicação para calcular e
    exibir uma aproximação de I obtida preservando-se
    os n coeficientes DCT mais importantes des e
    zerando-se os demais. O parâmetro n é um
    inteiro no intervalo [1, RxC].
     */
    public void questao2(int n) {
        
        String path = "src/samplesImage/";
        
        //operacoes com imagem (obter, visualizar, salvar)
        this.imageIO = new ImageIO();
        //envia os dados para frequencia / espaco (imagem ou audio)
        this.filtro = new Filtros();
        
        //objeto imagem
        Mat imagem = imageIO.getImage(path + "lena.bmp");
        Mat imagemTransformada;
        
        //exibe imagem original
        //imageIO.showImage("lena", imagem);

        //captura os pixels da imagem (banda 0)
        double[][] data = imageIO.getData(imagem, 0);
        
        //Converte os pixel para o Dominio da frequencia
        data = filtro.FrequenciaImg(data);
        //Converte a matriz de pixel para uma imagem
        //imagemTransformada = imageIO.returnData(data, imagem);
        //imageIO.showImage("Dominio da Frequencia", imagemTransformada);
        
        
        for (int i = 0; i < data.length; i++){
            data[i] = new CortaCossenos(data[i], n).getSaida();
        }
        
                
        //Converte os pixel para o Dominio do espaço
        data = filtro.espacoImg(data);
        imagemTransformada = imageIO.returnData(data, imagem);
        imageIO.showImage("Dominio do Espaço com " + n + " cos() mais importantes", imagemTransformada);
        
    }

    /*
    3. Dado um sinal de aúdio x com N amostras,
    desenvolva um deslocador de frequências
    para x (Xd[k] = X[k+c], c inteiro).
    O sinal resultante deve ser salvo em
    arquivo com o mesmo tipo de x.
     */
    
    public void questao3(int c){

        RunDCT runDct = new RunDCT(this.samples[0]);
        double[] resultadoTemp = runDct.getResultadoFinal();

        this.deslocaFrequencia = new DeslocaFrequencia(resultadoTemp, c);//aplica um deslocamento no array das frequencias
        new Grafico().plotar(resultadoTemp, c + "cossenosdeslocados.png", "X[k] = X[k+" + c + "]", "k");//plota o grafico de X[k] deslocado
        RunIDCT runIDct = new RunIDCT(resultadoTemp);//dct inversa para voltar da frequencia para o espaco os X[k] deslocado
        double[] sinalDeslocado = runIDct.getResultadoFinal();//pega o array resultado da dct inversa
        new Grafico().plotar(sinalDeslocado, "sinaldeslocadoem" + c + ".png", "x[n] após deslocamento " + c, "n");//plota grafico de x[n] deslocado
    }
}
