package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement 
public class JPAConfiguration {
	
	/* @Bean: tornar o método conhecido pelo Spring. */
	@Bean
	/* Metodo de criação do EntityManager
	 */
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		/* Criar configuracao do EntityManager. O Spring já oferece um método para isso*/
		LocalContainerEntityManagerFactoryBean factoryBean = 
				new LocalContainerEntityManagerFactoryBean();
		
		/* Informar qual a implementacao do JPA estamos usando.
		 * É uma interface. Daremos o new em uma classe que o implementa.*/
		JpaVendorAdapter vendorAdapter =  new HibernateJpaVendorAdapter();
		
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		
		/* DataSource: Cuida das conexões, gerencia as conexões
		 * Configura o DB. 
		 */
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("s");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		factoryBean.setDataSource(dataSource);
		
		/* Configurar propriedades do hibernate */
		Properties props = new Properties();
		// Dialeto de comunicacao do hibernate com o mysql.
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		// Hibernate gerenciar o banco toda vez que mudarmos algo. 
		// Hbm: Hibernate Map (Mapeamento do JPA) 2ddl: para Data Definition Language.
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		// Imprimir o codigo sql usado
		props.setProperty("hibernate.show_sql", "true");
		
		factoryBean.setJpaProperties(props);
		
		//Informar onde estão as models (entities).
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");
		
		return factoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
