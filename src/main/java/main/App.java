package main.java.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import main.java.dao.DepartamentoDao;
import main.java.dao.EmpleadoDao;
import main.java.entities.Departamento;
import main.java.utils.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App{	

	private static Logger logger = LogManager.getLogger(App.class);
	
    public static void main( String[] args ){
    	Session s = HibernateUtil.getSessionFactory().openSession();
    	//DepartamentoDao.insertarDepartamento(s, 4, "Marketing", 1);
    	//DepartamentoDao.editarDepartamento(s, 4, "Ocio");
    	DepartamentoDao.borrarDepartamento(s, 4);
    	//EmpleadoDao.insertarEmpleado(s, 2, "Carlos", "Perez", "Lopez", "Monfarracinos", "31-01-18", "Tres cruces", 700060020, "Administrador", 4);
    	//EmpleadoDao.editarEmpleado(s, 2, "Plaza alemania", 900024536, "RRHH");
    	//EmpleadoDao.borrarEmpleado(s, 2);
    	logger.info("Ha acabado la ejecucion del programa");
    }
}
