/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
 
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ProveedoresDAO {
    private Connection connection;

    public ProveedoresDAO() {
        try {
            this.connection = ConexionBD.obtenerConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearProveedor(String nombreProveedor, String contactoProveedor, String correoProveedor,
                               String productos, String diaEntrega) {
        try (CallableStatement statement = connection.prepareCall("{call CrearProveedor(?, ?, ?, ?, ?)}")) {
            statement.setString(1, nombreProveedor);
            statement.setString(2, contactoProveedor);
            statement.setString(3, correoProveedor);
            statement.setString(4, productos);
            statement.setString(5, diaEntrega);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProveedor(int idProveedor, String nuevoNombreProveedor, String nuevoContactoProveedor,
                                    String nuevoCorreoProveedor, String nuevosProductos, String nuevoDiaEntrega) {
        try (CallableStatement statement = connection.prepareCall("{call ActualizarProveedor(?, ?, ?, ?, ?, ?)}")) {
            statement.setInt(1, idProveedor);
            statement.setString(2, nuevoNombreProveedor);
            statement.setString(3, nuevoContactoProveedor);
            statement.setString(4, nuevoCorreoProveedor);
            statement.setString(5, nuevosProductos);
            statement.setString(6, nuevoDiaEntrega);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProveedor(int idProveedor) {
        try (CallableStatement statement = connection.prepareCall("{call EliminarProveedor(?)}")) {
            statement.setInt(1, idProveedor);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerTodosLosProveedores() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM VistaProveedores")) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
