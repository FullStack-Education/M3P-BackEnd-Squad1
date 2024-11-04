package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.DtoNotaFinal;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.aluno.DtoAlunoRequest;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.aluno.DtoAlunoResponse;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.notas.DtoNotaResponse;
import com.syllabus.modulo1_proj.avaliativo.service.AlunoService;
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
import java.util.List;


@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Endpoints para gerenciamento de alunos")
public class AlunoController {

    private static final Logger logger = LoggerFactory.getLogger(AlunoController.class);
    private final AlunoService service;
    private final NotaService notaService;

    public AlunoController(AlunoService service, NotaService notaService) {
        this.service = service;
        this.notaService = notaService;
    }

    @Operation(summary = "Criar novo aluno", description = "Cadastra um novo aluno no sistema", responses = {
            @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoAlunoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Impossível criar um Aluno sem um usuário não cadastrado.", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Impossível criar um Aluno sem o vincular a uma Turma existente/válida.", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<DtoAlunoResponse> criarAluno(@RequestBody @Valid DtoAlunoRequest novoAluno) {
        logger.info("Solicitado o cadastramento de novo Aluno.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarAluno(novoAluno));
    }

    @Operation(summary = "Obter aluno por ID", description = "Retorna os dados de um aluno pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Dados do aluno retornados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoAlunoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content(schema = @Schema()))
    })
    @GetMapping("{id}")
    public ResponseEntity<DtoAlunoResponse> obterAlunoPorId(@PathVariable Long id) {
        logger.info("Solicitado dados do Aluno de ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterAlunoPorId(id));
    }

    @Operation(summary = "Atualizar aluno", description = "Atualiza os dados de um aluno pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoAlunoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado para alteração.", content = @Content(schema = @Schema()))
    })
    @PutMapping("{id}")
    public ResponseEntity<DtoAlunoResponse> atualizarAluno(@PathVariable Long id, @RequestBody @Valid DtoAlunoRequest aluno) {
        logger.info("Solicitada alteração cadastral (PUT), Aluno ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarAluno(id, aluno));
    }

    @Operation(summary = "Deletar aluno", description = "Deleta um aluno pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Aluno deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        logger.info("Solicitada exclusão de Aluno (DELETE), ID {}.", id);
        service.deletarAluno(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Listar todos os alunos", description = "Retorna uma lista de todos os alunos cadastrados", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de alunos retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoAlunoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Não há Alunos cadastrados.", content = @Content(schema = @Schema()))
    })
    @GetMapping
    public ResponseEntity<List<DtoAlunoResponse>> listarTodosAlunos() {
        logger.info("Solicitada listagem completa de alunos cadastrados no sistema.");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodosAlunos());
    }

    @Operation(summary = "Listar notas por aluno", description = "Retorna uma lista de notas de um aluno pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de notas retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoNotaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Disponível apenas a pontuação do usuário/aluno logado.", content = @Content(schema = @Schema()))
    })
    @GetMapping("{id_aluno}/notas")
    public ResponseEntity<List<DtoNotaResponse>> listarNotasPorAluno(@PathVariable Long id_aluno) {
        logger.info("Solicitada listagem completa de Notas de Aluno, cadastro ID {}.", id_aluno);
        return ResponseEntity.status(HttpStatus.OK).body(notaService.listarNotasPorAluno(id_aluno));
    }

    @Operation(summary = "Obter pontuação total do aluno", description = "Retorna a pontuação total de um aluno pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Pontuação retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoNotaFinal.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Não há notas cadastradas.", content = @Content(schema = @Schema()))
    })
    @GetMapping("{id}/pontuacao")
    public ResponseEntity<DtoNotaFinal> pontuacaoAluno(@PathVariable Long id) {
        logger.info("Solicitada a pontuação TOTAL do Aluno, cadastro ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.pontuacaoAluno(id));
    }
}