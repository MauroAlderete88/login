package login_api;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.util.Scanner;

import javax.swing.JOptionPane;


/**
 * La clase consta de 3 constructores y 4 metodos. De acuerdo a las necesidades requeridas se usaran unos o otros.
 * @author Mauro
 *
 */


public class login{
	
	Connection conexion ; 
	String ruta;
	
	// CONSTRUCTORES // 
	
	login(){
		
	}
	
	/**
	 * Usar este constructor en caso de usar la base de datos.
	 * @param cn
	 */
	login(Connection cn){
		this.conexion = cn;
	}
	
	/**
	 * Usar este constructor en caso de guardar las cuentas en un archivo txt.
	 * @param url
	 */
	login(String url){
		this.ruta=url;
	}
	
	
	
	/**
	 * 
	 * Clase que inserta en la base de datos el nuevo usuario. Si la operacion sale bien, retorna true.
	 * @param nombre
	 * @param pass
	 * @return
	 */
	public boolean crear_db(String nombre, String pass) {
		boolean guardado =false; 
		try {
			 PreparedStatement pst = conexion.prepareCall("insert into login_data (NOMBRE,PASS) values(?,?)");
			 pst.setString(1, nombre);
			 pst.setString(2, pass);
			 
			 pst.execute();
			 System.out.print("Guardado exitoso.");
			 guardado=true;
			 pst.close();
		 }catch(SQLException e){
			 JOptionPane.showMessageDialog(null, e);
		 }
		return guardado;
	}
	
	
	/**
	 * 
	 * Busca en la base de datos los datos 'cuenta' y 'contraseña', si hay coincidencia retorna true.
	 * @param nombre
	 * @param pass
	 * @return
	 */
	public boolean log_in_db(String nombre, String pass) {
		boolean logeado = false;
		try {
			PreparedStatement pst = conexion.prepareCall("select * from login_data where NOMBRE='"+nombre+"' AND PASS='"+pass+"'");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println("Datos de la cuenta ingresada :");
				System.out.println("Identificador :" + rs.getInt("ID"));
				System.out.println("Nombre :" + rs.getString("NOMBRE"));
				System.out.println("Pass :" + rs.getString("PASS"));
				logeado = true;
			}
			pst.close();
		}catch(SQLException e){
			 JOptionPane.showMessageDialog(null, e);
		 }	
		
		
		return logeado;
	}
	
	
	/**
	 * Guarda achivo txt en la ruta especificada en el constructor. Si la operacion sale bien retorna true.
	 * @param contenido
	 * @return
	 */
	public boolean GuardarTXT(String contenido){
        boolean guardado = false;
		try {
            File file = new File(ruta);
            
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
             try (BufferedWriter bw = new BufferedWriter(fw)) {
                 bw.write(contenido);
                 guardado = true;
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return guardado;
    }
    
	
	/**
	 * Recibe de parametro el nombre y la contraseña. busca en la url enviada a traves del constructor. Si encuentra la cuenta retorna True.
	 * @param nombre
	 * @param pass
	 * @return
	 */
    public boolean LeerTXT(String nombre, String pass){
    	boolean logeado = false;
    	String cuentas = null;
    	int contador =0 ;
     try {
            Scanner input = new Scanner(new File(ruta));
            while (input.hasNextLine()) {
               cuentas += input.nextLine();
              }
            if (cuentas.indexOf(nombre)!=-1) {
				++contador;
				if(cuentas.indexOf(pass)!=-1) {
				++contador;
				}
			}
           
            if(contador==2) {
            	logeado=true;
            }
            input.close();
        } catch (FileNotFoundException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
     return logeado;
    }
		
}
