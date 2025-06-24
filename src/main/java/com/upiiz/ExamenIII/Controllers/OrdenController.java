package com.upiiz.ExamenIII.Controllers;

import com.upiiz.ExamenIII.Models.OrdenModel;
import com.upiiz.ExamenIII.Services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public List<OrdenModel> obtenerTodas() {
        return ordenService.findAllOrdenes();
    }

    @PostMapping
    public OrdenModel guardar(@RequestBody OrdenModel orden) {
        return ordenService.save(orden);
    }

    @GetMapping("/{id}")
    public OrdenModel obtenerPorId(@PathVariable int id) {
        return ordenService.findOrdenById(id);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        int afectados = ordenService.delete(id);
        return afectados > 0 ? "Eliminado con éxito" : "No se encontró el registro";
    }

    @PutMapping("/{id}")
    public String actualizar(@PathVariable int id, @RequestBody OrdenModel orden) {
        orden.setId((long) id);
        int afectados = ordenService.update(orden);
        return afectados > 0 ? "Actualizado con éxito" : "No se encontró el registro";
    }
}
