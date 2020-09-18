package ar.com.dsv;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 */

/**
 * @author Daniel E. Dalmagro
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

/*		Llamada llamada1 = new Llamada();
		Llamada llamada2 = new Llamada();

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<Integer> resultadoLlamada1 = executor.submit(llamada1);
		Future<Integer> resultadoLlamada2 = executor.submit(llamada2);

		while (true) {
			try {
				if (!resultadoLlamada1.isDone()) {
					System.out.println("Esperando fin llamda 1");
				} 

				if (!resultadoLlamada2.isDone()) {					
					System.out.println("Esperando fin llamda 2");
				}

				if (resultadoLlamada1.isDone() && resultadoLlamada2.isDone()) {
					System.out.println("Llamada 1: " + resultadoLlamada1.get() + " minutos");
					System.out.println("Llamada 2: " + resultadoLlamada2.get() + " minutos");
					executor.shutdown();
					return;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}*/
		
		Dispacher dispacher = new Dispacher();
		dispacher.generarLlamadas();

	}
		

}
