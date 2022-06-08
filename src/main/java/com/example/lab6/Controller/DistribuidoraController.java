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
    public ResponseEntity listardistribuidoras(){
        HashMap<String, Object> responsemap = new HashMap<>();
        List<Distribuidora> distribuidoraList = distribuidoraRepository.findAll();
        responsemap.put("estado","ok");
        responsemap.put("distribuidoras",distribuidoraList);
        return new ResponseEntity(responsemap, HttpStatus.OK);
    }

    @GetMapping(value = {"/obtener/{id}", "/obtener"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obtener(@PathVariable("id") Optional<String> idStr){
        HashMap<String, Object> responsemap = new HashMap<>();
        try{
            Integer id = Integer.parseInt(idStr.get());
            if(distribuidoraRepository.existsById(id)){
                responsemap.put("estado","ok");
                responsemap.put("msg",distribuidoraRepository.findById(id).get());
                return new ResponseEntity(responsemap, HttpStatus.OK);
            }else{
                responsemap.put("estado","error");
                responsemap.put("msg","No se encuentra distribuidora con el id solicitado");
                return new ResponseEntity(responsemap, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            responsemap.put("estado","error");
            responsemap.put("msg","Se envio un id invalido");
            return new ResponseEntity(responsemap, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/editar",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editar(@RequestBody Distribuidora distribuidora){
        HashMap<String, Object> responsemap = new HashMap<>();
        try{
            if(distribuidoraRepository.existsById(distribuidora.getId())){
                Optional<Distribuidora> distribuidora1 = distribuidoraRepository.findById(distribuidora.getId());
                distribuidora1.get().setDescripcion(distribuidora.getDescripcion());
                distribuidora1.get().setFundacion(distribuidora.getFundacion());
                distribuidora1.get().setNombre(distribuidora.getNombre());
                distribuidora1.get().setWeb(distribuidora.getWeb());
                distribuidoraRepository.save(distribuidora1.get());
                responsemap.put("estado","ok");
                responsemap.put("msg","Se edito correctamente la distribuidora");
                return new ResponseEntity(responsemap, HttpStatus.OK);
            }else{
                responsemap.put("estado","error");
                responsemap.put("msg","Se envio un id invalido");
                return new ResponseEntity(responsemap, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responsemap.put("estado","error");
            responsemap.put("msg","El id debe ser un numero");
            return new ResponseEntity(responsemap, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/nueva",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity nueva(@RequestBody Distribuidora distribuidora){
        HashMap<String, Object> responsemap = new HashMap<>();
        distribuidoraRepository.save(distribuidora);
        responsemap.put("estado","ok");
        responsemap.put("msg","Distribuidora registrada correctamente");
        return new ResponseEntity(responsemap,HttpStatus.OK);
    }

    @DeleteMapping(value = {"/borrar/{id}", "/borrar"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity borrar(@PathVariable("id") Optional<String> idStr){
        HashMap<String, Object> responsemap = new HashMap<>();
        try{
            Integer id = Integer.parseInt(idStr.get());
            if(distribuidoraRepository.existsById(id)){
                distribuidoraRepository.deleteById(id);
                responsemap.put("estado","ok");
                responsemap.put("msg","Distribuidora eliminada exitosamente");
                return new ResponseEntity(responsemap, HttpStatus.BAD_REQUEST);
            }else{
                responsemap.put("estado","error");
                responsemap.put("msg","No se ha encontrado distribuidora con ese ID");
                return new ResponseEntity(responsemap, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responsemap.put("estado","error");
            responsemap.put("msg","El id debe ser un numero");
            return new ResponseEntity(responsemap, HttpStatus.BAD_REQUEST);
        }
    }
}
