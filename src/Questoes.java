
import filters.CortaCossenos;
import filters.Filtros;
import org.opencv.core.Mat;


public class Questoes {

    private Filtros filtro;
    private ImageIO imageIO;
    private CortaCossenos cortaCos;

    public Questoes(){}
    
    /*1. Dado um sinal de aúdio x com N amostras,
    desenvolva uma aplicação para encontrar uma
    aproximação de x obtida preservando-se os n
    coeficientes DCT mais importantes de x e
    zerando-se os demais. O parâmetro n é um
    inteiro no intervalo [1, N]. O sinal
    resultante deve ser salvo em arquivo
    com o mesmo tipo de x.*/

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
}
