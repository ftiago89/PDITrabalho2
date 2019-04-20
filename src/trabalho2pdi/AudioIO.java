/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2pdi;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author Felipe
 */
public class AudioIO {
    private String nomeArquivo;
    
    public AudioIO(String nomeArquivo){
        this.nomeArquivo = nomeArquivo;
    }

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
                    samples[channel][sampleIndex] = (double)sample;
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

    protected int getSixteenBitSample(int high, int low) {
        return (high << 8) + (low & 0x00ff);
    }
}
  

