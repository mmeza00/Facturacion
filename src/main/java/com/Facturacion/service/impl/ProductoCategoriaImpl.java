package com.Facturacion.service.impl;



import com.Facturacion.Domain.Producto;
import com.Facturacion.dao.ProductoDao;
import com.Facturacion.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoCategoriaImpl implements ProductoService{
    @Autowired
    private ProductoDao productoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos){
        var lista = productoDao.findAll();
        if(activos){
            lista.removeIf(e->!e.isActivo());
        }
        return lista;
    }
    
    
    
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto){
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(Producto producto){
        productoDao.save(producto);
    }
    
    @Override
    @Transactional
    public void delete(Producto producto){
        productoDao.delete(producto);
    }
    
    
    @Transactional(readOnly = true)
    public List<Producto> consultaSQL(long id_categoria) {
        return productoDao.consultaSQL(id_categoria);
    }

    @Override
    public Producto getProducto(Producto producto, Object par1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
