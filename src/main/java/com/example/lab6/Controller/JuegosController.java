package com.example.lab6.Controller;

import com.example.lab6.Entity.Juego;
import com.example.lab6.Repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Optional;

@RestController
public class JuegosController {

    @Autowired
    JuegoRepository juegoRepository;

    @GetMapping("/juego")
    public ResponseEntity<HashMap<String,Object>> listarJuegos(){
        HashMap<String,Object> responseMAP = new HashMap<>();
        responseMAP.put("estado","ok");
        responseMAP.put("juegos", juegoRepository.findAll());
        return ResponseEntity.ok(responseMAP);
    }

    @PostMapping("/juego")
    public ResponseEntity<HashMap<String, Object>> guardarJuego(@RequestBody Juego juego){
        HashMap<String,Object> responseJson = new HashMap<>();
        juegoRepository.save(juego);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }


    @PutMapping("/juego")
    public ResponseEntity<HashMap<String, Object>> actualizarJuego(@RequestBody Juego juego){
        HashMap<String, Object> respondeMap = new HashMap<>();
        if (juego.getId() != null && juego.getId() > 0){
            Optional<Juego> opt = juegoRepository.findById(juego.getId());
            if (opt.isPresent()){
                Juego juegoDb = opt.get();
                if (juego.getDescripcion() != null){
                    juegoDb.setDescripcion(juego.getDescripcion());
                }
                if (juego.getImage() != null){
                    juegoDb.setImage(juego.getImage());
                }
                juegoRepository.save(juegoDb);
                respondeMap.put("estado","actualizado");
                return ResponseEntity.ok(respondeMap);
            } else{
                respondeMap.put("estado", "error");
                respondeMap.put("msg", "El producto a actualizar no existe");
                return ResponseEntity.badRequest().body(respondeMap);
            }
        }else{
            respondeMap.put("estado", "error");
            respondeMap.put("msg", "Debe enviar un ID");
            return ResponseEntity.badRequest().body(respondeMap);
        }
    }

    @DeleteMapping("/juego/{id}")
    public ResponseEntity<HashMap<String, Object>> borrarJuego(@PathVariable("id") String idStr){
        HashMap<String, Object> responseMap = new HashMap<>();
        try {
            int id = Integer.parseInt(idStr);
            if (juegoRepository.existsById(id)){
                juegoRepository.deleteById(id);
                responseMap.put("estado","borrado exitoso");
                return ResponseEntity.badRequest().body(responseMap);
            }else {
                responseMap.put("estado","error");
                responseMap.put("msg","No se encontro un juego con ese ID");
                return ResponseEntity.badRequest().body(responseMap);
            }
        }catch (NumberFormatException e){
            responseMap.put("estado","error");
            responseMap.put("msg","El ID debe ser un numero");
            return ResponseEntity.badRequest().body(responseMap);
        }

    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<HashMap<String, Object>> gestionExcepcion(HttpServletRequest request){
        HashMap<String, Object> respondeMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")){
            respondeMap.put("estado", "error");
            respondeMap.put("msg","Debe enviar un juego");
        }
        return ResponseEntity.badRequest().body(respondeMap);
    }


}
