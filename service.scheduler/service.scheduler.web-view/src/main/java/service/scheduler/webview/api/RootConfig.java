package service.scheduler.webview.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * is used to configure the application context created by ContextLoaderListener
 *
 */
@Configuration
@ComponentScan(basePackages={"service.scheduler"}, excludeFilters={ 
		@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
		}
)
public class RootConfig {

}
