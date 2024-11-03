package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.entities.Materia;
import com.syllabus.modulo1_proj.avaliativo.service.MateriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "Curso Controller", tags = {"Curso"})
@RestController
@RequestMapping("/cursos")
public class CursoController {

    private static final Logger logger = LoggerFactory.getLogger(CursoController.class);
    private final CursoService service;
    private final MateriaService materiaService;

    public CursoController(CursoService service, MateriaService materiaService) {
        this.service = service;
        this.materiaService = materiaService;
    }

    @ApiOperation(value = "Adicionar um novo curso", response = Curso.class)
    @PostMapping
    public ResponseEntity<Curso> adicionarCurso(
            @ApiParam(value = "Dados do novo curso", required = true) @RequestBody @Valid DtoCurso novoCurso) {
        logger.info("Solicitado cadastro de novo Curso.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarCurso(novoCurso));
    }

    @ApiOperation(value = "Obter curso por ID", response = Curso.class)
    @GetMapping("{id}")
    public ResponseEntity<Curso> obterCursoPorId(
            @ApiParam(value = "ID do curso", required = true) @PathVariable Long id) {
        logger.info("Solicitado dados do Curso ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterCursoPorId(id));
    }

    @ApiOperation(value = "Listar todos os cursos", response = Curso.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        logger.info("Solicitada listagem completa de Cursos cadastrados no sistema.");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarCursos());
    }

    @ApiOperation(value = "Atualizar curso por ID", response = Curso.class)
    @PutMapping("{id}")
    public ResponseEntity<Curso> atualizarCurso(
            @ApiParam(value = "ID do curso", required = true) @PathVariable Long id,
            @ApiParam(value = "Dados atualizados do curso", required = true) @RequestBody @Valid DtoCurso curso) {
        logger.info("Solicitada alteração de Curso (PUT), ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarCurso(id, curso));
    }

    @ApiOperation(value = "Deletar curso por ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarCurso(
            @ApiParam(value = "ID do curso", required = true) @PathVariable Long id) {
        logger.info("Solicitada exclusão de Curso, ID {}.", id);
        service.deletarCurso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Listar matérias por curso", response = Materia.class, responseContainer = "List")
    @GetMapping("{id_curso}/materias")
    public ResponseEntity<List<Materia>> listarMateriaPorCurso(
            @ApiParam(value = "ID do curso", required = true) @PathVariable Long id_curso) {
        logger.info("Solicitada a listagem de Matérias alocadas em um curso, de ID {}.", id_curso);
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.listarMateriaPorCurso(id_curso));
    }
}