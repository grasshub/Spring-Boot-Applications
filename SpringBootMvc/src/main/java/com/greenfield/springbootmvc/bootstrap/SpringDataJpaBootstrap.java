package com.greenfield.springbootmvc.bootstrap;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;

import com.greenfield.springbootmvc.domain.Product;
import com.greenfield.springbootmvc.domain.Role;
import com.greenfield.springbootmvc.domain.User;
import com.greenfield.springbootmvc.service.ProductService;
import com.greenfield.springbootmvc.service.RoleService;
import com.greenfield.springbootmvc.service.UserService;

// Register this class as application listener to get invoked when Spring Boot application is initialized in order to
// load initial data from services through repositories to underline database

// Comment out the @Component annotation after the first time to execute application with spring.jpa.hibernate.ddl-auto=create to create database schema,
// after that, change from create to update in order to save database changes made from application and remove this initial injection to avoid the conflicts
//@Component
public class SpringDataJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private static final String USER = "user";
	private static final String USER_PASSWORD = "user";
	private static final String USER1 = "user1";
	private static final String USER_PASSWORD1 = "user1";
	private static final String USER_ROLE = "USER";
	private static final String ADMIN = "admin";
	private static final String ADMIN_PASSWORD = "admin";
	private static final String ADMIN1 = "admin1";
	private static final String ADMIN_PASSWORD1 = "admin1";
	private static final String ADMIN_ROLE = "ADMIN";
	private static final List<String> USER__NAMES = Arrays.asList("user", "user1");
	private static final List<String> ADMIN__NAMES = Arrays.asList("admin", "admin1");
	private static final String DESCRIPTION = "Spring Framework Guru Shirt";
	private static final String IMAGE_URL = "https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg";
	private static final double PRICE = 18.95;
	private static final String PRODUCT_ID = "235268845711068308";
	private static final String DESCRIPTION1 = "Spring Framework Guru Mug";
	private static final String IMAGE_URL1 = "https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg";
	private static final double PRICE1 = 5.95;
	private static final String PRODUCT_ID1 = "168639393495335947";

	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	private static final Logger logger = LoggerFactory.getLogger(SpringDataJpaBootstrap.class);

	private void loadProducts() {
		Product shirt = new Product();
		shirt.setDescription(DESCRIPTION);
		shirt.setPrice(BigDecimal.valueOf(PRICE));
		shirt.setImageUrl(IMAGE_URL);
		shirt.setProductId(PRODUCT_ID);
		productService.saveProduct(shirt);

		logger.info("Saved Shirt - id: " + shirt.getId());

		Product mug = new Product();
		mug.setDescription(DESCRIPTION1);
		mug.setPrice(BigDecimal.valueOf(PRICE1));
		mug.setImageUrl(IMAGE_URL1);
		mug.setProductId(PRODUCT_ID1);
		productService.saveProduct(mug);

		logger.info("Saved Mug - id:" + mug.getId());
	}

	private void loadUsers() {
		User user1 = new User();
		user1.setUsername(USER);
		user1.setPassword(USER_PASSWORD);
		userService.saveOrUpdate(user1);
		logger.info("Saved User: " + user1.getUsername());
		
		User user2 = new User();
		user2.setUsername(USER1);
		user2.setPassword(USER_PASSWORD1);
		userService.saveOrUpdate(user2);
		logger.info("Saved User: " + user2.getUsername());

		User user3 = new User();
		user3.setUsername(ADMIN);
		user3.setPassword(ADMIN_PASSWORD);
		userService.saveOrUpdate(user3);
		logger.info("Saved User: " + user3.getUsername());
		
		User user4 = new User();
		user4.setUsername(ADMIN1);
		user4.setPassword(ADMIN_PASSWORD1);
		userService.saveOrUpdate(user4);
		logger.info("Saved User: " + user4.getUsername());
	}

	private void loadRoles() {
		Role role = new Role();
		role.setRole(USER_ROLE);
		roleService.saveOrUpdate(role);
		logger.info("Saved Role: " + role.getRole());

		Role adminRole = new Role();
		adminRole.setRole(ADMIN_ROLE);
		roleService.saveOrUpdate(adminRole);
		logger.info("Saved Role: " + adminRole.getRole());
	}

	private void assignUsersToUserRole() {
		List<Role> roles = roleService.listAll();
		List<User> users = userService.listAll();

		roles.forEach(role -> {
			if (role.getRole().equalsIgnoreCase(USER_ROLE)) {
				users.forEach(user -> {
					if (USER__NAMES.contains(user.getUsername())) {
						user.addRole(role);
						logger.info("Add Role {} to User {}", role.getRole(), user.getUsername());
						userService.saveOrUpdate(user);
					}
				});
			}
		});
	}

	private void assignUsersToAdminRole() {
		List<Role> roles = roleService.listAll();
		List<User> users = userService.listAll();

		roles.forEach(role -> {
			if (role.getRole().equalsIgnoreCase(ADMIN_ROLE)) {
				users.forEach(user -> {
					if (ADMIN__NAMES.contains(user.getUsername())) {
						user.addRole(role);
						logger.info("Add Role {} to User {}", role.getRole(), user.getUsername());
						userService.saveOrUpdate(user);
					}
				});
			}
		});
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadProducts();
		loadUsers();
		loadRoles();
		assignUsersToUserRole();
		assignUsersToAdminRole();	
	}

}
