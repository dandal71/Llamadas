package ar.com.dsv;

/**
 * Clase que representa una llamada. Como pueden darse en forma concurrente la hacemos
 * extender de Callable
 */ 

/**
 * @author Daniel E. Dalmagro
 *
 */
public class Llamada implements Runnable {

	private int id;
	private int duracionMinima = 5;
	private int duracionMaxima = 10;
	private Empleado empleado;
	
	public Llamada() {
		super();
	}
	
	public Llamada(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

		
	
	@Override
	public void run() {
		int duracion = (int)(Math.random()*(duracionMaxima-duracionMinima+1)+duracionMinima);
		try {
			System.out.println("Inicioa llamada: " + this.id + ". Duración: " + duracion + ". Atendida por: " + this.empleado.getNombre());
			Thread.sleep(duracion*1000);
			System.out.println("Fin llamada: " + this.id + " Cuelga: " + this.empleado.getNombre());
		} catch (InterruptedException  ex) {
			//Si se interrumpe la llamada no se hace nada
			ex.printStackTrace();
		} finally {
			this.empleado.setOcupado(false);	
		}				
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	

}
