package ro.msg.learning.shop.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageConvertor extends AbstractGenericHttpMessageConverter<Object> {

    protected final CsvConvertor<Object> csvConvertor;

    public MessageConvertor(){
        super(new MediaType("text","csv"));
        this.csvConvertor=new CsvConvertor<>();
    }

    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        List<Object> list;
        if(o instanceof List){
            list=new ArrayList<>((ArrayList<Object>) o);
        }
        else{
            list= Collections.singletonList(o);
        }
        csvConvertor.toCsv((Class<Object>) list.get(0).getClass(),list,outputMessage.getBody());
    }

    @Override
    protected Object readInternal(Class aclass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return csvConvertor.fromCsv(aclass,inputMessage.getBody());
    }

    @Override
    public Object read(Type type, Class contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(contextClass,inputMessage);
    }
}
