package cn.openjava.basic.format.annotation;

import cn.openjava.basic.crypto.properties.CryptoProperties;
import cn.openjava.basic.format.aspect.EncryptResultAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({EncryptResultAspect.class, CryptoProperties.class})
public @interface EnableEncryptResult {
}
