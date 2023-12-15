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

/**
 *
 * @author Desktop
 */
public class ProductosDAO {

    private Connection connection;

    public ProductosDAO() {
        try {
            this.connection = ConexionBD.obtenerConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearProducto(String nombreProducto, String categoria, String descripcion, float precio) {
        try (CallableStatement statement = connection.prepareCall("{call CrearProducto(?, ?, ?, ?)}")) {
            statement.setString(1, nombreProducto);
            statement.setString(2, categoria);
            statement.setString(3, descripcion);
            statement.setFloat(4, precio);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProducto(int idProducto, String nuevoNombreProducto, String nuevaCategoria,
            String nuevaDescripcion, float nuevoPrecio) {
        try (CallableStatement statement = connection.prepareCall("{call ActualizarProducto(?, ?, ?, ?, ?)}")) {
            statement.setInt(1, idProducto);
            statement.setString(2, nuevoNombreProducto);
            statement.setString(3, nuevaCategoria);
            statement.setString(4, nuevaDescripcion);
            statement.setFloat(5, nuevoPrecio);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(int idProducto) {
        try (CallableStatement statement = connection.prepareCall("{call EliminarProducto(?)}")) {
            statement.setInt(1, idProducto);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerTodosLosProductos() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM VistaProductos")) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
