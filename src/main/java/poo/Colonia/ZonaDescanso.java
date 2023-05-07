package poo.Colonia;

import javax.swing.JTextField;
import poo.Hormigas.Hormiga;
import poo.Hormigas.ListaHormigas;

/**
 *
 * @author josue
 */
public class ZonaDescanso {
    
    //private JTextField hormigasDescansando;
    
    public void acceder(Hormiga h, ListaHormigas hormigasDescansando){
        
        hormigasDescansando.meter(h);
        System.out.println(h.getMiId(h.getTipo())+" entran a la zona de descanso");
    }
    
}
