package in.co.job.portal.configration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
public class JobPortalConfigration {

	@Bean(name = "viewResolver")
	public ViewResolver getViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	@Bean(name = "tilesConfigure")
	public TilesConfigurer getTilesConfigure() {
		TilesConfigurer tilesConfigure = new TilesConfigurer();
		tilesConfigure.setDefinitions("/WEB-INF/tiles.xml");
		return tilesConfigure;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	private @Autowired AutowireCapableBeanFactory beanFactory;

	/*
	 * @Bean public FilterRegistrationBean myFilter() { FilterRegistrationBean
	 * registration = new FilterRegistrationBean(); Filter myFilter = new
	 * MyFilter(); beanFactory.autowireBean(myFilter);
	 * registration.setFilter(myFilter); return registration; }
	 */

	/*
	 * @Bean public MultipartResolver multipartResolver() { return new
	 * CommonsMultipartResolver(); }
	 */
	
	/*
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(new LocaleChangeInterceptor());
	 * registry.addInterceptor(new
	 * ThemeChangeInterceptor()).addPathPatterns("/**").excludePathPatterns(
	 * "/ctl/**");
	 * 
	 * // multiple urls (same is possible for `exludePathPatterns`) }
	 */
	
	@Autowired
	private JobPortalIntercepter intercepter;

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(intercepter).addPathPatterns("/ctl/*");
	}

	
}
