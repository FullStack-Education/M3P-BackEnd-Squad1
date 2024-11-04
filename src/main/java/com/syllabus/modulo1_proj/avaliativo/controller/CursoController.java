package com.syllabus.modulo1_proj.avaliativo.controller;
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
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.DtoCurso;
import com.syllabus.modulo1_proj.avaliativo.entities.Curso;
import com.syllabus.modulo1_proj.avaliativo.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200", "https://viacep.com.br/ws/null/json/"})
@RestController
@RequestMapping("cursos")
@Tag(name = "Cursos", description = "Endpoints para gerenciamento de cursos")
public class CursoController {

    private static final Logger logger = LoggerFactory.getLogger(CursoController.class);
    private final CursoService service;
    private final MateriaService materiaService;

    public CursoController(CursoService service, MateriaService materiaService) {
        this.service = service;
        this.materiaService = materiaService;
    }

    @Operation(summary = "Adicionar novo curso", description = "Cadastra um novo curso no sistema", responses = {
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<Curso> adicionarCurso(@RequestBody @Valid DtoCurso novoCurso) {
        logger.info("Solicitado cadastro de novo Curso.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarCurso(novoCurso));
    }

    @Operation(summary = "Obter curso por ID", description = "Retorna os dados de um curso pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Dados do curso retornados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("{id}")
    public ResponseEntity<Curso> obterCursoPorId(@PathVariable Long id) {
        logger.info("Solicitado dados do Curso ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterCursoPorId(id));
    }

    @Operation(summary = "Listar todos os cursos", description = "Retorna uma lista de todos os cursos cadastrados", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        logger.info("Solicitada listagem completa de Cursos cadastrados no sistema.");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarCursos());
    }

    @Operation(summary = "Atualizar curso", description = "Atualiza os dados de um curso pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody @Valid DtoCurso curso) {
        logger.info("Solicitada alteração de Curso (PUT), ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarCurso(id, curso));
    }

    @Operation(summary = "Deletar curso", description = "Deleta um curso pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Curso deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        logger.info("Solicitada exclusão de Curso, ID {}.", id);
        service.deletarCurso(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Listar matérias por curso", description = "Retorna uma lista de matérias alocadas em um curso pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de matérias retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("{id_curso}/materias")
    public ResponseEntity<List<Materia>> listarMateriaPorCurso(@PathVariable Long id_curso) {
        logger.info("Solicitada a listagem de Matérias alocadas em um curso, de ID {}.", id_curso);
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.listarMateriaPorCurso(id_curso));
    }
}