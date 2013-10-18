package com.austynmahoney.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    private GsonUtil() {
        // static class
    }

    public static Gson getGsonInstance() {
        //return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        return new GsonBuilder().create();
    }
}
