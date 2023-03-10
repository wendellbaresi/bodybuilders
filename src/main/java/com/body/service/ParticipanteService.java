package com.body.service;

import com.body.model.Categoria;
import com.body.model.Participante;
import com.body.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;


    // Create the service where will find all partipante class
    public List<Participante> listarParticipantes(){
        List<Participante> participante = participanteRepository.findAll();
        return participante;
    }

    // Create the service for insert into a new participant
    @Transactional // bean by unexpected error where insert a new participant the bean will restart for beginning
    public Participante inserirParticipante(Participante participante){
        Categoria categoria1 = new Categoria(1);
        Categoria categoria2 = new Categoria(2);
        Categoria categoria3 = new Categoria(3);

        if (participante.getPeso() <= 80 && participante.getAltura() <= 1.72){
            participante.setCategoria(categoria1);
        } else if(participante.getPeso() <= 100 && participante.getAltura() <= 1.83){
            participante.setCategoria(categoria2);
        } else{
            participante.setCategoria(categoria3);
        }

        return participanteRepository.save(participante);
    }

    // Optional do the tratament for when you have null values
    public Optional<Participante> listarParticipante(Integer id){
        return participanteRepository.findById(id);
    }

    // Create the service for delet the participant
    @Transactional
    public void removerParticipante(Integer id){
        participanteRepository.deleteById(id);
    }
}
