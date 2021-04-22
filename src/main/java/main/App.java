package main.java.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import main.java.dao.DepartamentoDao;
import main.java.dao.EmpleadoDao;
import main.java.entities.Departamento;
import main.java.entities.Empleado;
import main.java.utils.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App{	

	private static Logger logger = LogManager.getLogger(App.class);
	
    public static void main( String[] args ){
    	Session s = HibernateUtil.getSessionFactory().openSession();
    	int codDepartamento, codResponsable, codEmpleado;
    	String nombreDepartamento, nombre, apellido1, apellido2, lugar_nacimiento, fecha_nacimiento, direccion, puesto;
    	long telefono;
    	boolean correcto;
    	List listado;
    	Scanner teclado = new Scanner(System.in);
    	
    	do {
	    	System.out.print("Introduce codigo de departamento: ");
	    	codDepartamento = teclado.nextInt();
	    	teclado.nextLine();
	    	System.out.print("Introduce nombre de departamento: ");
	    	nombreDepartamento = teclado.nextLine();
	    	System.out.print("Introduce codigo de responsable: ");
	    	codResponsable = teclado.nextInt();
	    	correcto = DepartamentoDao.consultarDepartamento(s, codDepartamento);
    	}while(correcto);
    	DepartamentoDao.insertarDepartamento(s, codDepartamento, nombreDepartamento, codResponsable);
    	DepartamentoDao.editarDepartamento(s, codDepartamento, "Ocio");
    	//DepartamentoDao.borrarDepartamento(s, codDepartamento);
    	
    	do {
    		System.out.print("Introduce codigo de empleado: ");
    		codEmpleado = teclado.nextInt();
	    	teclado.nextLine();
	    	System.out.print("Introduce nombre: ");
	    	nombre = teclado.nextLine();
	    	System.out.print("Introduce primer apellido: ");
	    	apellido1 = teclado.nextLine();
	    	System.out.print("Introduce segundo apellido: ");
	    	apellido2 = teclado.nextLine();
	    	System.out.print("Introduce lugar de nacimiento: ");
	    	lugar_nacimiento = teclado.nextLine();
	    	System.out.print("Introduce fecha de nacimiento(yy-mm-dd): ");
	    	fecha_nacimiento = teclado.nextLine();
	    	System.out.print("Introduce direccion: ");
	    	direccion = teclado.nextLine();
	    	System.out.print("Introduce telefono: ");
	    	telefono = teclado.nextLong();
	    	System.out.print("Introduce puesto: ");
	    	puesto = teclado.nextLine();
	    	correcto = EmpleadoDao.consultarEmpleado(s, codDepartamento);
    	}while(correcto);
    	EmpleadoDao.insertarEmpleado(s, codEmpleado, nombre, apellido1, apellido2, lugar_nacimiento, fecha_nacimiento, direccion, telefono, puesto, codDepartamento);
    	EmpleadoDao.editarEmpleado(s, codEmpleado, "Plaza alemania", 900024536, "RRHH");
    	//EmpleadoDao.borrarEmpleado(s, codEmpleado);
    	System.out.print("Introduce codDepartamento para devolver a los empleados (1-3): ");
    	codDepartamento = teclado.nextInt();
    	listado = EmpleadoDao.listarEmpleadosPorDepartamento(s, codDepartamento);
    	System.out.println(listado);
    	logger.info("Ha acabado la ejecucion del programa");
    }
}
