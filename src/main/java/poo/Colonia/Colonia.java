package poo.Colonia;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import poo.Hormigas.*;

/**
 *
 * @author josue
 */
public class Colonia {
    
    private Hormiga h;
    private ListaHormigas listaBuscanComida, listaRepelenInsecto, listaAlamacenComida, listaLlevanComida, 
                    listaHacenInstruccion, listaDescansan, listaComiendo, listaRefugio;
    private JTextField undComA, undComZonaComer;
    private int comAlmacen=2, comZonaComer=4;
    
    public Colonia(JTextField tfBuscanComida, JTextArea tfRepelenInsecto, JTextField tfAlmacenComida, JTextField tfLlevandoComida,
                JTextField tfHacenInstruccion, JTextField tfDescansando, JTextField tfComAlmacen, JTextField tfComZonaComer,
                JTextArea tfComiendo, JTextArea tfRefugio){
        
        this.listaBuscanComida = new ListaHormigas(tfBuscanComida);
        this.listaRepelenInsecto = new ListaHormigas(tfRepelenInsecto);
        this.listaAlamacenComida = new ListaHormigas(tfAlmacenComida);
        this.listaLlevanComida = new ListaHormigas(tfLlevandoComida);
        this.listaHacenInstruccion = new ListaHormigas(tfHacenInstruccion);
        this.listaDescansan = new ListaHormigas(tfDescansando);
        this.undComA = tfComAlmacen;
        this.undComZonaComer = tfComZonaComer;
        this.listaComiendo = new ListaHormigas(tfComiendo);
        this.listaRefugio = new ListaHormigas(tfRefugio);
        }
    
    
    
    public void comprobarInterfaz(Hormiga h){
        
        listaBuscanComida.meter(h);
        listaRepelenInsecto.meter(h);
        listaAlamacenComida.meter(h); 
        listaLlevanComida.meter(h);
        listaHacenInstruccion.meter(h);
        listaDescansan.meter(h); 
        listaComiendo.meter(h);
        listaRefugio.meter(h);
        undComA.setText(String.valueOf(comAlmacen));
        undComZonaComer.setText(String.valueOf(comZonaComer));
    }
}
