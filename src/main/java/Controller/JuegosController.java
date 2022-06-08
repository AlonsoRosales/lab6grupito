package Controller;

import Entity.Juego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class JuegosController {

    @Autowired


    @GetMapping("/juego")
    public List<Juego> listarJuegos(){
        return ;
    }

    @PostMapping("/juego")
    public ResponseEntity<HashMap<String, Object>> guardarJuego(@RequestBody Juego juego){

    }


    @PutMapping("/juego")
    public ResponseEntity<HashMap<String, Object>> actualizarJuego(@RequestBody Juego juego){

    }

    @DeleteMapping("/juego/{id}")
    public ResponseEntity<HashMap<String, Object>> borrarJuego(@PathVariable("id") String idStr){


    }



}
