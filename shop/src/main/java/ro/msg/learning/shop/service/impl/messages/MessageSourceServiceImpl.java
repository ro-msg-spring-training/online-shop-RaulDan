package ro.msg.learning.shop.service.impl.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.service.MessageSourceService;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageSourceServiceImpl implements MessageSourceService {

    private final MessageSource messageSource;
    @Override
    public <T> String getMessage(String key, List<T> args) {
        return messageSource.getMessage(key, args.toArray(), Locale.ENGLISH);
    }

}
