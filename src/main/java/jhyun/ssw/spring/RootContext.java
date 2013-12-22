package jhyun.ssw.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by jhyun on 13. 12. 23.
 */
@Import(value = {ShiroContext.class})
@Configuration
public class RootContext {
}
