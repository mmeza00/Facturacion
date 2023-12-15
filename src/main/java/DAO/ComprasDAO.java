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

public class ComprasDAO {
     private Connection connection;

    public ComprasDAO() {
        try {
            this.connection = ConexionBD.obtenerConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearCompra(Date fecha, int cantidad, int idProveedor, int idProducto) {
        try (CallableStatement statement = connection.prepareCall("{call CrearCompra(?, ?, ?, ?)}")) {
            statement.setDate(1, fecha);
            statement.setInt(2, cantidad);
            statement.setInt(3, idProveedor);
            statement.setInt(4, idProducto);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCompra(int idCompra, Date nuevaFecha, int nuevaCantidad, int nuevoIdProveedor, int nuevoIdProducto) {
        try (CallableStatement statement = connection.prepareCall("{call ActualizarCompra(?, ?, ?, ?, ?)}")) {
            statement.setInt(1, idCompra);
            statement.setDate(2, nuevaFecha);
            statement.setInt(3, nuevaCantidad);
            statement.setInt(4, nuevoIdProveedor);
            statement.setInt(5, nuevoIdProducto);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCompra(int idCompra) {
        try (CallableStatement statement = connection.prepareCall("{call EliminarCompra(?)}")) {
            statement.setInt(1, idCompra);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerTodasLasCompras() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM VistaCompras")) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
