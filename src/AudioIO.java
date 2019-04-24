
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;




//classe para processar o audio
public class AudioIO {

    private String nomeArquivo;

    public AudioIO(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    //le o audio e extrai as informacoes para serem trabalhadas.
    public double[][] readAudio() {
        File file = new File(this.nomeArquivo);
        AudioInputStream ais = null;
        try {
            ais = AudioSystem.getAudioInputStream(file);
            int frameLength = (int) ais.getFrameLength();
            int frameSize = (int) ais.getFormat().getFrameSize();
            byte[] eightBitByteArray = new byte[frameLength * frameSize];

            int result = ais.read(eightBitByteArray);

            int channels = ais.getFormat().getChannels();
            double[][] samples = new double[channels][frameLength];

            int sampleIndex = 0;
            for (int t = 0; t < eightBitByteArray.length;) {
                for (int channel = 0; channel < channels; channel++) {
                    int low = (int) eightBitByteArray[t];
                    t++;
                    int high = (int) eightBitByteArray[t];
                    t++;
                    int sample = getSixteenBitSample(high, low);
                    samples[channel][sampleIndex] = (double) sample;
                }
                sampleIndex++;
            }
            return samples;

        } catch (Exception exp) {

            exp.printStackTrace();

        } finally {

            try {
                ais.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    // path of the wav file
    File wavFile = new File("ecordAudio.wav");
 
    // format of audio file
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
 
    // the line from which audio data is captured
    TargetDataLine line;
 
    
    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
        return format;
    }
    
    public void writeAudio(byte[] dataAudio) {
        InputStream b_in = new ByteArrayInputStream(dataAudio);

        
        try {
            
            //AudioFormat format = getAudioFormat();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(
                "filename.bin"));
        dos.write(dataAudio);
        AudioFormat format = new AudioFormat(8000f, 16, 1, true, false);
        AudioInputStream stream = new AudioInputStream(b_in, format,
                dataAudio.length);
        File file = new File("file.wav");
        AudioSystem.write(stream, Type.WAVE, file);
 
 /*
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
 
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing
 
            System.out.println("Start capturing...");

 
            InputStream is = new ByteArrayInputStream(dataAudio);
            AudioInputStream ais = new AudioInputStream(is, format, dataAudio.length);
 
            System.out.println("Start recording...");
 
            // start recording
            AudioSystem.write(ais, fileType, wavFile);
 
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
  */       
        }catch(Exception  e){}
        
        
        
        
        
        
/*
        InputStream b_in = new ByteArrayInputStream(dataAudio);
        File file = new File("C:\\file.wav");
        try {
            
            AudioSystem.write(b_in, Type.WAVE, "");
            
            /*
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(
                    "C:\\filename.bin"));
            dos.write(dataAudio);
            AudioFormat format = new AudioFormat(8000f, 16, 1, true, false);
            AudioInputStream stream = new AudioInputStream(b_in, format,
                    dataAudio.length);
            File file = new File("C:\\file.wav");
            AudioSystem.write(stream, Type.WAVE, file);
        }catch(Exception err){}
*/
        
    }
    
    

    protected int getSixteenBitSample(int high, int low) {
        return (high << 8) + (low & 0x00ff);
    }
}
