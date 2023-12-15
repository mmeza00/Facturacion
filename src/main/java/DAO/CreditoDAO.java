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

public class CreditoDAO {
    private Connection connection;
    
    public CreditoDAO() {
        try {
            this.connection = ConexionBD.obtenerConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearCredito(String nombreCliente, String cedula, float saldo, int idCliente) {
        try (CallableStatement statement = connection.prepareCall("{call CrearCredito(?, ?, ?, ?)}")) {
            statement.setString(1, nombreCliente);
            statement.setString(2, cedula);
            statement.setFloat(3, saldo);
            statement.setInt(4, idCliente);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCredito(int idCredito, String nuevoNombreCliente, String nuevaCedula,
                                  float nuevoSaldo, int nuevoIdCliente) {
        try (CallableStatement statement = connection.prepareCall("{call ActualizarCredito(?, ?, ?, ?, ?)}")) {
            statement.setInt(1, idCredito);
            statement.setString(2, nuevoNombreCliente);
            statement.setString(3, nuevaCedula);
            statement.setFloat(4, nuevoSaldo);
            statement.setInt(5, nuevoIdCliente);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCredito(int idCredito) {
        try (CallableStatement statement = connection.prepareCall("{call EliminarCredito(?)}")) {
            statement.setInt(1, idCredito);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerTodosLosCreditos() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM VistaCreditos")) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
