package com.application.proriv;


import com.application.proriv.domain.fake.FakeProducts;
import com.application.proriv.domain.model.Address;
import com.application.proriv.domain.model.customer.Customer;
import com.application.proriv.domain.model.user.Authority;
import com.application.proriv.domain.model.user.Role;
import com.application.proriv.domain.model.user.User;
import com.application.proriv.enums.UserStatus;
import com.application.proriv.repository.AddressRepository;
import com.application.proriv.repository.ProductRepository;
import com.application.proriv.service.customer.CustomerService;
import com.application.proriv.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ProrivApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProrivApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner(
      ProductRepository productRepository,
      UserService userService,
      AddressRepository addressRepository,
      CustomerService customerService,
      BCryptPasswordEncoder passwordEncoder
  ) {


    Authority sr = Authority.builder().name("system:read").build();
    Authority sw = Authority.builder().name("system:write").build();
    Authority ar = Authority.builder().name("admin:read").build();
    Authority aw = Authority.builder().name("admin:write").build();

    Set<Authority> adminAuth = Set.of(
        sr, sw, ar, aw);
    Set<Authority> moderatorAuth = Set.of(sr, sw);
    Set<Authority> userAuth = Set.of();


    Role adminRole = Role.builder()
        .name("ROLE_ADMIN")
        .authorities(adminAuth)
        .build();
    Role moderatorRole = Role.builder()
        .name("ROLE_MODERATOR")
        .authorities(moderatorAuth)
        .build();
    Role userRole = Role.builder()
        .name("ROLE_USER")
        .authorities(userAuth)
        .build();


    User tigran = User.builder()
        .firstName("Tigran")
        .lastName("Avushyan")
        .username("tigran")
        .password(passwordEncoder.encode("tigran"))
        .isEnabled(true)
        .phone(List.of("+79807152521"))
        .roles(Set.of(adminRole))
        .userStatus(UserStatus.ACTIVE)
        .build();


    User anush = User.builder()
        .firstName("Anush")
        .lastName("Avushyan")
        .username("anush")
        .password(passwordEncoder.encode("anush"))
        .isEnabled(true)
        .phone(List.of("+79807152521"))
        .roles(Set.of(userRole))
        .userStatus(UserStatus.ACTIVE)
        .build();

    User serob = User.builder()
        .firstName("Serob")
        .lastName("Avushyan")
        .username("serob")
        .password(passwordEncoder.encode("serob"))
        .isEnabled(true)
        .phone(List.of("+79807152521"))
        .roles(Set.of(moderatorRole))
        .userStatus(UserStatus.ACTIVE)
        .build();

    Address kn = Address.builder()
        .city("Обнинск")
        .region("Калужская область")
        .street("Ленина")
        .house("15Б")
        .build();

    Address of = Address.builder()
        .city("Обнинск")
        .region("Калужская область")
        .street("Гагарина")
        .house("74")
        .build();

    Customer oficerova = Customer.builder()
        .name("Офицерова")
        .phone(Set.of("+79109177778"))
        .address(kn)
        .build();

    Customer knjnaya = Customer.builder()
        .name("Книжная Находка")
        .phone(Set.of("+79109177720"))
        .address(kn)
        .build();


    return args -> {
      addressRepository.save(of);
      addressRepository.save(kn);

      productRepository.saveAll(FakeProducts.getProducts());

      customerService.createCustomer(oficerova);
      customerService.createCustomer(knjnaya);

      userService.saveAuthority(sr);
      userService.saveAuthority(sw);
      userService.saveAuthority(aw);
      userService.saveAuthority(ar);

      userService.saveRole(adminRole);
      userService.saveRole(moderatorRole);
      userService.saveRole(userRole);

      userService.save(tigran);
      userService.save(anush);
      userService.save(serob);

    };
  }


}
