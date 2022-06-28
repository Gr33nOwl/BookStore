package com.github.gr33nowl.bookstore.util;

import com.github.gr33nowl.bookstore.model.Genre;
import org.springframework.core.convert.converter.Converter;


public class StringToEnumConverter implements Converter<String, Genre> {

    @Override
    public Genre convert(String source) {
        try {
            return Genre.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
