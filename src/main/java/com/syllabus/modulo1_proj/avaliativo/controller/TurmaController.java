package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.turma.DtoTurma;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.turma.DtoTurmaResponse;
import com.syllabus.modulo1_proj.avaliativo.service.TurmaService;
import com.syllabus.modulo1_proj.avaliativo.service.implement.AlunoImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200", "https://viacep.com.br/ws/null/json/"})
@RestController
@RequestMapping("turmas")
@Api(value = "Turmas", tags = "Turmas")
public class TurmaController {

    private final TurmaService service;

    private static final Logger logger = LoggerFactory.getLogger(TurmaController.class);

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Criar uma nova turma")
    public ResponseEntity<DtoTurmaResponse> criarTurma(@RequestBody DtoTurma novaTurma) {
        logger.info("Requerida a criação de nova Turma.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarTurma(novaTurma));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Obter turma por ID")
    public ResponseEntity<DtoTurmaResponse> obterTurmaPorId(@PathVariable Long id) {
        logger.info("Solicitada Turma por ID, {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterTurmaPorId(id));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualizar turma por ID")
    public ResponseEntity<DtoTurmaResponse> atualizarTurma(@PathVariable Long id, @RequestBody @Valid DtoTurma turma) {
        logger.info("Solicitada atualização dos dados da Turma ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarTurma(id, turma));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletar turma por ID")
    public ResponseEntity<Void> deletarTurma(@PathVariable Long id) {
        logger.info("Solicitada exclusão de turma, ID {}.", id);
        service.deletarTurma(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ApiOperation(value = "Listar todas as turmas")
    public ResponseEntity<List<DtoTurmaResponse>> listarTodasAsTurmas() {
        logger.info("Solicitada listagem completa de Turmas cadastradas.");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodasAsTurmas());
    }
}
