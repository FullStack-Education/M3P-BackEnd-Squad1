package com.syllabus.modulo1_proj.avaliativo.serviceImpl;

import com.syllabus.modulo1_proj.avaliativo.dtoUtils.aluno.DtoAlunoRequest;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.aluno.DtoAlunoResponse;
import com.syllabus.modulo1_proj.avaliativo.entities.Aluno;
import com.syllabus.modulo1_proj.avaliativo.entities.Turma;
import com.syllabus.modulo1_proj.avaliativo.entities.Usuario;
import com.syllabus.modulo1_proj.avaliativo.repository.AlunoRepository;
import com.syllabus.modulo1_proj.avaliativo.repository.TurmaRepository;
import com.syllabus.modulo1_proj.avaliativo.repository.UsuarioRepository;
import com.syllabus.modulo1_proj.avaliativo.service.implement.AlunoImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AlunoImplTest {

    @InjectMocks
    private AlunoImpl alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TurmaRepository turmaRepository;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarAluno_Success() {
        DtoAlunoRequest alunoRequest = new DtoAlunoRequest();
        alunoRequest.setNome("Aluno Teste");
        alunoRequest.setUsuario_id(1L);
        alunoRequest.setTurma_id(1L);

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(turmaRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.getById(1L)).thenReturn(usuario);
        when(turmaRepository.getById(1L)).thenReturn(mock(Turma.class));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(new Aluno());

        DtoAlunoResponse response = alunoService.criarAluno(alunoRequest);

        assertNotNull(response);
        verify(alunoRepository, times(1)).save(any(Aluno.class));
    }

    @Test
    public void testCriarAluno_UsuarioNaoExistente() {
        DtoAlunoRequest alunoRequest = new DtoAlunoRequest();
        alunoRequest.setUsuario_id(1L);

        when(usuarioRepository.existsById(1L)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            alunoService.criarAluno(alunoRequest);
        });
        assertEquals(HttpStatus.NOT_FOUND,
                exception.getStatusCode());
    }

    @Test
    public void testObterAlunoPorId_Success() {
        Long alunoId = 1L;
        Aluno aluno = new Aluno();
        aluno.setId(alunoId);

        when(alunoRepository.existsById(alunoId)).thenReturn(true);
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));

        DtoAlunoResponse response = alunoService.obterAlunoPorId(alunoId);

        assertNotNull(response);
        assertEquals(alunoId, response.getId());
    }

    @Test
    public void testObterAlunoPorId_AlunoNaoEncontrado() {
        Long alunoId = 1L;
        when(alunoRepository.existsById(alunoId)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            alunoService.obterAlunoPorId(alunoId);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    public void testAtualizarAluno_Success() {
        Long alunoId = 1L;
        DtoAlunoRequest alunoRequest = new DtoAlunoRequest();
        alunoRequest.setNome("Novo Nome");

        Aluno alunoExistente = new Aluno();
        alunoExistente.setId(alunoId);

        when(alunoRepository.existsById(alunoId)).thenReturn(true);
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(alunoExistente));

        DtoAlunoResponse response = alunoService.atualizarAluno(alunoId, alunoRequest);

        assertNotNull(response);
        assertEquals("Novo Nome", alunoExistente.getNome());
    }

    @Test
    public void testDeletarAluno_Success() {
        Long alunoId = 1L;
        when(alunoRepository.existsById(alunoId)).thenReturn(true);
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(new Aluno()));

        alunoService.deletarAluno(alunoId);

        verify(alunoRepository, times(1)).delete(any(Aluno.class));
    }

    @Test
    public void testDeletarAluno_AlunoNaoEncontrado() {
        Long alunoId = 1L;
        when(alunoRepository.existsById(alunoId)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            alunoService.deletarAluno(alunoId);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    public void testListarTodosAlunos_Success() {
        when(alunoRepository.findAll()).thenReturn(List.of(new Aluno()));

        List<DtoAlunoResponse> response = alunoService.listarTodosAlunos();

        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    public void testListarTodosAlunos_Vazio() {
        when(alunoRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            alunoService.listarTodosAlunos();
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

}
