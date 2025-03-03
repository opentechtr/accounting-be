
package com.otcp.accounting.tax.service.impl;

import com.otcp.accounting.tax.entity.Tax;
import com.otcp.accounting.tax.service.TaxService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxServiceImpl implements TaxService {

    @Override
    public Tax saveTax(Tax tax) {
        return null;
    }

    @Override
    public Tax getTaxById(Long id) {
        return null;
    }

    @Override
    public List<Tax> getAllTaxes() {
        return null;
    }

    @Override
    public Tax updateTax(Long id, Tax tax) {
        return null;
    }

    @Override
    public void deleteTax(Long id) {

    }

    @Override
    public List<Tax> searchTaxByName(String name) {
        return null;
    }

    @Override
    public List<Tax> getActiveTaxes() {
        return null;
    }
}
