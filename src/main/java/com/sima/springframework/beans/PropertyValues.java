package com.sima.springframework.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addProperty(PropertyValue pv) {
        propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues(){
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getProperty(String name){
        for (PropertyValue pv : propertyValueList){
            if (pv.getName().equals(name)){
                return pv;
            }
        }

        return null;
    }
}
