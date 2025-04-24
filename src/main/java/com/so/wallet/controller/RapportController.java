package com.so.wallet.controller;

import com.so.wallet.entities.Rapport;
import com.so.wallet.service.RapportService;
import com.so.wallet.util.PdfExporter;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rapports")
public class RapportController {

    @Autowired
    private RapportService rapportService;

    @GetMapping
    public List<Rapport> getAllRapports() {
        return rapportService.getAllRapports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rapport> getRapportById(@PathVariable Long id) {
        Optional<Rapport> rapport = rapportService.getRapportById(id);
        return rapport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Rapport createRapport(@RequestBody Rapport rapport) {
        return rapportService.saveRapport(rapport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRapport(@PathVariable Long id) {
        rapportService.deleteRapport(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/export/pdf")
    public void exportRapportsToPDF(HttpServletResponse response) throws IOException, java.io.IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=rapports.pdf");

        List<String> lignes = rapportService.getAllRapports().stream()
                .map(r -> "Nom: " + r.getNom() + ", Description: " + r.getDescription())
                .toList();

        PdfExporter.exportRapports(response, lignes);
    }
}
