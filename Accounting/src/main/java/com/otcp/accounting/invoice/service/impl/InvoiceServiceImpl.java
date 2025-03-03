
package com.otcp.accounting.invoice.service.impl;

import com.otcp.accounting.invoice.entity.Invoice;
import com.otcp.accounting.invoice.entity.InvoiceStatus;
import com.otcp.accounting.invoice.repository.InvoiceRepository;
import com.otcp.accounting.invoice.service.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public Invoice updateInvoice(Long id, Invoice invoice) {
        return null;
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return null;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return null;
    }

    @Override
    public void deleteInvoice(Long id) {

    }

    @Override
    public List<Invoice> findInvoicesByCustomer(Long customerId) {
        return null;
    }

    @Override
    public BigDecimal calculateTotal(Invoice invoice) {
        return null;
    }

    @Override
    public List<Invoice> findInvoicesByStatus(InvoiceStatus status) {
        return null;
    }

    @Override
    public List<Invoice> searchInvoices(String query) {
        return null;
    }

    @Override
    public void markAsPaid(Long id) {

    }

    @Override
    public void cancelInvoice(Long id) {

    }

    @Override
    public BigDecimal calculateTax(Invoice invoice) {
        return null;
    }

    @Override
    public List<Invoice> findOverdueInvoices() {
        return null;
    }

    @Override
    public void sendInvoiceEmail(Long id) {

    }

    @Override
    public List<Invoice> getInvoicesWithinDateRange(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public Invoice uploadInvoiceFile(Long id, MultipartFile file) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        try {
            invoice.setInvoiceFile(file.getBytes());
            invoice.setFileType(file.getContentType());
            return invoiceRepository.save(invoice);
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file");
        }
    }

    @Override
    public byte[] getInvoiceFile(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        return invoice.getInvoiceFile();
    }
}
