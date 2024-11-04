package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.notas.DtoNota;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.notas.DtoNotaResponse;
import com.syllabus.modulo1_proj.avaliativo.service.NotaService;
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

@CrossOrigin(origins = { "http://localhost:4200", "https://viacep.com.br/ws/null/json/"})
@RestController
@RequestMapping("notas")
@Tag(name = "Notas", description = "Endpoints para gerenciamento de notas")
public class NotaController {

    private static final Logger logger = LoggerFactory.getLogger(NotaController.class);
    private final NotaService service;

    public NotaController(NotaService service) {
        this.service = service;
    }

    @Operation(summary = "Criar nova nota", description = "Cadastra uma nova nota no sistema", responses = {
            @ApiResponse(responseCode = "201", description = "Nota criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoNotaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Uma nota só pode ser atribuída a um aluno (role: ALUNO).", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado para se cadastrar uma nota.", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "É necessário haver uma matéria para se cadastrar uma Nota.", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Docente não encontrado para se cadastrar uma nota.", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<DtoNotaResponse> criarNota(@RequestBody @Valid DtoNota novaNota) {
        logger.info("Solicitada inserção de nova Nota.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarNota(novaNota));
    }

    @Operation(summary = "Obter nota por ID", description = "Retorna os dados de uma nota pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Dados da nota retornados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoNotaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada", content = @Content(schema = @Schema()))
    })
    @GetMapping("{id}")
    public ResponseEntity<DtoNotaResponse> obterNotaPorId(@PathVariable Long id) {
        logger.info("Solicitada Nota por ID, {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterNotaPorId(id));
    }

    @Operation(summary = "Atualizar nota", description = "Atualiza os dados de uma nota pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Nota atualizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoNotaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "A alteração de uma Nota requer uma matéria.", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "A alteração de uma Nota requer um Aluno cadastrado.", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "A alteração de uma Nota requer uma Docente cadastrado.", content = @Content(schema = @Schema()))
    })
    @PutMapping("{id}")
    public ResponseEntity<DtoNotaResponse> atualizarNota(@PathVariable Long id, @RequestBody @Valid DtoNota nota) {
        logger.info("Solicitada alteração de Nota, ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarNota(id, nota));
    }

    @Operation(summary = "Deletar nota", description = "Deleta uma nota pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Nota deletada com sucesso", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarNota(@PathVariable Long id) {
        logger.info("Solicitada exclusão de Nota, ID {}.", id);
        service.deletarNota(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
