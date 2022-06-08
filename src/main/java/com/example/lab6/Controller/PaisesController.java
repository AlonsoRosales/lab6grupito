package com.example.lab6.Controller;

import com.example.lab6.Entity.Paises;
import com.example.lab6.Repository.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/paises")
public class PaisesController {

    @Autowired
    PaisesRepository paisesRepository;

    @GetMapping("")
    public List<Paises> listarPaises() {
        return paisesRepository.findAll();
    }
}
