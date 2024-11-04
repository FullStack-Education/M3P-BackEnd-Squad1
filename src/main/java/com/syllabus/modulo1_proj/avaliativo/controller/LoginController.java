package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.login.DtoTokenResponse;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.login.LoginDtoRequest;
import com.syllabus.modulo1_proj.avaliativo.entities.Usuario;
import com.syllabus.modulo1_proj.avaliativo.repository.AlunoRepository;
import com.syllabus.modulo1_proj.avaliativo.repository.DocenteRepository;
import com.syllabus.modulo1_proj.avaliativo.repository.UsuarioRepository;
import com.syllabus.modulo1_proj.avaliativo.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "http://localhost:4200", "https://viacep.com.br/ws/null/json/"})
@RestController
@RequestMapping("login")
@Tag(name = "Login", description = "Endpoints para autenticação de usuários")
public class LoginController {

    private final HttpServletRequest request;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final TokenService tokenService;
    private final AlunoRepository alunoRepository;
    private final DocenteRepository docenteRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(HttpServletRequest request, AuthenticationManager authenticationManager, UsuarioRepository repository, TokenService tokenService, AlunoRepository alunoRepository, DocenteRepository docenteRepository) {
        this.request = request;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
        this.alunoRepository = alunoRepository;
        this.docenteRepository = docenteRepository;
    }

    @Operation(summary = "Realizar login", description = "Autentica um usuário no sistema", responses = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoTokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<DtoTokenResponse> login(@RequestBody @Valid LoginDtoRequest login) {
        logger.info("Realizada requisição de login.");
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        logger.debug("Autenticação de usuário.");
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        Long usuarioId = ((Usuario) auth.getPrincipal()).getId();
        String nome;
        String entityId = "root";

        if (((Usuario) auth.getPrincipal()).getRole().toString().equals("ALUNO")) {
            nome = alunoRepository.buscarLogado(usuarioId).getNome();
            entityId = alunoRepository.buscarLogado(usuarioId).getId().toString();
        } else if (((Usuario) auth.getPrincipal()).getRole().toString().equals("PROFESSOR")) {
            nome = docenteRepository.buscarLogado(usuarioId).getNome();
            entityId = docenteRepository.buscarLogado(usuarioId).getId().toString();
        } else {
            nome = "Administrador (root)";
        }

        return ResponseEntity.ok(new DtoTokenResponse(token, ((Usuario) auth.getPrincipal()).getRole().toString(), nome, entityId));
    }
}