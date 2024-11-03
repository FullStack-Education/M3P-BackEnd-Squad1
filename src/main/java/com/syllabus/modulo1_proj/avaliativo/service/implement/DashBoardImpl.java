package com.syllabus.modulo1_proj.avaliativo.service.implement;

import com.syllabus.modulo1_proj.avaliativo.dtoUtils.DtoDashBoard;
import com.syllabus.modulo1_proj.avaliativo.service.AlunoService;
import com.syllabus.modulo1_proj.avaliativo.service.DashBoadService;
import com.syllabus.modulo1_proj.avaliativo.service.DocenteService;
import com.syllabus.modulo1_proj.avaliativo.service.TurmaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DashBoardImpl implements DashBoadService {

    private final AlunoService alunoService;
    private final DocenteService docenteService;
    private final TurmaService turmaService;
    private static final Logger logger = LoggerFactory.getLogger(DashBoardImpl.class);


    public DashBoardImpl(AlunoService alunoService, DocenteService docenteService, TurmaService turmaService) {
        this.alunoService = alunoService;
        this.docenteService = docenteService;
        this.turmaService = turmaService;
    }

    @Override
    public DtoDashBoard buscarEstatisticas() {
        logger.info("Coletando informações para dashboard...");
        var numeroTurmas = turmaService.listarTodasAsTurmas().size();
        var numeroAlunos = alunoService.listarTodosAlunos().size();
        var numeroDocentes = docenteService.listarTodosDocentes().size();

        logger.info("Retornando estatísticas (/dashboard).");
        return new DtoDashBoard(numeroAlunos, numeroDocentes, numeroTurmas);
    }
}
