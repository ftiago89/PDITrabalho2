/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2pdi;

import java.util.Arrays;

/**
 *
 * @author Felipe
 */
public class RunIDCT {
    private double[] cossenos1, cossenos2, cossenos3, cossenos4, resultadoFinal;
    private RunnableIDCT r1, r2, r3, r4;
    
    public RunIDCT(double[] cossenos){
        this.cossenos1 = cossenos;
        this.cossenos2 = Arrays.copyOf(cossenos1, cossenos1.length);
        this.cossenos3 = Arrays.copyOf(cossenos1, cossenos1.length);
        this.cossenos4 = Arrays.copyOf(cossenos1, cossenos1.length);
        run();
    }
    
    private void run(){
        int step = cossenos1.length/4;
        
        r1 = new RunnableIDCT(cossenos1, 0, step, cossenos1.length);
        r2 = new RunnableIDCT(cossenos2, step, 2*step, cossenos2.length);
        r3 = new RunnableIDCT(cossenos3, 2*step, 3*step, cossenos3.length);
        r4 = new RunnableIDCT(cossenos4, 3*step, 4*step, cossenos4.length);
        
        
        Thread t1 = new Thread(r1);
        t1.start();
        Thread t2 = new Thread(r2);
        t2.start();
        Thread t3 = new Thread(r3);
        t3.start();
        Thread t4 = new Thread(r4);
        t4.start();
        
        while (t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive()){
            ;
        }
        /*
        for(int i = 0; i<r2.getResultadoParte().length; ++i) System.out.print(r2.getResultadoParte()[i] + " ");
        for(int i = 0; i<r2.getResultadoParte().length; ++i) System.out.print(r2.getResultadoParte()[i] + " ");
        for(int i = 0; i<r3.getResultadoParte().length; ++i) System.out.print(r3.getResultadoParte()[i] + " ");
        for(int i = 0; i<r4.getResultadoParte().length; ++i) System.out.print(r4.getResultadoParte()[i] + " ");
        */
        
        resultadoFinal = combine(r1.getResultadoParte(), r2.getResultadoParte(),
                r3.getResultadoParte(), r4.getResultadoParte());
    }
    
    private double[] combine(double[] a, double[] b, double[] c, double[] d){
        int length = a.length + b.length + c.length + d.length;
        double[] result = new double[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        System.arraycopy(c, 0, result, 2*b.length, c.length);
        System.arraycopy(d, 0, result, 3*c.length, d.length);
        return result;
    }

    public double[] getResultadoFinal() {
        return resultadoFinal;
    }
}
