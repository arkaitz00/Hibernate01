package main.java.entities;

public class Empleado {
	
	int codigo;
	String nombre, apellido1, apellido2, lugar_nacimiento, fecha_nacimiento, direccion;
	long telefono;
	String puesto;
	int cod_departamento;
	public Empleado() {
		
	}	
	
	public Empleado(int codigo, String nombre, String apellido1, String apellido2, String lugar_nacimiento, 
			String fecha_nacimiento, String direccion, long telefono, String puesto, int cod_departamento) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.lugar_nacimiento = lugar_nacimiento;
		this.fecha_nacimiento = fecha_nacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.puesto = puesto;
		this.cod_departamento = cod_departamento;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido1() {
		return apellido1;
	}
	
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return apellido2;
	}
	
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public String getLugar_nacimiento() {
		return lugar_nacimiento;
	}
	
	public void setLugar_nacimiento(String lugar_nacimiento) {
		this.lugar_nacimiento = lugar_nacimiento;
	}
	
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public long getTelefono() {
		return telefono;
	}
	
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	
	public String getPuesto() {
		return puesto;
	}
	
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	public int getCod_departamento() {
		return cod_departamento;
	}
	
	public void setCod_departamento(int cod_departamento) {
		this.cod_departamento = cod_departamento;
	}	
}
