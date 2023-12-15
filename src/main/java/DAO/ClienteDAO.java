/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import com.Facturacion.Domain.Cliente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Desktop
 */
public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() {
        try {
            this.connection = ConexionBD.obtenerConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearCliente(String nombre, String cedula, String correo, String telefono, int idCredito) {
        try (CallableStatement statement = connection.prepareCall("{call CrearCliente(?, ?, ?, ?, ?)}")) {
            statement.setString(1, nombre);
            statement.setString(2, cedula);
            statement.setString(3, correo);
            statement.setString(4, telefono);
            statement.setInt(5, idCredito);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCliente(int idCliente, String nuevoNombre, String nuevaCedula, String nuevoCorreo,
                                  String nuevoTelefono, int nuevoIdCredito) {
        try (CallableStatement statement = connection.prepareCall("{call ActualizarCliente(?, ?, ?, ?, ?, ?)}")) {
            statement.setInt(1, idCliente);
            statement.setString(2, nuevoNombre);
            statement.setString(3, nuevaCedula);
            statement.setString(4, nuevoCorreo);
            statement.setString(5, nuevoTelefono);
            statement.setInt(6, nuevoIdCredito);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCliente(int idCliente) {
        try (CallableStatement statement = connection.prepareCall("{call EliminarCliente(?)}")) {
            statement.setInt(1, idCliente);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerTodosLosClientes() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM VistaClientes")) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cliente obtenerClientePorID(int idCliente) {
        try (CallableStatement statement = connection.prepareCall("{call ObtenerClientePorID(?, ?, ?, ?, ?)}")) {
            statement.setInt(1, idCliente);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.registerOutParameter(3, Types.VARCHAR);
            statement.registerOutParameter(4, Types.VARCHAR);
            statement.registerOutParameter(5, Types.VARCHAR);
            statement.registerOutParameter(6, Types.INTEGER);

            statement.execute();

            return new Cliente(
                    idCliente,
                    statement.getString(2),
                    statement.getString(3),
                    statement.getString(4),
                    statement.getString(5),
                    statement.getInt(6)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}