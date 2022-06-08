package com.example.lab6.Controller;

import com.example.lab6.Entity.Juego;
import com.example.lab6.Repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Optional;

@RestController
@CrossOrigin
public class JuegosController {

    @Autowired
    JuegoRepository juegoRepository;

    @GetMapping(value = "/juegos")
    public ResponseEntity<HashMap<String,Object>> listarJuegos(){
        HashMap<String,Object> responseMAP = new HashMap<>();
        responseMAP.put("estado","ok");
        responseMAP.put("juegos", juegoRepository.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(responseMAP);
    }

    @GetMapping(value = "/juego/obtener/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<HashMap<String, Object>> juegoxID(@PathVariable("id") String idstr){
        HashMap<String,Object> responseMAP = new HashMap<>();
        try{
            int id = Integer.parseInt(idstr);
            Optional<Juego> opt = juegoRepository.findById(id);
            if (opt.isPresent()){
                responseMAP.put("estado","ok");
                responseMAP.put("juegos",opt.get());
                return  ResponseEntity.ok(responseMAP);
            }else{
                responseMAP.put("estado","error");
                responseMAP.put("msg", "el juego no se encuentra registrado en el sistema");
                return ResponseEntity.badRequest().body(responseMAP);
            }
        }catch (NumberFormatException e){
            responseMAP.put("estado","error");
            responseMAP.put("msg", "El ID debe ser un número entero");
            return ResponseEntity.badRequest().body(responseMAP);
        }

    }


    @PostMapping(value = "/juego/nuevo",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> guardarJuego(@RequestBody Juego juego){
        HashMap<String,Object> responseJson = new HashMap<>();
        juegoRepository.save(juego);
        responseJson.put("estado","ok");
        responseJson.put("msg","El juego fue creado exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }


    @PutMapping(value = "/juego/actualizar",produces = MediaType.APPLICATION_JSON_VALUE)
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

    @DeleteMapping(value = "/juego/eliminar/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> borrarJuego(@PathVariable("id") String idStr){
        HashMap<String, Object> responseMap = new HashMap<>();
        try {
            int id = Integer.parseInt(idStr);
            if (juegoRepository.existsById(id)){
                juegoRepository.deleteById(id);
                responseMap.put("estado","ok");
                responseMap.put("msg","juego eliminado exitosamente");
                return ResponseEntity.ok(responseMap);
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
