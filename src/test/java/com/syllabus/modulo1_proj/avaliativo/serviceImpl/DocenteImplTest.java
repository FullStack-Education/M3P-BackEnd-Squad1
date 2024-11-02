package com.syllabus.modulo1_proj.avaliativo.serviceImpl;

import com.syllabus.modulo1_proj.avaliativo.dtoUtils.docente.DtoDocenteRequest;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.docente.DtoDocenteResponse;
import com.syllabus.modulo1_proj.avaliativo.entities.Docente;
import com.syllabus.modulo1_proj.avaliativo.entities.Usuario;
import com.syllabus.modulo1_proj.avaliativo.repository.DocenteRepository;
import com.syllabus.modulo1_proj.avaliativo.repository.UsuarioRepository;
import com.syllabus.modulo1_proj.avaliativo.service.implement.DocenteImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DocenteImplTest {

    @InjectMocks
    private DocenteImpl docenteService;

    @Mock
    private DocenteRepository docenteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Docente docente;
    private DtoDocenteRequest dtoDocenteRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        docente = new Docente();
        docente.setNome("Joao Medeiros");
        docente.setDataEntrada(LocalDate.now());

        dtoDocenteRequest = new DtoDocenteRequest();
        dtoDocenteRequest.setNome("Joao Medeiros");
        dtoDocenteRequest.setUsuario_id(1L);
    }

    @Test
    void criarDocente_UsuarioExists_ReturnsDtoDocenteResponse() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.getById(1L)).thenReturn(new Usuario()); // Simular o usuário
        when(docenteRepository.save(any(Docente.class))).thenReturn(docente);

        DtoDocenteResponse response = docenteService.criarDocente(dtoDocenteRequest);

        assertNotNull(response);
        assertEquals("Joao Medeiros", response.getNome());
    }

    @Test
    void criarDocente_UsuarioNotFound_ThrowsResponseStatusException() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            docenteService.criarDocente(dtoDocenteRequest);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Impossível criar um docente para um usuário não cadastrado.", exception.getReason());
    }

    @Test
    void obterDocentePorId_DocenteExists_ReturnsDtoDocenteResponse() {
        when(docenteRepository.existsById(1L)).thenReturn(true);
        when(docenteRepository.findById(1L)).thenReturn(Optional.of(docente));

        DtoDocenteResponse response = docenteService.obterDocentePorId(1L);

        assertNotNull(response);
        assertEquals("Joao Medeiros", response.getNome());
    }

    @Test
    void obterDocentePorId_DocenteNotFound_ThrowsResponseStatusException() {
        when(docenteRepository.existsById(1L)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            docenteService.obterDocentePorId(1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Docente não encontrado.", exception.getReason());
    }

    @Test
    void atualizarDocente_DocenteExists_ReturnsDtoDocenteResponse() {
        when(docenteRepository.existsById(1L)).thenReturn(true);
        when(docenteRepository.findById(1L)).thenReturn(Optional.of(docente));
        when(docenteRepository.save(any(Docente.class))).thenReturn(docente);

        DtoDocenteResponse response = docenteService.atualizarDocente(1L, dtoDocenteRequest);

        assertNotNull(response);
        assertEquals("Joao Medeiros", response.getNome());
    }

    @Test
    void atualizarDocente_DocenteNotFound_ThrowsResponseStatusException() {
        when(docenteRepository.existsById(1L)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            docenteService.atualizarDocente(1L, dtoDocenteRequest);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Docente não encontrada para alteração.", exception.getReason());
    }

    @Test
    void deletarDocente_DocenteExists() {
        when(docenteRepository.existsById(1L)).thenReturn(true);
        when(docenteRepository.findById(1L)).thenReturn(Optional.of(docente));

        docenteService.deletarDocente(1L);

        verify(docenteRepository, times(1)).delete(docente);
    }

    @Test
    void deletarDocente_DocenteNotFound_ThrowsResponseStatusException() {
        when(docenteRepository.existsById(1L)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            docenteService.deletarDocente(1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Docente não encontrado.", exception.getReason());
    }

    @Test
    void listarTodosDocentes_ReturnsListOfDtoDocenteResponse() {
        when(docenteRepository.findAll()).thenReturn(Arrays.asList(docente));

        List<DtoDocenteResponse> response = docenteService.listarTodosDocentes();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Joao Medeiros", response.get(0).getNome());
    }

    @Test
    void listarTodosDocentes_NoDocentesFound_ThrowsResponseStatusException() {
        when(docenteRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            docenteService.listarTodosDocentes();
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Não há Docentes cadastrados.", exception.getReason());
    }
}
