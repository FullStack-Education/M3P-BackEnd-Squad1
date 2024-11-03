package com.syllabus.modulo1_proj.avaliativo.controller;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.usuario.DtoUsuarioRequest;
import com.syllabus.modulo1_proj.avaliativo.dtoUtils.usuario.DtoUsuarioResponse;
import com.syllabus.modulo1_proj.avaliativo.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "http://localhost:4200", "https://viacep.com.br/ws/null/json/" })
@Api(value = "Cadastro Controller", tags = {"Cadastro"})
@RestController
@RequestMapping("cadastro")
public class CadastroController {

    private static final Logger logger = LoggerFactory.getLogger(CadastroController.class);

    private final UsuarioService service;

    public CadastroController(UsuarioService service) {
        this.service = service;
    }

    @ApiOperation(value = "Criar um novo usuário", response = DtoUsuarioResponse.class)
    @PostMapping
    public ResponseEntity<DtoUsuarioResponse> criarUsuario(
            @ApiParam(value = "Dados do novo usuário", required = true) @RequestBody @Valid DtoUsuarioRequest novoUsuario) {
        logger.info("Solicitado cadastramento de NOVO Usuário.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.criarUsuario(novoUsuario));
    }
}
