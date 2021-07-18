package login_api;

import java.sql.*;

import javax.swing.JOptionPane;

public class prueba {

	
	static Connection cn;
    
	// Metodos de regalo para que puedan usarlos. Creacion de bases de datos usando apache derby.
    
	/**
	 * Crea la base de datos con el nombre baseDatos.DB 
	 * @return
	 */
  	public static Connection crear(){
         try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");    
            cn = DriverManager.getConnection("jdbc:derby:baseDatos.DB;create=true");   
            JOptionPane.showMessageDialog(null, "Base de datos creada");
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "No hay bases de datos en tu software."+"\n", JOptionPane.ERROR_MESSAGE);
        }
        return cn;
      } 
    
	/**
	 * Creacion de tabla sql para login. Los datos de la tabla son:
	 *  * ID primary key e incrementable.
	 *  * Nombre varchar de 150 caracteres.
	 *  * Contraseña varchar de 150 caracteres.
	 *  
	 *  Tener en cuenta : El texto que se guarda en la dataBase es sensible a estacios en blanco, signos y mayusculas. En caso de tener problemas.
	 *  Es recomendable usar un metodo que filtle todos estos problemas. 
	 */
	public void Tabla() {
		try {
            PreparedStatement pst = con().prepareCall("create table login_data(\n" +
                            "ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
                            "NOMBRE VARCHAR(150),\n" +
                            "PASS VARCHAR(150),\n" +
                            " primary key(ID)\n" +
                            ")");
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "CREADO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "\n" + "tabla login_data error.");
        }
		
	}


    /**
     * Conecta el programa con la base de datos. Cuando se envia al constructor el objeto connection , se le envia este metodo.
     * @return objeto conexion
     */
    public static Connection con (){
    try {
             Class.forName("org.apache.derby.jdbc.EmbeddedDriver");  
              cn = DriverManager.getConnection("jdbc:derby:baseDatos.DB");    
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e + "\n" + "error_creacion");
        }
        return cn;
    }
    
    // metodo main.
    public static void main(String args[]) {
    	
    	
    }
    
    
    // ejemplo : Forma que usaria yo, para crear un nuevo usuario. 
    
    /*
    String nombre = "mauro";
	String pass = "0000";
	login login = new login(con());
	boolean guardado = login.crear_db(nombre,pass);
	
	if (guardado==true) {
		JOptionPane.showMessageDialog(null, "Guardado exitoso.");
	}  
	*/
    
   // ejemplo : Forma que usaria yo, para logear un usuario registrado . 
    
    /*
   login login = new login(con());
    	boolean logeado =login.log_in_db("mauro", "0000");
    	
    	if (logeado==true) {
			JOptionPane.showMessageDialog(null, "Ingreso exitoso.");
		}

	*/
    
 // ejemplo : Forma que usaria yo, para crear cuenta usando ficheros de texto . 
    
    /*
   login login = new login("C:/Users/Mauro/Desktop/cuentas.txt");
    	String nombre = "mauro";
    	String pass = "0000";
    	String cuenta = nombre + "\n" + pass;
    	boolean creado = login.GuardarTXT(cuenta);
    	
    	if (creado==true) {
			JOptionPane.showMessageDialog(null, "Creado exitoso");
		}


	*/
    
    // ejemplo : Forma que usaria yo, para logear cuenta usando ficheros de texto . 
    
    /*
   		login login = new login("C:/Users/Mauro/Desktop/cuentas.txt");
    	boolean resultado = login.LeerTXT("mauro","0000");
    	if (resultado==true) {
			JOptionPane.showMessageDialog(null, "Ingreso exitoso");
		}
     */
    
    
    
    
    
}
