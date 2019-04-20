/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2pdi;

import static java.lang.Math.cos;

/**
 *
 * @author Felipe
 */
public class DCT {
    public double[] Dct(double[] x, int k, int limite, int N){
        double[] X = new double[limite-k];
        double soma;
        double ck0 = Math.pow(1.0/2.0, 1.0/2.0);
        double ck;
        double piPorN = (Math.PI)/N;
        double piPor2N = (Math.PI)/(2.0*N);
        double raizDeDoisN = Math.pow(2.0/N, 1.0/2.0);
        double piK2;
        double fase;
        int kaux = 0;
        
        for (; k < limite; ++k){
            piK2 = piPorN*k;
            fase = piPor2N*k;
            if (k>0) ck = 1;
            else ck = ck0;
            
            soma = 0.0;
            for (int n = 0;n < N; ++n){
                soma += x[n] * cos(((piK2*n) + (fase)));
            }
            
            X[kaux] = raizDeDoisN * ck * soma;
            ++kaux;
        }
        return X;
    }
    
    public double[] IDct(double[] X, int n, int limite, int N){
        double[] x = new double[limite-n];
        double ck;
        double ck0 = Math.pow(1.0/2.0, 1.0/2.0);
        double soma;
        double piPorN = (Math.PI)/N;
        double piPor2N = (Math.PI/(2.0*N));
        double raizDeDoisN = Math.pow(2.0/N, 1.0/2.0);
        double pin2;
        int naux = 0;
        
        for (;n<limite;++n){
            pin2 = piPorN*n;
            soma = 0.0;
            for(int k = 0; k<N; ++k){
                if(k>0) ck = 1;
                else ck = ck0;
                soma += ck * X[k]*cos(((pin2*k) + (piPor2N*k)));
            }
            x[naux] = raizDeDoisN * soma;
            ++naux;
        }
        return x;
    }
}
