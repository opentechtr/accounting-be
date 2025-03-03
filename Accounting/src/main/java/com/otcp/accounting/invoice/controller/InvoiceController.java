
package com.otcp.accounting.invoice.controller;

import com.otcp.accounting.invoice.entity.Invoice;
import com.otcp.accounting.invoice.entity.InvoiceStatus;
import com.otcp.accounting.invoice.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice created = invoiceService.createInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        Invoice updated = invoiceService.updateInvoice(id, invoice);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Invoice>> findInvoicesByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(invoiceService.findInvoicesByCustomer(customerId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Invoice>> findInvoicesByStatus(@PathVariable InvoiceStatus status) {
        return ResponseEntity.ok(invoiceService.findInvoicesByStatus(status));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Invoice>> searchInvoices(@RequestParam String query) {
        return ResponseEntity.ok(invoiceService.searchInvoices(query));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Void> markAsPaid(@PathVariable Long id) {
        invoiceService.markAsPaid(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelInvoice(@PathVariable Long id) {
        invoiceService.cancelInvoice(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Invoice>> findOverdueInvoices() {
        return ResponseEntity.ok(invoiceService.findOverdueInvoices());
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Invoice>> getInvoicesWithinDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(invoiceService.getInvoicesWithinDateRange(startDate, endDate));
    }

    @PostMapping("/{id}/send-email")
    public ResponseEntity<Void> sendInvoiceEmail(@PathVariable Long id) {
        invoiceService.sendInvoiceEmail(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/upload-file")
    public ResponseEntity<Invoice> uploadInvoiceFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Invoice updatedInvoice = invoiceService.uploadInvoiceFile(id, file);
        return ResponseEntity.ok(updatedInvoice);
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> getInvoiceFile(@PathVariable Long id) {
        byte[] file = invoiceService.getInvoiceFile(id);
        return ResponseEntity.ok().header("Content-Type", "application/octet-stream").body(file);
    }
}
