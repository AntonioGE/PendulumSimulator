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
public interface Integrable {
    
    public double[] getIns();
    public double[] getParams();
    public Function[] getFuncs();
}
