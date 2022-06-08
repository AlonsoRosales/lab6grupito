package com.example.lab6.Controller;

import com.example.lab6.Entity.Distribuidora;
import com.example.lab6.Repository.DistribuidoraRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/distribuidora")
public class DistribuidoraController {

    @Autowired
    DistribuidoraRepository distribuidoraRepository;

    @GetMapping(value = "/listar",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> listardistribuidoras(){
        HashMap<String, Object> responsemap = new HashMap<>();
        List<Distribuidora> distribuidoraList = distribuidoraRepository.findAll();
        responsemap.put("estado","ok");
        responsemap.put("distribuidoras",distribuidoraList);
        return ResponseEntity.ok(responsemap);
    }

    @GetMapping(value = {"/obtener/{id}", "/obtener"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> obtener(@PathVariable("id") String idStr){
        HashMap<String, Object> responsemap = new HashMap<>();
        try{
            Integer id = Integer.parseInt(idStr);
            if(distribuidoraRepository.existsById(id)){
                responsemap.put("estado","ok");
                responsemap.put("distribuidora",distribuidoraRepository.findById(id).get());
                return ResponseEntity.ok(responsemap);
            }else{
                responsemap.put("estado","error");
                responsemap.put("msg","No se encuentra distribuidora con el id solicitado");
                return ResponseEntity.ok(responsemap);
            }
        }catch(Exception e){
            responsemap.put("estado","error");
            responsemap.put("msg","Se envio un id invalido");
            return ResponseEntity.ok(responsemap);
        }
    }

    @PutMapping(value = "/editar",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> editar(@RequestBody Distribuidora distribuidora){
        HashMap<String, Object> responsemap = new HashMap<>();
        try{
            if(distribuidoraRepository.existsById(distribuidora.getId())){
                Optional<Distribuidora> distribuidora1 = distribuidoraRepository.findById(distribuidora.getId());
                distribuidora1.get().setDescripcion(distribuidora.getDescripcion());
                distribuidora1.get().setFundacion(distribuidora.getFundacion());
                distribuidora1.get().setNombre(distribuidora.getNombre());
                distribuidora1.get().setWeb(distribuidora.getWeb());
                distribuidora1.get().setIdsede(distribuidora.getIdsede());
                distribuidoraRepository.save(distribuidora1.get());
                responsemap.put("estado","ok");
                responsemap.put("msg","Se edito correctamente la distribuidora");
                return ResponseEntity.ok(responsemap);
            }else{
                responsemap.put("estado","error");
                responsemap.put("msg","Se envio un id invalido");
                return ResponseEntity.ok(responsemap);
            }
        }catch (Exception e){
            responsemap.put("estado","error");
            responsemap.put("msg","El id debe ser un numero");
            return ResponseEntity.ok(responsemap);
        }
    }

    @PostMapping(value = "/nueva",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> nueva(@RequestBody Distribuidora distribuidora){
        HashMap<String, Object> responsemap = new HashMap<>();
        distribuidoraRepository.save(distribuidora);
        responsemap.put("estado","ok");
        responsemap.put("msg","Distribuidora registrada correctamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(responsemap);
    }

    @DeleteMapping(value = {"/borrar/{id}", "/borrar"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> borrar(@PathVariable("id") Optional<String> idStr){
        HashMap<String, Object> responsemap = new HashMap<>();
        try{
            Integer id = Integer.parseInt(idStr.get());
            if(distribuidoraRepository.existsById(id)){
                distribuidoraRepository.deleteById(id);
                responsemap.put("estado","ok");
                responsemap.put("msg","Distribuidora eliminada exitosamente");
                return ResponseEntity.ok(responsemap);
            }else{
                responsemap.put("estado","error");
                responsemap.put("msg","No se ha encontrado distribuidora con ese ID");
                return ResponseEntity.badRequest().body(responsemap);
            }
        }catch (Exception e){
            responsemap.put("estado","error");
            responsemap.put("msg","El id debe ser un numero");
            return ResponseEntity.badRequest().body(responsemap);
        }
    }
}
