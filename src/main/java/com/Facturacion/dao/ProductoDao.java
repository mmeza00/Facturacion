
package com.Facturacion.dao;

import com.Facturacion.Domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoDao extends JpaRepository<Producto, Long>{

     //Ejemplo de metodo utilizando consultas SQL
    @Query(nativeQuery = true, value = "SELECT * FROM producto a WHERE a.id_categoria=:id_categoria ")
    public List<Producto> consultaSQL(long id_categoria);
}
