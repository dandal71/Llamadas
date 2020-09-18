package ar.com.dsv;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispacher {
	
	private int maximoLlamadas = 0;
	private List<Empleado> empleados = new ArrayList<>();
	ExecutorService poolThread = Executors.newFixedThreadPool(10);
	private Long espacioEntreLlamadas = 1000L;
	public Long getEspacioEntreLlamadas() {
		return espacioEntreLlamadas;
	}

	public void setEspacioEntreLlamadas(Long espacioEntreLlamadas) {
		this.espacioEntreLlamadas = espacioEntreLlamadas;
	}

	private List<Llamada> llamadasSinAtender = new ArrayList<Llamada>();
	private List<Llamada> llamadasAtendidas = new ArrayList<Llamada>();
	
	public Dispacher() {
		super();
		
		
	}
	
	public void generarLlamadas() throws NoSuchElementException {
		
		Llamada llamada;
		
		if (this.empleados.size() > 0) {
			
			for (int i = 0; i < this.maximoLlamadas; i++) {
			
				llamada = new Llamada(i);
				
				if (!dispacherCall(llamada)) {
					this.llamadasSinAtender.add(llamada);
				} else {
					this.llamadasAtendidas.add(llamada);
				}
				
				try {
					Thread.sleep(this.espacioEntreLlamadas); //apusa entre llamadas	
				} catch (InterruptedException ex) {
					
				}
			
			}
		
			this.llamadasSinAtender.forEach(llamadaAtendida -> System.out.println("Llamada Atendidas: " + llamadaAtendida.getId()));
			this.llamadasSinAtender.forEach(llamadaError -> System.out.println("Llamada con error: " + llamadaError.getId()));
		} else {
			throw new NoSuchElementException();
		}
	}


	/**
	 * Trata de asignar una llamada a un empleado, en caso de que la misma no pueda ser atendida retorna falso.
	 * 
	 * @param llamada Llamada a procesar
	 * @return True si la llamada puede ser atendida, false en caso contrario
	 */
	private boolean dispacherCall(Llamada llamada) {
		
		
		asignarAtendedor(llamada);
			
		if (llamada.getEmpleado() != null) { 	
			return true;	//La llamada fue atendida
		}
		
		return false; //La llamada no fue atendida
	}
	
	/**
	 * Asigna un empleado a una llamda
	 */
	private void asignarAtendedor(Llamada llamada) {
		
		this.empleados.stream().filter(empleado -> empleado.getTipo().equals(Empleado.EMPLEADO) && !empleado.isOcupado())
				.findFirst().ifPresent(e -> {llamada.setEmpleado(e); e.setOcupado(true);});
				
		if (llamada.getEmpleado() == null) {
			this.empleados.stream().filter(empleado -> empleado.getTipo().equals(Empleado.SUPERVISOR) && !empleado.isOcupado())
				.findFirst().ifPresent(e -> {llamada.setEmpleado(e); e.setOcupado(true);});
		}
		
		if (llamada.getEmpleado() == null) {
			this.empleados.stream().filter(empleado -> empleado.getTipo().equals(Empleado.DIRECTOR) && !empleado.isOcupado())
				.findFirst().ifPresent(e -> {llamada.setEmpleado(e); e.setOcupado(true);});
		}
		
		
		if (llamada.getEmpleado() != null) {
			this.poolThread.execute(llamada); //Atiende la llamada
		}
		else
			System.out.println("Llamada sin atender: " + llamada.getId());
	}


	public int getMaximoLlamadas() {
		return maximoLlamadas;
	}

	public void setMaximoLlamadas(int maximoLlamadas) {
		this.maximoLlamadas = maximoLlamadas;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	

}
