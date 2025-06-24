package com.upiiz.ExamenIII.Controllers;

import com.upiiz.ExamenIII.Models.OrdenModel;
import com.upiiz.ExamenIII.Services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
public class OdernController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping("/orden")
    public String detalleOrden() {
        return "orden";
    }

    @GetMapping("/v1/api/orden")
    public ResponseEntity<Map<String,Object>> getAllOrdenes(){
        List<OrdenModel> ordenes=ordenService.findAllOrdenes();
        return ResponseEntity.ok(Map.of(
                "estado",1,
                "mensaje","Listado de ordenes",
                "ordenes",ordenes
        ));
    }

    @PostMapping("/v1/api/orden")
    public ResponseEntity<Map<String,Object>> ordenPost(@RequestBody Map<String,Object> objetoOrden){
        OrdenModel orden = new OrdenModel(
                Integer.parseInt(objetoOrden.get("ordenid").toString()),
                Integer.parseInt(objetoOrden.get("productoid").toString()),
                Integer.parseInt(objetoOrden.get("cantidad").toString()
        ));

        OrdenModel ordenGuardada=ordenService.save(orden);
        if(ordenGuardada!=null)
            return ResponseEntity.ok(Map.of(
                    "estado",1,
                    "mensaje","Orden guardada correctamente",
                    "orden", ordenGuardada
            ));
        else
            return ResponseEntity.ok(Map.of(
                    "estado",0,
                    "mensaje","Error: No se pudo guardar la orden",
                    "orden", objetoOrden
            ));
    }

    @PostMapping("/v1/api/orden/eliminar")
    public ResponseEntity<Map<String,Object>> eliminarDelere(@RequestBody Map<String,Object> objetoOrden){
        int id = Integer.parseInt(objetoOrden.get("id").toString());

        if(ordenService.delete(id) > 0){
            return ResponseEntity.ok(Map.of(
                    "estado",1,
                    "mensaje","Orden eliminada"
            ));
        }else {
            return ResponseEntity.ok(Map.of(
                    "estado",0,
                    "mensaje","No se pudo eliminar la orden"
            ));
        }
    }

    @GetMapping("/v1/api/orden/actualizar/{id}")
    public ResponseEntity<Map<String,Object>> actualizarOrden(@PathVariable int id){
        OrdenModel orden = ordenService.findOrdenById(id);
        return ResponseEntity.ok(Map.of(
                "estado",1,
                "mensaje","Orden encontrada",
                "orden", orden
        ));
    }

    @PostMapping("/v1/api/orden/actualizar/{id}")
    public ResponseEntity<Map<String,Object>> ordenActualizarDatos(@PathVariable Long id, @RequestBody Map<String,Object> objetoOrden){
        OrdenModel orden = new OrdenModel(
                Integer.parseInt(objetoOrden.get("ordenid").toString()),
                Integer.parseInt(objetoOrden.get("productoid").toString()),
                Integer.parseInt(objetoOrden.get("cantidad").toString())
                );
        orden.setId(id);
        if(ordenService.update(orden) > 0)
            return ResponseEntity.ok(Map.of(
                    "estado", 1,
                    "mensaje", "Orden actualizada correctamente",
                    "orden", orden
            ));
        else
            return ResponseEntity.ok(Map.of(
                    "estado", 0,
                    "mensaje", "Error: No se pudo actualizar la orden",
                    "orden", objetoOrden
            ));
    }
}