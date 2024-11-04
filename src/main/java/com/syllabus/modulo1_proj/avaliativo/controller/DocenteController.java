package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.docente.DtoDocenteRequest;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.docente.DtoDocenteResponse;
import com.syllabus.modulo1_proj.avaliativo.service.DocenteService;
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
@RequestMapping("docentes")
@Tag(name = "Docentes", description = "Endpoints para gerenciamento de docentes")
public class DocenteController {

    private final DocenteService service;
    private static final Logger logger = LoggerFactory.getLogger(DocenteController.class);

    public DocenteController(DocenteService service) {
        this.service = service;
    }

    @Operation(summary = "Criar novo docente", description = "Cadastra um novo docente no sistema", responses = {
            @ApiResponse(responseCode = "201", description = "Docente criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoDocenteResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Impossível criar um docente para um usuário não cadastrado.", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<DtoDocenteResponse> criarDocente(@RequestBody @Valid DtoDocenteRequest novoDocente) {
        logger.info("Solicitado cadastro de novo Docente.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarDocente(novoDocente));
    }

    @Operation(summary = "Obter docente por ID", description = "Retorna os dados de um docente pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Dados do docente retornados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoDocenteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Docente não encontrado", content = @Content(schema = @Schema()))
    })
    @GetMapping("{id}")
    public ResponseEntity<DtoDocenteResponse> obterDocentePorId(@PathVariable Long id) {
        logger.info("Solicitado cadastro de Docente por ID, {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterDocentePorId(id));
    }

    @Operation(summary = "Atualizar docente", description = "Atualiza os dados de um docente pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Docente atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoDocenteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Docente não encontrado", content = @Content(schema = @Schema())),
    })
    @PutMapping("{id}")
    public ResponseEntity<DtoDocenteResponse> atualizarDocente(@PathVariable Long id, @RequestBody @Valid DtoDocenteRequest docente) {
        logger.info("Solicitado PUT de Docente, ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarDocente(id, docente));
    }

    @Operation(summary = "Deletar docente", description = "Deleta um docente pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Docente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Docente não encontrada para alteração.", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarDocente(@PathVariable Long id) {
        logger.info("Solicitada exclusão de cadastro de Docente, ID {}.", id);
        service.deletarDocente(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Listar todos os docentes", description = "Retorna uma lista de todos os docentes cadastrados", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de docentes retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoDocenteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Não há Docentes cadastrados.", content = @Content(schema = @Schema()))
    })
    @GetMapping
    public ResponseEntity<List<DtoDocenteResponse>> listarTodosDocentes() {
        logger.info("Solicitada listagem completa de Docentes cadastrados no sistema.");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodosDocentes());
    }
}
