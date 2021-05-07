/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcss.microadmin.data.entity.ItemKit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author edgar
 */
public class KitItemsConverter implements AttributeConverter<List<ItemKit>, String> {

    private static final Logger logger = LoggerFactory.getLogger(ModulesConverter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<ItemKit> list) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(list);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }

        return json;
    }

    @Override
    public List<ItemKit> convertToEntityAttribute(String json) {
        List<ItemKit> kits = null;
        try {
            kits = this.objectMapper.readValue(json, ArrayList.class);
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }
        return kits;
    }

}
