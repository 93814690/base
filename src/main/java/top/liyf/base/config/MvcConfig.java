package top.liyf.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.liyf.base.util.LocalDateTimeUtils;

import java.time.LocalDateTime;

/**
 * @author liyf
 * Created in 2020-05-02
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateTimeConverter());
    }

    public static class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            return LocalDateTimeUtils.convert(source.trim());
        }
    }
}
