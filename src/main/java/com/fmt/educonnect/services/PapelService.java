package com.fmt.educonnect.services;

import com.fmt.educonnect.datasource.entities.PapelEntity;
import com.fmt.educonnect.datasource.repositories.PapelRepository;
import com.fmt.educonnect.interfaces.PapelInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PapelService implements PapelInterface {

    private final PapelRepository papelRepository;

    public PapelService(PapelRepository papelRepository) {
        this.papelRepository = papelRepository;
    }

    public Optional<PapelEntity> buscarPapelPorId(Long id){
        return papelRepository.findById(id);
    }

}
