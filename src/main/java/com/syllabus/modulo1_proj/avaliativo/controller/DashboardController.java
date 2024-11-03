package com.syllabus.modulo1_proj.avaliativo.controller;

import com.syllabus.modulo1_proj.avaliativo.dtoUtils.DtoDashBoard;
import com.syllabus.modulo1_proj.avaliativo.service.DashBoadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@Api(value = "Dashboard Controller", tags = {"Dashboard"})
@RestController
@RequestMapping("dashboard")
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
    private final DashBoadService dashBoadService;

    public DashboardController(DashBoadService dashBoadService) {
        this.dashBoadService = dashBoadService;
    }

    @ApiOperation(value = "Listar estatísticas do dashboard", response = DtoDashBoard.class)
    @GetMapping
    public ResponseEntity<DtoDashBoard> listarCursos() {
        logger.info("Solicitada estatísticas para DashBoard.");
        return ResponseEntity.status(HttpStatus.OK).body(dashBoadService.buscarEstatisticas());
    }
}