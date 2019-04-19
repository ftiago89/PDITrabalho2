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
public class RunnableDCT implements Runnable{
    private double[] dados;
    private int N;
    private double[] resultadoParte;
    private int k;
    private int limite;
    
    public RunnableDCT(double[] dados, int k, int limite, int N){
        this.dados = dados;
        this.N = N;
        this.k = k;
        this.limite = limite;
    }

    @Override
    public void run() {
        resultadoParte = new DCT().Dct(this.dados, this.k, this.limite, this.N);
    }

    public double[] getResultadoParte() {
        return resultadoParte;
    }
    
}
