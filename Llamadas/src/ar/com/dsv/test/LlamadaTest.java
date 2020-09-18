package ar.com.dsv.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ar.com.dsv.Dispacher;
import ar.com.dsv.Empleado;

class LlamadaTest {

	List<Empleado> empleados = new ArrayList<Empleado>();
	Dispacher dispacher = new Dispacher();
	
	@Before
	void inicializaEmpleados() {
		empleados = new ArrayList<Empleado>();
		dispacher.setMaximoLlamadas(100);
		dispacher.setEspacioEntreLlamadas(3000L);
		System.out.println("Fin de seteo");
	}
	
	
	@Test
	public void test() {
		empleados.add(new Empleado(1, "Juan", Empleado.EMPLEADO));
		empleados.add(new Empleado(2, "Pedro", Empleado.EMPLEADO));
		empleados.add(new Empleado(3, "Andres", Empleado.EMPLEADO));
		empleados.add(new Empleado(4, "Daniel", Empleado.SUPERVISOR));
		empleados.add(new Empleado(5, "Victor", Empleado.DIRECTOR));
		
		dispacher.setEmpleados(empleados);
		dispacher.setMaximoLlamadas(10);
		dispacher.setEspacioEntreLlamadas(3000L);
		
		this.dispacher.generarLlamadas();
	;

		assertEquals(5, this.empleados.size());
	}
	
	@Test
	public void sinEmpleados() {
		dispacher.setEmpleados(new ArrayList<Empleado>());
		dispacher.setMaximoLlamadas(10);
		dispacher.setEspacioEntreLlamadas(3000L);
		
		this.dispacher.generarLlamadas();
		Assertions.assertThrows(NoSuchElementException.class, () -> {
		    this.empleados = new ArrayList<Empleado>(); dispacher.generarLlamadas();
		  });
	}

}
