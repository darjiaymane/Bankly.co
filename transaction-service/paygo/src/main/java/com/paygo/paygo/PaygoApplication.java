package com.paygo.paygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication //TODO enableeurekaclient
@EnableFeignClients
public class PaygoApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaygoApplication.class, args);
	}
}
