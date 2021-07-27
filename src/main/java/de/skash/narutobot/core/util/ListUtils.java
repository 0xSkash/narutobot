package de.skash.narutobot.core.util;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListUtils {
    public static <T> Type getTypeForList() {
        return new TypeToken<ArrayList<T>>() {
        }.getType();
    }
}
