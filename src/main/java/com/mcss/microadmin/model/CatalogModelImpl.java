/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.BrandDAO;
import com.mcss.microadmin.data.dao.CountryDAO;
import com.mcss.microadmin.data.dao.PaymentMethodDAO;
import com.mcss.microadmin.data.dao.SectionDAO;
import com.mcss.microadmin.data.dao.StateDAO;
import com.mcss.microadmin.data.dao.StoreDAO;
import com.mcss.microadmin.data.dao.TaxTypeDAO;
import com.mcss.microadmin.data.dao.UnitDAO;
import com.mcss.microadmin.data.entity.Brand;
import com.mcss.microadmin.data.entity.Country;
import com.mcss.microadmin.data.entity.PaymentMethod;
import com.mcss.microadmin.data.entity.Section;
import com.mcss.microadmin.data.entity.State;
import com.mcss.microadmin.data.entity.Store;
import com.mcss.microadmin.data.entity.TaxType;
import com.mcss.microadmin.data.entity.Unit;
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatalogModelImpl implements CatalogModel {

    @Autowired
    CountryDAO countryDAO;

    @Autowired
    StateDAO stateDAO;

    @Autowired
    BrandDAO brandDAO;

    @Autowired
    PaymentMethodDAO paymentMethodDAO;

    @Autowired
    SectionDAO sectionDAO;

    @Autowired
    UnitDAO unitDAO;

    @Autowired
    TaxTypeDAO taxTypeDAO;

    @Autowired
    private StoreDAO storeDAO;

    @Override
    public Iterable<Country> countries() {
        return this.countryDAO.findAll();
    }

    @Override
    public Iterable<State> states() {
        return this.stateDAO.findAll();
    }

    @Override
    public Iterable<Brand> brands() {
        return this.brandDAO.findByActive(Status.ACTIVE);
    }

    @Override
    public Iterable<PaymentMethod> methods() {
        return this.paymentMethodDAO.findAll();
    }

    @Override
    public List<String> printers() {
        List<String> printers = new ArrayList<>();
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printer : printServices) {
            printers.add(printer.getName());
        }
        return printers;
    }

    @Override
    public Iterable<Section> sections() {
        return this.sectionDAO.findByActive(Boolean.TRUE);
    }

    @Override
    public Iterable<TaxType> taxTypes() {
        return this.taxTypeDAO.findByActive(Boolean.TRUE);
    }

    @Override
    public Iterable<Unit> units() {
        return this.unitDAO.findByActive(Boolean.TRUE);
    }

    @Override
    public Iterable<Store> stores() {
        return this.storeDAO.findByActive(Boolean.TRUE);
    }
}
