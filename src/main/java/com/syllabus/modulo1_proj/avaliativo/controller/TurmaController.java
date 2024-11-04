package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.turma.DtoTurma;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.turma.DtoTurmaResponse;
import com.syllabus.modulo1_proj.avaliativo.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Turmas", description = "Endpoints para gerenciamento de turmas")
public class TurmaController {

    private final TurmaService service;
    private static final Logger logger = LoggerFactory.getLogger(TurmaController.class);

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @Operation(summary = "Criar nova turma", description = "Cadastra uma nova turma no sistema", responses = {
            @ApiResponse(responseCode = "201", description = "Turma criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoTurmaResponse.class))),
            @ApiResponse(responseCode = "404", description = "É necessário um Curso válido para se cadastrar uma Turma.", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "É necessário um Docente cadastrado para a Turma.", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<DtoTurmaResponse> criarTurma(@RequestBody @Valid DtoTurma novaTurma) {
        logger.info("Requerida a criação de nova Turma.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarTurma(novaTurma));
    }

    @Operation(summary = "Obter turma por ID", description = "Retorna os dados de uma turma pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Dados da turma retornados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoTurmaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada", content = @Content(schema = @Schema()))
    })
    @GetMapping("{id}")
    public ResponseEntity<DtoTurmaResponse> obterTurmaPorId(@PathVariable Long id) {
        logger.info("Solicitada Turma por ID, {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterTurmaPorId(id));
    }

    @Operation(summary = "Atualizar turma", description = "Atualiza os dados de uma turma pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Turma atualizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoTurmaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "A alteração de uma Turma exige um curso válido.", content = @Content(schema = @Schema()))
    })
    @PutMapping("{id}")
    public ResponseEntity<DtoTurmaResponse> atualizarTurma(@PathVariable Long id, @RequestBody @Valid DtoTurma turma) {
        logger.info("Solicitada atualização dos dados da Turma ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarTurma(id, turma));
    }

    @Operation(summary = "Deletar turma", description = "Deleta uma turma pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Turma deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Long id) {
        logger.info("Solicitada exclusão de turma, ID {}.", id);
        service.deletarTurma(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Listar todas as turmas", description = "Retorna uma lista de todas as turmas cadastradas", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de turmas retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoTurmaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Não há Turmas cadastradas.", content = @Content(schema = @Schema()))
    })
    @GetMapping
    public ResponseEntity<List<DtoTurmaResponse>> listarTodasAsTurmas() {
        logger.info("Solicitada listagem completa de Turmas cadastradas.");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodasAsTurmas());
    }
}
