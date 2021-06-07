/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulumsimulator.rk;

import java.util.Arrays;

/**
 *
 * @author ANTONIO
 */
public class RungeKutta4 {

    private double h;
    private double[] ins;
    private double[] ins_k;
    private double[] params;
    private Function[] funcs;
    private double[][] ks;

    public RungeKutta4(double h, double[] in0, double[] params, Function[] funcs) {
        this.h = h;
        this.ins = in0;
        this.ins_k = Arrays.copyOf(in0, in0.length);
        this.params = params;
        this.funcs = funcs;
        this.ks = new double[4][funcs.length];
    }

    public RungeKutta4(double h, Integrable integrable){
        this(h, integrable.getIns(), integrable.getParams(), integrable.getFuncs());
    }
    
    private static void updateKs(double h, double[] ins, double[] params, Function[] funcs, double[] outKs) {
        for (int i = 0; i < outKs.length; i++) {
            outKs[i] = h * funcs[i].execute(ins, params);
        }
    }

    private static void updateIns_k(double h, double[] ins, double[] ks, double coeff, double[] insOut) {
        insOut[0] = ins[0] + coeff * h;
        for (int i = 0; i < ks.length; i++) {
            insOut[i + 1] = ins[i + 1] + coeff * ks[i];
        }
    }

    private static void updateIns(double h, double[] ins, double[][] ks, double[] insOut) {
        insOut[0] = ins[0] + h;
        for (int i = 0; i < ins.length - 1; i++) {
            insOut[i + 1] = ins[i + 1] + (ks[0][i] + 2.0 * ks[1][i] + 2.0 * ks[2][i] + ks[3][i]) / 6.0;
        }
    }

    public void execute() {
        updateKs(h, ins_k, params, funcs, ks[0]);

        updateIns_k(h, ins, ks[0], 0.5, ins_k);
        updateKs(h, ins_k, params, funcs, ks[1]);

        updateIns_k(h, ins, ks[1], 0.5, ins_k);
        updateKs(h, ins_k, params, funcs, ks[2]);

        updateIns_k(h, ins, ks[2], 1.0, ins_k);
        updateKs(h, ins_k, params, funcs, ks[3]);

        updateIns(h, ins, ks, ins);
        
        printIns();
    }
    
    public void printIns(){
        for(double in : ins){
            System.out.print(in + " ");
        }
        System.out.println();
    }

    private static void execute(double h, double[] params, double[] in, double[] out, Function[] funcs, double[] ks) {
        for (int i = 0; i < ks.length; i++) {
            ks[i] = h * funcs[i].execute(in, params);
        }

    }

}
