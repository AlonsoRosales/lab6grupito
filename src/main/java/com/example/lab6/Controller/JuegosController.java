package com.example.lab6.Controller;

import com.example.lab6.Entity.Juego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class JuegosController {

    @Autowired


    @GetMapping("/juego")
    public List<Juego> listarJuegos(){
        return ;
    }

    @PostMapping("/juego")
    public ResponseEntity<HashMap<String, Object>> guardarJuego(@RequestBody Juego juego){
        HashMap<String,Object> responseJson = new HashMap<>();
        try {
            Optional<Juego> optionalJuego ;
            if (optionalJuego.isPresent()){
                responseJson.put("result", );
            }
        }catch (NumberFormatException e){
            responseJson.put("msg", "el ID de ser un número entero postivo");
        }
    }


    @PutMapping("/juego")
    public ResponseEntity<HashMap<String, Object>> actualizarJuego(@RequestBody Juego juego){

    }

    @DeleteMapping("/juego/{id}")
    public ResponseEntity<HashMap<String, Object>> borrarJuego(@PathVariable("id") String idStr){
         HashMap<String, Object> responseMap = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
        }catch (NumberFormatException e){

        }

    }



}
