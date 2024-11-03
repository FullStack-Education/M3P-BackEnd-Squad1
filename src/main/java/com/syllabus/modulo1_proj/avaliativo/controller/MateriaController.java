package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.DtoMateria;
import com.syllabus.modulo1_proj.avaliativo.entities.Materia;
import com.syllabus.modulo1_proj.avaliativo.service.MateriaService;
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
@RequestMapping("materias")
@Api(value = "Materias", tags = "Materias")
public class MateriaController {

    private static final Logger logger = LoggerFactory.getLogger(MateriaController.class);

    private final MateriaService service;

    public MateriaController(MateriaService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Criar uma nova matéria")
    public ResponseEntity<Materia> criarMateria(@RequestBody @Valid DtoMateria novaMateria) {
        logger.info("Requerido o cadastro de nova Materia.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarMateria(novaMateria));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Obter matéria por ID")
    public ResponseEntity<Materia> obterMateriaPorId(@PathVariable Long id) {
        logger.info("Solicitada Matéria por ID, {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterMateriaPorId(id));
    }

    @GetMapping()
    @ApiOperation(value = "Listar todas as matérias")
    public ResponseEntity<List<Materia>> listarTodasMaterias() {
        logger.info("Solicitada listagem de materias");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodasMaterias());
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualizar matéria por ID")
    public ResponseEntity<Materia> atualizarMateria(@PathVariable Long id, @RequestBody @Valid DtoMateria materia) {
        logger.info("Requerida alteração da Materia ID {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarMateria(id, materia));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletar matéria por ID")
    public ResponseEntity<Void> deletarMateria(@PathVariable Long id) {
        logger.info("Solicitada a exclusão de Matéria, ID {}", id);
        service.deletarMateria(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
