package ro.msg.learning.shop.service;

import java.util.List;

public interface MessageSourceService {

    <T> String getMessage(String key, List<T> args);
}
