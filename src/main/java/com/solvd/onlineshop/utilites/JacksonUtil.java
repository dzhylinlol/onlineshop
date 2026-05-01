package com.solvd.onlineshop.utilites;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

    public class JacksonUtil {

        private static final ObjectMapper mapper = new ObjectMapper();

        static {
            mapper.registerModule(new JavaTimeModule());
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }

        public static <T> void serialize(T object, String filePath) throws IOException {
            mapper.writeValue(new File(filePath), object);
        }

        public static <T> T deserialize(String filePath, Class<T> clazz) throws IOException {
            return mapper.readValue(new File(filePath), clazz);
        }
    }

