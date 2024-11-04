package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.DtoMateria;
import com.syllabus.modulo1_proj.avaliativo.entities.Materia;
import com.syllabus.modulo1_proj.avaliativo.service.MateriaService;
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
@RequestMapping("materias")
@Tag(name = "Materias", description = "Endpoints para gerenciamento de matérias")
public class MateriaController {

    private static final Logger logger = LoggerFactory.getLogger(MateriaController.class);
    private final MateriaService service;

    public MateriaController(MateriaService service) {
        this.service = service;
    }

    @Operation(summary = "Criar nova matéria", description = "Cadastra uma nova matéria no sistema", responses = {
            @ApiResponse(responseCode = "201", description = "Matéria criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<Materia> criarMateria(@RequestBody @Valid DtoMateria novaMateria) {
        logger.info("Requerido o cadastro de nova Materia.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarMateria(novaMateria));
    }

    @Operation(summary = "Obter matéria por ID", description = "Retorna os dados de uma matéria pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Dados da matéria retornados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "404", description = "Matéria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("{id}")
    public ResponseEntity<Materia> obterMateriaPorId(@PathVariable Long id) {
        logger.info("Solicitada Matéria por ID, {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterMateriaPorId(id));
    }

    @Operation(summary = "Listar todas as matérias", description = "Retorna uma lista de todas as matérias cadastradas", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de matérias retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<Materia>> listarTodasMaterias() {
        logger.info("Solicitada listagem de materias");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodasMaterias());
    }

    @Operation(summary = "Atualizar matéria", description = "Atualiza os dados de uma matéria pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Matéria atualizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "404", description = "Matéria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("{id}")
    public ResponseEntity<Materia> atualizarMateria(@PathVariable Long id, @RequestBody @Valid DtoMateria materia) {
        logger.info("Requerida alteração da Materia ID {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarMateria(id, materia));
    }

    @Operation(summary = "Deletar matéria", description = "Deleta uma matéria pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Matéria deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Matéria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarMateria(@PathVariable Long id) {
        logger.info("Solicitada a exclusão de Matéria, ID {}", id);
        service.deletarMateria(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
