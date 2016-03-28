package pe.edu.cibertec.proyemp.jpa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.edu.cibertec.proyemp.jpa.domain.Departamento;
import pe.edu.cibertec.proyemp.jpa.domain.Empleado;

public class JPATest {
	
	private EntityManager manager;
	
	public JPATest(EntityManager manager) {
		this.manager = manager;
	}
	
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager manager = factory.createEntityManager();
		JPATest test = new JPATest(manager);
		
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
			test.crearEmpleados2();
	
		tx.commit();

		test.listarEmpleados();
		test.mostrarEmpleadoId1();
		System.out.println(".. done");

	}
	
	
	private void crearEmpleados() {
		
			Departamento departamento = new Departamento("Java");
			manager.persist(departamento);

			manager.persist(new Empleado("Bob",departamento));
			manager.persist(new Empleado("Mike",departamento));
		
	}
	
	private void crearEmpleados2() {
		
		Departamento departamento = new Departamento("Java");
		
		Empleado emp1 = new Empleado("Bob");
		Empleado emp2 = new Empleado("Mike");
		
//		List<Empleado> empleados = new ArrayList<Empleado>();
//		empleados.add(emp1);
//		empleados.add(emp2);
		departamento.setEmpleados(Arrays.asList(emp1, emp2));
		
		manager.persist(departamento);
		
	}
	
	private void listarEmpleados() {
		List<Empleado> resultList = 
				manager.createQuery("Select a From Empleado a",
						Empleado.class).getResultList();
		
		System.out.println("nro de empleados:" + resultList.size());
		for (Empleado emp : resultList) {
			System.out.println("siguiente empleado: " + emp);
		}
	}
	
	
	private void mostrarEmpleadoId1(){
//		Empleado emp = manager.createQuery(""
//				+ "Select a From Empleado a where a.id = 1",
//				Empleado.class).getSingleResult();
		
		Empleado emp = manager.find(Empleado.class,new Long(1));
		
		
		
		System.out.println(emp);
		
	}

	
	
	

}
