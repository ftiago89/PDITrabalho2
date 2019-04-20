/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2pdi;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class CortaCossenos {
    private int n;
    private double[] X;
    private double[] saida;

    public CortaCossenos(double[] X, int n) {
        this.n = n;
        this.X = X;
        this.saida = new double[this.X.length];
        run();
    }
    
    private void run(){
        List<Element> elements = new ArrayList<Element>();
        for (int i = 0; i<X.length; ++i){
            elements.add(new Element(i, X[i]));
        }
        
        Collections.sort(elements);
        
        for(;this.n<elements.size(); ++this.n){
            elements.get(n).value = 0.0;
        }
        
        //for(Element e: elements) System.out.println(e.index);
        
        for(Element e: elements){
            saida[e.index] = e.value;
        }
        
        //for(int i=0;i<saida.length;++i) System.out.println(saida[i]);
    }

    public double[] getSaida() {
        return saida;
    }
    
}

class Element implements Comparable<Element> {

    int index;
    double value;

    Element(int index, double value){
        this.index = index;
        this.value = value;
    }

    @Override
    public int compareTo(Element e) {
        if (abs(this.value) > abs(e.value)) return -1;
        else if (abs(e.value) > abs(this.value)) return 1;
        return 0;
    }
}
