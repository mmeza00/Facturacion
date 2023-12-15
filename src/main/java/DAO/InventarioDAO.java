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

/**
 *
 * @author Desktop
 */
public class InventarioDAO {

    private Connection connection;

    public InventarioDAO() {
        try {
            this.connection = ConexionBD.obtenerConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearInventario(int stock, int cantidadIngreso, Date fecha, int idProveedor, int idProducto) {
        try (CallableStatement statement = connection.prepareCall("{call CrearInventario(?, ?, ?, ?, ?)}")) {
            statement.setInt(1, stock);
            statement.setInt(2, cantidadIngreso);
            statement.setDate(3, fecha);
            statement.setInt(4, idProveedor);
            statement.setInt(5, idProducto);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarInventario(int idInventario, int nuevoStock, int nuevaCantidadIngreso, Date nuevaFecha, int nuevoIdProveedor, int nuevoIdProducto) {
        try (CallableStatement statement = connection.prepareCall("{call ActualizarInventario(?, ?, ?, ?, ?, ?)}")) {
            statement.setInt(1, idInventario);
            statement.setInt(2, nuevoStock);
            statement.setInt(3, nuevaCantidadIngreso);
            statement.setDate(4, nuevaFecha);
            statement.setInt(5, nuevoIdProveedor);
            statement.setInt(6, nuevoIdProducto);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarInventario(int idInventario) {
        try (CallableStatement statement = connection.prepareCall("{call EliminarInventario(?)}")) {
            statement.setInt(1, idInventario);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerTodosLosInventarios() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM VistaInventario")) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
