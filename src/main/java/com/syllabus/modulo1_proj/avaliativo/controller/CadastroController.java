package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.usuario.DtoUsuarioRequest;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.usuario.DtoUsuarioResponse;
import com.syllabus.modulo1_proj.avaliativo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "http://localhost:4200", "https://viacep.com.br/ws/null/json/"})
@RestController
@RequestMapping("cadastro")
@Tag(name = "Cadastro", description = "Endpoints para gerenciamento de cadastro de usuários")
public class CadastroController {

    private static final Logger logger = LoggerFactory.getLogger(CadastroController.class);
    private final UsuarioService service;

    public CadastroController(UsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Criar novo usuário", description = "Cadastra um novo usuário no sistema", responses = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoUsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Papel não condizente com as opções disponíveis (nível de acesso).", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Usuário já cadastrado!.", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<DtoUsuarioResponse> criarUsuario(@RequestBody @Valid DtoUsuarioRequest novoUsuario) {
        logger.info("Solicitado cadastramento de NOVO Usuário.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarUsuario(novoUsuario));
    }
}