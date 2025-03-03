
package com.otcp.accounting.tax.service;

import com.otcp.accounting.tax.entity.Tax;

import java.util.List;

public interface TaxService {
    Tax saveTax(Tax tax);
    Tax getTaxById(Long id);
    List<Tax> getAllTaxes();
    Tax updateTax(Long id, Tax tax);
    void deleteTax(Long id);
    List<Tax> searchTaxByName(String name);
    List<Tax> getActiveTaxes();
}
