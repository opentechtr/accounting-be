
package com.otcp.accounting.tax.controller;

import com.otcp.accounting.tax.entity.Tax;
import com.otcp.accounting.tax.service.TaxService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/taxes")
public class TaxController {
    private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @PostMapping
    public ResponseEntity<Tax> createTax(@Valid @RequestBody Tax tax) {
        return new ResponseEntity<>(taxService.saveTax(tax), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tax> getTaxById(@PathVariable Long id) {
        return ResponseEntity.ok(taxService.getTaxById(id));
    }

    @GetMapping
    public ResponseEntity<List<Tax>> getAllTaxes() {
        return ResponseEntity.ok(taxService.getAllTaxes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tax> updateTax(@PathVariable Long id, @Valid @RequestBody Tax tax) {
        return ResponseEntity.ok(taxService.updateTax(id, tax));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTax(@PathVariable Long id) {
        taxService.deleteTax(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Tax>> searchTaxByName(@RequestParam String name) {
        return ResponseEntity.ok(taxService.searchTaxByName(name));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Tax>> getActiveTaxes() {
        return ResponseEntity.ok(taxService.getActiveTaxes());
    }
}
