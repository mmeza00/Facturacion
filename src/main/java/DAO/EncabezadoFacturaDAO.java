/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EncabezadoFacturaDAO {
    private Connection connection;

    public EncabezadoFacturaDAO() {
        try {
            this.connection = ConexionBD.obtenerConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearEncabezadoFactura(Date fecha, String nombreCliente, String cedula, float total) {
        try (CallableStatement statement = connection.prepareCall("{call CrearEncabezadoFactura(?, ?, ?, ?)}")) {
            statement.setDate(1, fecha);
            statement.setString(2, nombreCliente);
            statement.setString(3, cedula);
            statement.setFloat(4, total);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarEncabezadoFactura(int numeroFactura, Date nuevaFecha, String nuevoNombreCliente,
                                            String nuevaCedula, float nuevoTotal) {
        try (CallableStatement statement = connection.prepareCall("{call ActualizarEncabezadoFactura(?, ?, ?, ?, ?)}")) {
            statement.setInt(1, numeroFactura);
            statement.setDate(2, nuevaFecha);
            statement.setString(3, nuevoNombreCliente);
            statement.setString(4, nuevaCedula);
            statement.setFloat(5, nuevoTotal);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEncabezadoFactura(int numeroFactura) {
        try (CallableStatement statement = connection.prepareCall("{call EliminarEncabezadoFactura(?)}")) {
            statement.setInt(1, numeroFactura);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerTodosLosEncabezadosFactura() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM VistaEncabezadoFacturas")) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
