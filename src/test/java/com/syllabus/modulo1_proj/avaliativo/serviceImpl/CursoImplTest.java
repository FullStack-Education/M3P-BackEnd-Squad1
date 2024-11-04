package com.syllabus.modulo1_proj.avaliativo.serviceImpl;

import com.syllabus.modulo1_proj.avaliativo.dtoUtils.DtoCurso;
import com.syllabus.modulo1_proj.avaliativo.entities.Curso;
import com.syllabus.modulo1_proj.avaliativo.repository.CursoRepository;
import com.syllabus.modulo1_proj.avaliativo.service.implement.CursoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CursoImplTest {

    @InjectMocks
    private CursoImpl cursoService;

    @Mock
    private CursoRepository cursoRepository;

    private DtoCurso dtoCurso;
    private Curso curso;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dtoCurso = new DtoCurso();
        dtoCurso.setNome("Curso de Teste");

        curso = new Curso();
        curso.setNome(dtoCurso.getNome());
    }

    @Test
    public void criarCurso_ShouldSaveAndReturnCurso() {
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        Curso result = cursoService.criarCurso(dtoCurso);

        assertNotNull(result);
        assertEquals("Curso de Teste", result.getNome());
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }

    @Test
    public void obterCursoPorId_CursoExists_ShouldReturnCurso() {
        when(cursoRepository.existsById(1L)).thenReturn(true);
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        Curso result = cursoService.obterCursoPorId(1L);

        assertNotNull(result);
        assertEquals("Curso de Teste", result.getNome());
        verify(cursoRepository, times(1)).findById(1L);
    }

    @Test
    public void obterCursoPorId_CursoDoesNotExist_ShouldThrowException() {
        when(cursoRepository.existsById(1L)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            cursoService.obterCursoPorId(1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Curso não encontrado.", exception.getReason());
        verify(cursoRepository, times(1)).existsById(1L);
    }

    @Test
    public void listarCursos_CursosExistem_ShouldReturnCursos() {
        when(cursoRepository.findAll()).thenReturn(Arrays.asList(curso));

        List<Curso> result = cursoService.listarCursos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Curso de Teste", result.get(0).getNome());
        verify(cursoRepository, times(1)).findAll();
    }

    @Test
    public void listarCursos_CursosNaoExistem_ShouldThrowException() {
        when(cursoRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            cursoService.listarCursos();
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Não há cursos cadastrados.", exception.getReason());
        verify(cursoRepository, times(1)).findAll();
    }

    @Test
    public void atualizarCurso_CursoExists_ShouldUpdateAndReturnCurso() {
        when(cursoRepository.existsById(1L)).thenReturn(true);
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        Curso result = cursoService.atualizarCurso(1L, dtoCurso);

        assertNotNull(result);
        assertEquals("Curso de Teste", result.getNome());
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }

    @Test
    public void atualizarCurso_CursoDoesNotExist_ShouldThrowException() {
        when(cursoRepository.existsById(1L)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            cursoService.atualizarCurso(1L, dtoCurso);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Curso não encontrado para edição.", exception.getReason());
        verify(cursoRepository, times(1)).existsById(1L);
    }

    @Test
    public void deletarCurso_CursoExists_ShouldDeleteCurso() {
        when(cursoRepository.existsById(1L)).thenReturn(true);
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        cursoService.deletarCurso(1L);

        verify(cursoRepository, times(1)).delete(curso);
    }

    @Test
    public void deletarCurso_CursoDoesNotExist_ShouldThrowException() {
        when(cursoRepository.existsById(1L)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            cursoService.deletarCurso(1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Curso não encontrado.", exception.getReason());
        verify(cursoRepository, times(1)).existsById(1L);
    }
}
