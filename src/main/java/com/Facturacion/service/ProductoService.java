
package com.Facturacion.service;

import com.Facturacion.dao.CategoriaDao;
import com.Facturacion.dao.ProductoDao;
import java.util.List;
import com.Facturacion.Domain.Producto;
import org.springframework.beans.factory.annotation.Autowired;

public interface ProductoService {
    
    public List<Producto> getProductos(boolean activos);
    
    public Producto getProducto(Producto producto, Object par1);
    

    public List<Producto> consultaSQL(long id_categoria);
    
    public void save(Producto categoria);
    
    public void delete(Producto categoria);
    
    
}
