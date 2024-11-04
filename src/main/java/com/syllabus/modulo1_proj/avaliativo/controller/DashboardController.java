package com.syllabus.modulo1_proj.avaliativo.controller;

import com.syllabus.modulo1_proj.avaliativo.dtoUtils.DtoDashBoard;
import com.syllabus.modulo1_proj.avaliativo.service.DashBoadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("dashboard")
@Tag(name = "Dashboard", description = "Endpoints para o Dashboard")
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
    private final DashBoadService dashBoadService;

    public DashboardController(DashBoadService dashBoadService) {
        this.dashBoadService = dashBoadService;
    }

    @Operation(summary = "Listar estatísticas do dashboard", description = "Retorna as estatísticas do dashboard", responses = {
            @ApiResponse(responseCode = "200", description = "Estatísticas retornadas com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoDashBoard.class)))
    })
    @GetMapping
    public ResponseEntity<DtoDashBoard> listarCursos() {
        logger.info("Solicitada estatísticas para DashBoard.");
        return ResponseEntity.status(HttpStatus.OK).body(dashBoadService.buscarEstatisticas());
    }
}