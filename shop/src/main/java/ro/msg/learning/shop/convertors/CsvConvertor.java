package ro.msg.learning.shop.convertors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvConvertor<T> {

    CsvMapper mapper=new CsvMapper();

    public List<T> fromCsv(Class<T> importedClass, InputStream csvInputStream){
        CsvSchema schema=this.mapper.schemaFor(importedClass);
        try {
            MappingIterator<T> iterator=this.mapper.readerFor(importedClass).with(schema).readValues(csvInputStream);
            return iterator.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void toCsv(Class<T> exportedClass, List<T> items, OutputStream csvOutputStream){
        Field[] fields=exportedClass.getDeclaredFields();
        CsvSchema schema=this.mapper.typedSchemaFor(exportedClass);
        ObjectWriter objectWriter=this.mapper.writer(schema.withLineSeparator("\n"));
        List<String> headers= Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
        StringBuilder csvValue=new StringBuilder();
        try {
            csvValue.append(objectWriter.writeValueAsString(headers));
            for(T item:items){
                csvValue.append(objectWriter.writeValueAsString(item));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
