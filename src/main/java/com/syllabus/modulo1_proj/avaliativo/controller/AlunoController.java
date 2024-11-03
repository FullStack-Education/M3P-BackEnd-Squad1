package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.DtoNotaFinal;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.aluno.DtoAlunoRequest;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.aluno.DtoAlunoResponse;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.notas.DtoNotaResponse;
import com.syllabus.modulo1_proj.avaliativo.service.AlunoService;
import com.syllabus.modulo1_proj.avaliativo.service.NotaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(value = "Aluno Controller", tags = {"Aluno"})
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private static final Logger logger = LoggerFactory.getLogger(AlunoController.class);
    private final AlunoService service;
    private final NotaService notaService;

    public AlunoController(AlunoService service, NotaService notaService) {
        this.service = service;
        this.notaService = notaService;
    }

    @ApiOperation(value = "Criar um novo aluno", response = DtoAlunoResponse.class)
    @PostMapping
    public ResponseEntity<DtoAlunoResponse> criarAluno(
            @ApiParam(value = "Dados do novo aluno", required = true) @RequestBody @Valid DtoAlunoRequest novoAluno) {
        logger.info("Solicitado o cadastramento de novo Aluno.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarAluno(novoAluno));
    }

    @ApiOperation(value = "Obter aluno por ID", response = DtoAlunoResponse.class)
    @GetMapping("{id}")
    public ResponseEntity<DtoAlunoResponse> obterAlunoPorId(
            @ApiParam(value = "ID do aluno", required = true) @PathVariable Long id) {
        logger.info("Solicitado dados do Aluno de ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterAlunoPorId(id));
    }

    @ApiOperation(value = "Atualizar aluno por ID", response = DtoAlunoResponse.class)
    @PutMapping("{id}")
    public ResponseEntity<DtoAlunoResponse> atualizarAluno(
            @ApiParam(value = "ID do aluno", required = true) @PathVariable Long id,
            @ApiParam(value = "Dados atualizados do aluno", required = true) @RequestBody @Valid DtoAlunoRequest aluno) {
        logger.info("Solicitada alteração cadastral (PUT), Aluno ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarAluno(id, aluno));
    }

    @ApiOperation(value = "Deletar aluno por ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAluno(
            @ApiParam(value = "ID do aluno", required = true) @PathVariable Long id) {
        logger.info("Solicitada exclusão de Aluno (DELETE), ID {}.", id);
        service.deletarAluno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Listar todos os alunos", response = DtoAlunoResponse.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity<List<DtoAlunoResponse>> listarTodosAlunos() {
        logger.info("Solicitada listagem completa de alunos cadastrados no sistema.");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodosAlunos());
    }

    @ApiOperation(value = "Listar notas por aluno", response = DtoNotaResponse.class, responseContainer = "List")
    @GetMapping("{id_aluno}/notas")
    public ResponseEntity<List<DtoNotaResponse>> listarNotasPorAluno(
            @ApiParam(value = "ID do aluno", required = true) @PathVariable Long id_aluno) {
        logger.info("Solicitada listagem completa de Notas de Aluno, cadastro ID {}.", id_aluno);
        return ResponseEntity.status(HttpStatus.OK).body(notaService.listarNotasPorAluno(id_aluno));
    }

    @ApiOperation(value = "Obter pontuação total do aluno", response = DtoNotaFinal.class)
    @GetMapping("{id}/pontuacao")
    public ResponseEntity<DtoNotaFinal> pontuacaoAluno(
            @ApiParam(value = "ID do aluno", required = true) @PathVariable Long id) {
        logger.info("Solicitada a pontuação TOTAL do Aluno, cadastro ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.pontuacaoAluno(id));
    }
}