
package com.otcp.accounting.invoice.service;

import com.otcp.accounting.invoice.entity.Invoice;
import com.otcp.accounting.invoice.entity.InvoiceStatus;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {
    Invoice createInvoice(Invoice invoice);
    Invoice updateInvoice(Long id, Invoice invoice);
    Invoice getInvoiceById(Long id);
    List<Invoice> getAllInvoices();
    void deleteInvoice(Long id);
    List<Invoice> findInvoicesByCustomer(Long customerId);
    BigDecimal calculateTotal(Invoice invoice);
    List<Invoice> findInvoicesByStatus(InvoiceStatus status);
    List<Invoice> searchInvoices(String query);
    void markAsPaid(Long id);
    void cancelInvoice(Long id);
    BigDecimal calculateTax(Invoice invoice);
    List<Invoice> findOverdueInvoices();
    void sendInvoiceEmail(Long id);
    List<Invoice> getInvoicesWithinDateRange(LocalDate startDate, LocalDate endDate);
    Invoice uploadInvoiceFile(Long id, MultipartFile file);
    byte[] getInvoiceFile(Long id);
}
