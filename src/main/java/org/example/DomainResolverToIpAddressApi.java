package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ipAddress")
public class DomainResolverToIpAddressApi {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<?> resolveDomainToIpAddress() {
        List<DomainResolverDto> ipAddressList = new ArrayList<DomainResolverDto>();
        String[] websites = {"www.google.com", "www.amazon.com", "www.facebook.com"};

        for (String website : websites) {
            DomainResolverDto domainResolverDto = new DomainResolverDto();
            domainResolverDto.setDomainName(website);
            try {
                InetAddress[] addresses = InetAddress.getAllByName(website);
                List<String> host = new ArrayList<String>();
                for (InetAddress address : addresses) {
                    host.add(address.getHostAddress());
                }
                domainResolverDto.setIpAddresses(host);
            } catch (UnknownHostException e) {
                System.err.println("Unable to resolve IP address for " + website);
            }

            ipAddressList.add(domainResolverDto);
        }

        Map response = new HashMap();
        response.put("domain", ipAddressList);
        return new ResponseEntity<Map>(response, HttpStatus.OK);
    }
}
