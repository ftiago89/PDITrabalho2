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
public class RunnableIDCT implements Runnable{
    private double[] cossenos;
    private int N;
    private double[] resultadoParte;
    private int n;
    private int limite;
    
    public RunnableIDCT(double[] dados, int n, int limite, int N){
        this.cossenos = dados;
        this.N = N;
        this.n = n;
        this.limite = limite;
    }

    @Override
    public void run() {
        resultadoParte = new DCT().IDct(this.cossenos, this.n, this.limite, this.N);
    }

    public double[] getResultadoParte() {
        return resultadoParte;
    }
    
}
