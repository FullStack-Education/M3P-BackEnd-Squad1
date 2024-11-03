package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.docente.DtoDocenteRequest;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.docente.DtoDocenteResponse;
import com.syllabus.modulo1_proj.avaliativo.service.DocenteService;
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

@CrossOrigin(origins = { "http://localhost:4200", "https://viacep.com.br/ws/null/json/"})
@RestController
@RequestMapping("docentes")
@Api(value = "DocenteController", tags = {"Docentes"})
public class DocenteController {

    private final DocenteService service;

    private static final Logger logger = LoggerFactory.getLogger(DocenteController.class);

    public DocenteController(DocenteService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Criar um novo docente", response = DtoDocenteResponse.class)
    public ResponseEntity<DtoDocenteResponse> criarDocente(
            @ApiParam(value = "Dados do novo docente", required = true) @RequestBody @Valid DtoDocenteRequest novoDocente) {
        logger.info("Solicitado cadastro de novo Docente.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarDocente(novoDocente));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Obter docente por ID", response = DtoDocenteResponse.class)
    public ResponseEntity<DtoDocenteResponse> obterDocentePorId(
            @ApiParam(value = "ID do docente", required = true) @PathVariable Long id) {
        logger.info("Solicitado cadastro de Docente por ID, {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterDocentePorId(id));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualizar docente", response = DtoDocenteResponse.class)
    public ResponseEntity<DtoDocenteResponse> atualizarDocente(
            @ApiParam(value = "ID do docente", required = true) @PathVariable Long id,
            @ApiParam(value = "Dados atualizados do docente", required = true) @RequestBody @Valid DtoDocenteRequest docente) {
        logger.info("Solicitado PUT de Docente, ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarDocente(id, docente));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletar docente")
    public ResponseEntity<Void> deletarDocente(
            @ApiParam(value = "ID do docente", required = true) @PathVariable Long id) {
        logger.info("Solicitada exclusão de cadastro de Docente, ID {}.", id);
        service.deletarDocente(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ApiOperation(value = "Listar todos os docentes", response = DtoDocenteResponse.class, responseContainer = "List")
    public ResponseEntity<List<DtoDocenteResponse>> listarTodosDocentes() {
        logger.info("Solicitada listagem completa de Docentes cadastrados no sistema.");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodosDocentes());
    }
}
