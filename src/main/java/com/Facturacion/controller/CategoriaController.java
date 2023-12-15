
package com.Facturacion.controller;

import com.Facturacion.Domain.Categoria;
import com.Facturacion.service.CategoriaService;
import com.Facturacion.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;
    
   
    
    @GetMapping("/listado")
    private String listado(Model model){
      
        var productos = productoService.consultaSQL(1);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("id_categoria", 1);
        return "/categorias/listado";
    }
    
    @GetMapping("/diademas")
    private String diademas(Model model){
        var productos = productoService.consultaSQL(2);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("id_categoria", 2);
        return "/categorias/listado";
    }
    
    @GetMapping("/colas")
    private String colas(Model model){
        var productos = productoService.consultaSQL(3);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("id_categoria", 3);
        return "/categorias/listado";
    }
    
    @GetMapping("/nuevo")
    public String categoriaNuevo(Categoria categoria){
        return "/categorias/modifica";
    }
    
    @PostMapping("/guardar")
    public String categoriaGuardar(Categoria categoria, @RequestParam("imagenFile") MultipartFile imagenFile){
        categoriaService.save(categoria);
        return "redirect:/categorias/listado";
    }
    
    @GetMapping("/eliminar/{idCategoria}")
    public String categoriaEliminar(Categoria categoria){
        categoriaService.delete(categoria);
        return "redirect:/categorias/listado";
    }
    
    @GetMapping("/modificar/{idCategoria}")
    public String categoriaModificar(Categoria categoria, Model model){
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categorias", categoria);
        return "/categorias/modifica";
    }
    
    
    
    
}
