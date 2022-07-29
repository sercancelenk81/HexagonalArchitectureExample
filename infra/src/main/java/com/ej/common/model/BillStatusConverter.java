package com.ej.common.model;

import com.ej.common.enums.BillStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Locale;

@Converter(autoApply = true)
public class BillStatusConverter implements AttributeConverter<BillStatus, String> {
    @Override
    public String convertToDatabaseColumn(BillStatus billStatus) {
        if(billStatus == null){
            return null;
        }
        return billStatus.name().toLowerCase();
    }

    @Override
    public BillStatus convertToEntityAttribute(String s) {
        if(s == null){
            return null;
        }
        return BillStatus.valueOf(s.toUpperCase());
    }
}
