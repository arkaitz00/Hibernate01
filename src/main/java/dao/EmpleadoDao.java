package main.java.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import main.java.entities.Empleado;

public class EmpleadoDao {
	
	static Logger logger = LogManager.getLogger(EmpleadoDao.class);
	
	public static void insertarEmpleado(Session s,int codigo, String nombre, String apellido1, String apellido2, String lugar_nacimiento,
	String fecha_nacimiento, String direccion, long telefono, String puesto, int cod_departamento) {
		Transaction t = s.beginTransaction();
		try {
			if(!consultarEmpleado(s, codigo)) {
				Empleado empleado = new Empleado(codigo, nombre, apellido1, apellido2, lugar_nacimiento,
						fecha_nacimiento, direccion, telefono, puesto, cod_departamento);
				s.save(empleado);
				t.commit();
				logger.info("Se ha insertado el empleado '"+codigo+"' correctamente");
			}else {
				logger.warn("El empleado '"+codigo+"' ya existe");
			}
		}catch(Exception ex) {
			logger.error(ex);
			t.rollback();
		}
	}
	
	public static boolean consultarEmpleado(Session s, int codigo) {
		if(s.get(Empleado.class, codigo) != null) {
			return true;
		}
		return false;
	}
	
	public static Empleado devolverEmpleado(Session s, int codigo) {
		String hQuery = "from Empleado e where e.codigo = :codigo";
		Empleado empleado = s.createQuery(hQuery, Empleado.class)
							 .setParameter("codigo", codigo).setMaxResults(1).uniqueResult();
		return empleado;
	}
	
	public static void borrarEmpleado(Session s, int codigo) {
		Transaction t = s.beginTransaction();
		try {
			if(consultarEmpleado(s, codigo)) {
				Empleado empleado = devolverEmpleado(s, codigo);
				s.delete(empleado);
				t.commit();
				logger.info("El empleado '"+codigo+"' ha sido borrado correctamente");
			}else {
				logger.warn("El empleado '"+codigo+"' no existe");
			}
		}catch(Exception ex) {
			logger.error(ex);
			t.rollback();
		}
	}
	
	
	public static void editarEmpleado(Session s, int codigo, String direccion, long telefono, String puesto) {
		Transaction t = s.beginTransaction();
		try {
			if(consultarEmpleado(s, codigo)) {
				Empleado empleado = devolverEmpleado(s, codigo);
				empleado.setDireccion(direccion);
				empleado.setTelefono(telefono);
				empleado.setPuesto(puesto);
				s.update(empleado);
				t.commit();
				logger.info("El empleado '"+codigo+"' ha sido editado correctamente");
			}else {
				logger.warn("El empleado '"+codigo+"' no existe");
			}

		}catch(Exception ex) {
			logger.error(ex);
			t.rollback();
		}
	}
	

	public static List<Empleado> listarEmpleadosPorDepartamento(Session s, int cod_departamento) {
		String hql = "from Empleado e where cod_departamento = :cod_departamento";
		List lista = s.createQuery(hql, Empleado.class).setParameter("cod_departamento", cod_departamento).list();		
		return lista;
	}
	
	public static List<Empleado> mayorQue(Session s, int edad) {
		List lista;
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Empleado> query = builder.createQuery(Empleado.class);
		Root<Empleado> root = query.from(Empleado.class);
		query.select(root).where(builder.equal(root.get("fecha_nacimiento"), edad));
		Query<Empleado> q = s.createQuery(query);
		lista = q.getResultList();
		return lista;
	}
	
}
