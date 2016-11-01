package com.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer{
	@Autowired
	private DataSource dataSource;
	@Bean
	    public FacebookConnectionFactory facebookConnectionFactory() {
	        FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory("565822240274576", "10b32f8b48aa3920e28de4edd75341c8");
	        facebookConnectionFactory.setScope("email");
	        return facebookConnectionFactory;
	    }

	    @Override
	    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
	        cfConfig.addConnectionFactory(facebookConnectionFactory());
	    }

	    @Override
	    public UserIdSource getUserIdSource() {
			return new UserIdSource() {
				@Override
				public String getUserId() {
					 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
					 if (authentication == null) {
					 throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
					 }
					 return authentication.getName();
				}
			};
		}
	    @Override
	    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
	        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
	    }
}



