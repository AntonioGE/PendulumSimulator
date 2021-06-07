/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulumsimulator.rk;

/**
 *
 * @author ANTONIO
 */
public interface Function {
    
    public abstract double execute(double[] in, double[] params);
    
}
