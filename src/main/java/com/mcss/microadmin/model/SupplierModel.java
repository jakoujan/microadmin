package com.mcss.microadmin.model;

import com.mcss.microadmin.data.entity.Supplier;
import com.mcss.microadmin.data.filter.SupplierFilter;
import com.ispc.slibrary.dto.Response;

public interface SupplierModel {

    public Response getSuppliers(SupplierFilter filter);

    public Response save(Supplier supplier);

    public Response findActives();
}
