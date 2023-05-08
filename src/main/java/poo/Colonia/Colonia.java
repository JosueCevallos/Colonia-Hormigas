package poo.Colonia;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import poo.Controladores.Pausar;
import poo.Hormigas.*;

/**
 *
 * @author josue
 */
public class Colonia {
    
    private Pausar p;
    private AlmacenComida almacen;
    private ZonaComer zonaComer;
    private ZonaDescanso zonaDescanso;
    private ZonaInstruccion zonaInstruccion;
    private Refugio refugio;
    private Hormiga h;
    private ListaHormigas hormigasBuscanComida, hormigasRepelenInsecto, hormigasAlmacen, hormigasLlevanComida, 
                    hormigasHacenInstruccion, hormigasDescansando, hormigasEnZonaComer, hormigasRefugio;
    
    private int comAlmacen=2, comZonaComer=4;
    
    //MECANISMOS DE SINCRONIZACIÓN PARA ENTRADAS Y SALIDAS
    Semaphore entrada = new Semaphore(1);
    Semaphore salida1 = new Semaphore(1);
    Semaphore salida2 = new Semaphore(1);
    
    public Colonia(JTextField tfBuscanComida, JTextArea tfRepelenInsecto, JTextField tfAlmacenComida, JTextField tfLlevandoComida,
                JTextField tfHacenInstruccion, JTextField tfDescansando, JTextField tfComAlmacen, JTextField tfComZonaComer,
                JTextArea tfComiendo, JTextArea tfRefugio, Pausar p){
        
        this.hormigasBuscanComida = new ListaHormigas(tfBuscanComida, p);
        this.hormigasRepelenInsecto = new ListaHormigas(tfRepelenInsecto, p);
        this.hormigasAlmacen = new ListaHormigas(tfAlmacenComida, p);
        this.hormigasLlevanComida = new ListaHormigas(tfLlevandoComida, p);
        this.hormigasHacenInstruccion = new ListaHormigas(tfHacenInstruccion, p);
        this.hormigasDescansando = new ListaHormigas(tfDescansando, p);
        
        //this.undComZonaComer = tfComZonaComer;
        this.hormigasEnZonaComer = new ListaHormigas(tfComiendo, p);
        this.hormigasRefugio = new ListaHormigas(tfRefugio, p);
        
        this.almacen = new AlmacenComida(tfComAlmacen);
        this.zonaComer = new ZonaComer(tfComZonaComer);
        this.zonaDescanso = new ZonaDescanso();
        this.zonaInstruccion = new ZonaInstruccion();
        this.refugio = new Refugio();
        this.p = p;
        }
    
    
    /*
    public void comprobarInterfaz(Hormiga h){
        
        hormigasBuscanComida.meter(h);
        hormigasRepelenInsecto.meter(h);
        hormigasAlmacen.meter(h); 
        hormigasLlevanComida.meter(h);
        hormigasHacenInstruccion.meter(h);
        hormigasDescansan.meter(h); 
        hormigasComiendo.meter(h);
        hormigasRefugio.meter(h);
        
        //undComZonaComer.setText(String.valueOf(comZonaComer));
    } */
    
    // ***************+ BLOQUE FUNCIONES ENTRADA Y SALIDA *******************
    public void entraColonia(Hormiga h){
        
        try {
            entrada.acquire();
            Thread.sleep(100);
            System.out.println(h.getMiId(h.getTipo())+" entra a la colonia");
            entrada.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saleColonia(Hormiga h, int salida){
        
        try {
            System.out.println(h.getMiId(h.getTipo())+" ha elegido la puerta "+salida);
            if(salida==1){
                salida1.acquire();
                Thread.sleep(100);
                System.out.println(h.getMiId(h.getTipo())+" sale por salida 1");
                salida1.release();
                
            }else{
                salida2.acquire();
                Thread.sleep(100);
                System.out.println(h.getMiId(h.getTipo())+" sale por salida 2");
                salida2.release();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //***************** FIN BLOQUE FUNCIONES ENTRADA Y SALIDA ********************
    
    /* **************** BLOQUE FUNCIONES DE OBRERAS *****************************
        *GUARDAR COMIDA
        *BUSCAR COMIDA
        *RECOGER COMIDA
        *DEPOSITA COMIDA EN ZONA COMER
    ****************************************************************************/
    public void buscaComida(Hormiga h){ //OBRERA IMPAR BUSCA COMIDA: SALE DE LA COLONIA,BUSCA Y ENTRA
        
        saleColonia(h, h.eligeSalida());
        try {
            hormigasBuscanComida.meter(h);
            System.out.println(h.getMiId(h.getTipo())+" recoge 5 alimentos");
            Thread.sleep(3900);
            hormigasBuscanComida.sacar(h);
            entraColonia(h);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void guardaComidaA(Hormiga h){
        almacen.acceder(h, hormigasAlmacen);
        almacen.sumaComida(5);
        System.out.println(h.getMiId(h.getTipo()) + " ha dejado 5 alimentos");
        Random r = new Random();
        espera(4000, 2000);
        almacen.salir(h, hormigasAlmacen);
    }
    
    public void recogeComidaAlmacen(Hormiga h){
        almacen.acceder(h, hormigasAlmacen);
        almacen.restaComida(5);
        //espera(2000, 1000);
        System.out.println(h.getMiId(h.getTipo()) + " se lleva 5 alimentos");
        almacen.salir(h, hormigasAlmacen);

    }
    
    public void trasladaComida(Hormiga h){
       
        hormigasLlevanComida.meter(h);
        System.out.println(h.getMiId(h.getTipo()) + "esta viajando a la zona para comer");
        espera(3000, 1000);
        hormigasLlevanComida.sacar(h);
    }
    
    public void depositaAlimentos(Hormiga h){
        zonaComer.acceder(h, hormigasEnZonaComer);
        zonaComer.sumaComida(5);
        espera(2000,1000);
        zonaComer.salir(h, hormigasEnZonaComer);
        
    }
    // ********************** FIN BLOQUE FUNCIONES OBRERAS *****************************
    
    // ********************** FUNCIONES DE DESCANSO Y DE COMER **********************
    
    public void come(Hormiga h){
        zonaComer.acceder(h, hormigasEnZonaComer);
        zonaComer.restaComida();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }
        zonaComer.salir(h, hormigasEnZonaComer);
    }
    
    public void descansa(Hormiga h){
        
        zonaDescanso.acceder(h,hormigasDescansando);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }
        zonaDescanso.salir(h, hormigasDescansando);
    }
    
    public void espera(int max, int min){
        
        try {
            Random r = new Random();
            Thread.sleep(r.nextInt(max+1)+min);
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }            
}
