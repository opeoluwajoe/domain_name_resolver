package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableSwagger2
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DomainResolverToIpAddress {
    public static void main(String[] args) {
        String[] websites = {"www.google.com", "www.amazon.com", "www.facebook.com"};
        for (String website : websites) {
            try {
                InetAddress[] addresses = InetAddress.getAllByName(website);
                System.out.println("IP addresses for " + website + ":");

                for (InetAddress address : addresses) {
                    System.out.println(address.getHostAddress());
                }
            } catch (UnknownHostException e) {
                System.err.println("Unable to resolve IP address for " + website);
            }
        }
        SpringApplication.run(DomainResolverToIpAddress.class, args);
    }
}