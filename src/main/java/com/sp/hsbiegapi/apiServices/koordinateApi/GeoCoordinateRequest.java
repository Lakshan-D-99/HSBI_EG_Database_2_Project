package com.sp.hsbiegapi.apiServices.koordinateApi;

import java.util.Arrays;

public record GeoCoordinateRequest(String address) {

    public String[] getAddressData(){
        String[] addressData = new String[3];

        addressData = address.split(",");

        //System.out.println(Arrays.toString(addressData));

        return addressData;
    }
}
