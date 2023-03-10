package com.body.controller;

import com.body.model.Participante;
import com.body.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/participante")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    // Return the response entity for the user
    @GetMapping
    public ResponseEntity<List<Participante>> getallParticipantes(){
        return ResponseEntity.ok().body(participanteService.listarParticipantes());
    }

    @PostMapping
    public ResponseEntity<Object> insertParticipante(@RequestBody Participante participante){
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.inserirParticipante(participante));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getParticipante(@PathVariable(value = "id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(participanteService.listarParticipante(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletParticipante(@PathVariable(value = "id") Integer id){
        participanteService.removerParticipante(id);
        return ResponseEntity.status(HttpStatus.OK).body("Partipante Removido do banco de dados!");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateParticipante(@PathVariable(value = "id") Integer id, @RequestBody Participante participante){
        Optional<Participante> dadosParticipante = participanteService.listarParticipante(id);
        Participante alterarParticipante = dadosParticipante.get();
        alterarParticipante.setNome(participante.getNome());
        alterarParticipante.setApelido(participante.getApelido());
        alterarParticipante.setAltura(participante.getAltura());
        alterarParticipante.setPeso(participante.getPeso());
        alterarParticipante.setIdade(participante.getIdade());

        return ResponseEntity.status(HttpStatus.OK).body(participanteService.inserirParticipante(alterarParticipante));
    }


}
