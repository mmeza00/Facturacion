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
public class DetalleFacturaDAO {

    private Connection connection;

    public DetalleFacturaDAO() {
        try {
            this.connection = ConexionBD.obtenerConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearDetalleFactura(int numeroFactura, String nombreProducto, int cantidad, float subtotal, int idProducto) {
        try (CallableStatement statement = connection.prepareCall("{call CrearDetalleFactura(?, ?, ?, ?, ?)}")) {
            statement.setInt(1, numeroFactura);
            statement.setString(2, nombreProducto);
            statement.setInt(3, cantidad);
            statement.setFloat(4, subtotal);
            statement.setInt(5, idProducto);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarDetalleFactura(int idDetalle, int nuevoNumeroFactura, String nuevoNombreProducto,
            int nuevaCantidad, float nuevoSubtotal, int nuevoIdProducto) {
        try (CallableStatement statement = connection.prepareCall("{call ActualizarDetalleFactura(?, ?, ?, ?, ?, ?)}")) {
            statement.setInt(1, idDetalle);
            statement.setInt(2, nuevoNumeroFactura);
            statement.setString(3, nuevoNombreProducto);
            statement.setInt(4, nuevaCantidad);
            statement.setFloat(5, nuevoSubtotal);
            statement.setInt(6, nuevoIdProducto);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarDetalleFactura(int idDetalle) {
        try (CallableStatement statement = connection.prepareCall("{call EliminarDetalleFactura(?)}")) {
            statement.setInt(1, idDetalle);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerTodosLosDetallesFactura() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM VistaDetalleFacturas")) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
