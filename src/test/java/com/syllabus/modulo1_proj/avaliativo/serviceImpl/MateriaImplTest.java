package com.syllabus.modulo1_proj.avaliativo.serviceImpl;

import com.syllabus.modulo1_proj.avaliativo.dtoUtils.DtoMateria;
import com.syllabus.modulo1_proj.avaliativo.entities.Curso;
import com.syllabus.modulo1_proj.avaliativo.entities.Materia;
import com.syllabus.modulo1_proj.avaliativo.repository.CursoRepository;
import com.syllabus.modulo1_proj.avaliativo.repository.MateriaRepository;
import com.syllabus.modulo1_proj.avaliativo.service.implement.MateriaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MateriaImplTest {

    @Mock
    private MateriaRepository materiaRepository;

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private MateriaImpl materiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarMateriaPorCurso_CursoExiste_DeveRetornarMaterias() {
        Long cursoId = 1L;
        Materia materia1 = new Materia();
        Materia materia2 = new Materia();
        List<Materia> materias = Arrays.asList(materia1, materia2);

        when(cursoRepository.existsById(cursoId)).thenReturn(true);
        when(materiaRepository.listarMateriaPorCurso(cursoId)).thenReturn(materias);

        List<Materia> result = materiaService.listarMateriaPorCurso(cursoId);

        assertEquals(2, result.size());
        verify(cursoRepository, times(1)).existsById(cursoId);
        verify(materiaRepository, times(1)).listarMateriaPorCurso(cursoId);
    }

    @Test
    void listarMateriaPorCurso_CursoNaoExiste_DeveLancarExcecao() {
        Long cursoId = 1L;
        when(cursoRepository.existsById(cursoId)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> materiaService.listarMateriaPorCurso(cursoId));

        assertEquals("404 NOT_FOUND \"Curso inexistente para pesquisa.\"", exception.getMessage());
        verify(cursoRepository, times(1)).existsById(cursoId);
        verify(materiaRepository, never()).listarMateriaPorCurso(cursoId);
    }

    @Test
    void criarMateria_CursoExiste_DeveSalvarMateria() {
        DtoMateria dtoMateria = new DtoMateria();
        dtoMateria.setNome("Matemática");
        dtoMateria.setCurso_id(1L);

        Curso curso = new Curso();
        curso.setId(1L);

        Materia novaMateria = new Materia();
        novaMateria.setNome(dtoMateria.getNome());
        novaMateria.setCurso(curso);

        when(materiaRepository.buscaCurso(dtoMateria.getCurso_id())).thenReturn(curso);
        when(materiaRepository.save(any(Materia.class))).thenReturn(novaMateria);

        Materia result = materiaService.criarMateria(dtoMateria);

        assertEquals("Matemática", result.getNome());
        assertEquals(curso, result.getCurso());
        verify(materiaRepository, times(1)).buscaCurso(dtoMateria.getCurso_id());
        verify(materiaRepository, times(1)).save(any(Materia.class));
    }

    @Test
    void criarMateria_CursoNaoExiste_DeveLancarExcecao() {
        DtoMateria dtoMateria = new DtoMateria();
        dtoMateria.setCurso_id(1L);

        when(materiaRepository.buscaCurso(dtoMateria.getCurso_id())).thenReturn(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> materiaService.criarMateria(dtoMateria));

        assertEquals("404 NOT_FOUND \"É necessário um Curso para se cadastrar uma matéria.\"", exception.getMessage());
        verify(materiaRepository, times(1)).buscaCurso(dtoMateria.getCurso_id());
        verify(materiaRepository, never()).save(any(Materia.class));
    }

    @Test
    void obterMateriaPorId_MateriaExiste_DeveRetornarMateria() {
        Long materiaId = 1L;
        Materia materia = new Materia();
        materia.setId(materiaId);

        when(materiaRepository.existsById(materiaId)).thenReturn(true);
        when(materiaRepository.findById(materiaId)).thenReturn(Optional.of(materia));

        Materia result = materiaService.obterMateriaPorId(materiaId);

        assertEquals(materiaId, result.getId());
        verify(materiaRepository, times(1)).existsById(materiaId);
        verify(materiaRepository, times(1)).findById(materiaId);
    }

    @Test
    void obterMateriaPorId_MateriaNaoExiste_DeveLancarExcecao() {
        Long materiaId = 1L;

        when(materiaRepository.existsById(materiaId)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> materiaService.obterMateriaPorId(materiaId));

        assertEquals("404 NOT_FOUND \"Materia não encontrada.\"", exception.getMessage());
        verify(materiaRepository, times(1)).existsById(materiaId);
        verify(materiaRepository, never()).findById(materiaId);
    }

    @Test
    void atualizarMateria_MateriaECursoExistem_DeveAtualizarMateria() {
        Long materiaId = 1L;
        DtoMateria dtoMateria = new DtoMateria();
        dtoMateria.setNome("Física");
        dtoMateria.setCurso_id(2L);

        Materia materia = new Materia();
        Curso curso = new Curso();
        curso.setId(dtoMateria.getCurso_id());

        when(materiaRepository.existsById(materiaId)).thenReturn(true);
        when(cursoRepository.existsById(dtoMateria.getCurso_id())).thenReturn(true);
        when(materiaRepository.findById(materiaId)).thenReturn(Optional.of(materia));
        when(cursoRepository.getById(dtoMateria.getCurso_id())).thenReturn(curso);
        when(materiaRepository.save(any(Materia.class))).thenReturn(materia);

        Materia result = materiaService.atualizarMateria(materiaId, dtoMateria);

        assertEquals("Física", result.getNome());
        assertEquals(curso, result.getCurso());
        verify(materiaRepository, times(1)).existsById(materiaId);
        verify(cursoRepository, times(1)).existsById(dtoMateria.getCurso_id());
        verify(materiaRepository, times(1)).findById(materiaId);
        verify(materiaRepository, times(1)).save(any(Materia.class));
    }

    @Test
    void deletarMateria_MateriaExiste_DeveDeletarMateria() {
        Long materiaId = 1L;
        Materia materia = new Materia();

        when(materiaRepository.existsById(materiaId)).thenReturn(true);
        when(materiaRepository.findById(materiaId)).thenReturn(Optional.of(materia));

        materiaService.deletarMateria(materiaId);

        verify(materiaRepository, times(1)).existsById(materiaId);
        verify(materiaRepository, times(1)).findById(materiaId);
        verify(materiaRepository, times(1)).delete(materia);
    }

    @Test
    void deletarMateria_MateriaNaoExiste_DeveLancarExcecao() {
        Long materiaId = 1L;

        when(materiaRepository.existsById(materiaId)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> materiaService.deletarMateria(materiaId));

        assertEquals("404 NOT_FOUND \"Materia não encontrada.\"", exception.getMessage());
        verify(materiaRepository, times(1)).existsById(materiaId);
        verify(materiaRepository, never()).findById(materiaId);
        verify(materiaRepository, never()).delete(any(Materia.class));
    }
}
