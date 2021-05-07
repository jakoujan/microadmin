/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.mcss.microadmin.data.entity.Brand;
import com.mcss.microadmin.data.entity.Country;
import com.mcss.microadmin.data.entity.PaymentMethod;
import com.mcss.microadmin.data.entity.Product;
import com.mcss.microadmin.data.entity.ProductType;
import com.mcss.microadmin.data.entity.Status;
import com.mcss.microadmin.data.entity.Section;
import com.mcss.microadmin.data.entity.State;
import com.mcss.microadmin.data.entity.Store;
import com.mcss.microadmin.data.entity.Supplier;
import com.mcss.microadmin.data.entity.Table;
import com.mcss.microadmin.data.entity.TaxType;
import com.mcss.microadmin.data.entity.Unit;
import com.mcss.microadmin.data.entity.User;
import com.mcss.microadmin.model.CatalogModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/catalogs/")
public class CatalogController {

    @Autowired
    CatalogModel catalogModel;

    @GetMapping(value = "states")
    public Iterable<State> states() {
        return this.catalogModel.states();
    }

    @GetMapping(value = "countries")
    public Iterable<Country> countries() {
        return this.catalogModel.countries();
    }

    @GetMapping(value = "brands")
    public Iterable<Brand> brands() {
        return this.catalogModel.brands();
    }

    @GetMapping(value = "methods")
    public Iterable<PaymentMethod> methods() {
        return this.catalogModel.methods();
    }
    
    @GetMapping(value = "sections")
    public Iterable<Section> sections() {
        return this.catalogModel.sections();
    }
    
    @GetMapping(value = "tax/types")
    public Iterable<TaxType> taxTypes() {
        return this.catalogModel.taxTypes();
    }
    
    @GetMapping(value = "units")
    public Iterable<Unit> units() {
        return this.catalogModel.units();
    }

    @GetMapping(value = "printers")
    public List<String> printers() {
        return this.catalogModel.printers();
    }
    
    @GetMapping(value = "stores")
    public Iterable<Store> strores() {
        return this.catalogModel.stores();
    }
    
    @GetMapping(value = "suppliers")
    public Iterable<Supplier> suppliers() {
        return this.catalogModel.suppliers();
    }
    
    @GetMapping(value = "product/types")
    public Iterable<ProductType> productTypes() {
        return this.catalogModel.productTypes();
    }
    
    @GetMapping(value = "products")
    public Iterable<Product> products() {
        return this.catalogModel.products();
    }
    
    @GetMapping(value = "status")
    public Iterable<Status> status() {
        return this.catalogModel.status();
    }
    
    @GetMapping(value = "tables")
    public Iterable<Table> tables() {
        return this.catalogModel.tables();
    }
    
    @GetMapping(value = "users")
    public Iterable<User> users() {
        return this.catalogModel.users();
    }

}
