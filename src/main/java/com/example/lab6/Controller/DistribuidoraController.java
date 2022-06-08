package com.example.lab6.Controller;

import com.example.lab6.Entity.Distribuidora;
import com.example.lab6.Repository.DistribuidoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController("/distribuidora")
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
    public ResponseEntity listar(){
        HashMap<String, Object> responsemap = new HashMap<>();
        return new ResponseEntity(responsemap, HttpStatus.OK);
    }

}
