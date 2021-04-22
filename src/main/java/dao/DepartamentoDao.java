package main.java.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import main.java.entities.Departamento;
import main.java.utils.HibernateUtil;

public class DepartamentoDao {
	
	static Logger logger = LogManager.getLogger(DepartamentoDao.class);
	
	public static void insertarDepartamento(Session s, int codigo, String nombre, int codigoResponsable) {
		Transaction t = s.beginTransaction();
		
		try {
			if(!consultarDepartamento(s, codigo)){
				Departamento departamento = new Departamento(codigo, nombre, codigoResponsable);
				s.save(departamento);
				t.commit();
				logger.info("El departamento '"+codigo+"' ha sido insertado");
			}else {
				logger.warn("El departamento '"+codigo+"' ya existe");
			}
		}catch(Exception ex) {
			logger.error(ex);
			t.rollback();
		}
	}
	
	public static boolean consultarDepartamento(Session s, int codigo) {
		if(s.get(Departamento.class, codigo) != null) {
			return true;
		}
		return false;
	}
	
	public static Departamento devolverDepartamento(Session s, int codigo) {
		String hQuery = "from Departamento d where d.codigo = :codigo";
		Departamento departamento = s.createQuery(hQuery, Departamento.class)
									.setParameter("codigo", codigo).setMaxResults(1).uniqueResult();
		return departamento;
	}
	
	public static void borrarDepartamento(Session s, int codigo) {
		Transaction t = s.beginTransaction();
		try {
			if(consultarDepartamento(s, codigo)) {		
				Departamento departamento = devolverDepartamento(s, codigo);
				s.delete(departamento);
				t.commit();
				logger.info("El departamento '"+codigo+"' ha sido borrado correctamente");
			}else {
				logger.warn("El departamento '"+codigo+"' no existe");
			}
		}catch(Exception ex) {
			logger.error(ex);
			t.rollback();
		}
	}
	
	public static void editarDepartamento(Session s, int codigo, String nombre) {
		Transaction t = s.beginTransaction();
		try {
			if(consultarDepartamento(s, codigo)) {
				Departamento departamento = devolverDepartamento(s, codigo);
				departamento.setNombre(nombre);
				s.update(departamento);
				t.commit();
				logger.info("El departamento '"+codigo+"' se ha editado correctamente");
			}else {
				logger.warn("El departamento '"+codigo+"' no existe");
			}
		}catch(Exception ex) {
			logger.error(ex);
			t.rollback();
		}
	}
}
