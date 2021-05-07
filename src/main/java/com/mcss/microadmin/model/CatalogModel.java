/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

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
import java.util.List;

/**
 *
 * @author edgar
 */
public interface CatalogModel {

    public Iterable<Country> countries();

    public Iterable<State> states();
    
    public Iterable<Brand> brands();

    public Iterable<PaymentMethod> methods();
    
    public List<String> printers();

    public Iterable<Section> sections();

    public Iterable<TaxType> taxTypes();

    public Iterable<Unit> units();

    public Iterable<Store> stores();

    public Iterable<Supplier> suppliers();

    public Iterable<ProductType> productTypes();

    public Iterable<Product> products();
    
    public Iterable<Status> status();

    public Iterable<Table> tables();
    
    public Iterable<User> users();

}
