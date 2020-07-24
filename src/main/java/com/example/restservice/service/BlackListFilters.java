package com.example.restservice.service;
import com.example.restservice.Util;
import com.example.restservice.persistance.repositories.IpBlacklistRepository;
import com.example.restservice.persistance.repositories.UaBlacklistRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andris Magins
 * @created 22-Jul-20
 **/
@Service
public class BlackListFilters {

    final IpBlacklistRepository ipBlacklistRepository;
    final UaBlacklistRepository uaBlacklistRepository;

    List<String> ipBlacklist;
    List<String> uaBlacklist;


    public BlackListFilters(IpBlacklistRepository ipBlacklistRepository,
                            UaBlacklistRepository uaBlacklistRepository) {
        this.ipBlacklistRepository = ipBlacklistRepository;
        this.uaBlacklistRepository = uaBlacklistRepository;
    }

    public boolean isIpBlacklisted(HttpServletRequest request) {

        ipBlacklist = ipBlacklistRepository.findAll()
                .stream().map(item -> Util.longToIp(item.getIp())).collect(Collectors.toList());

        return ipBlacklist.stream().anyMatch(ip -> ip.equals(request.getRemoteAddr()));
    }

    public boolean isUserAgentBlacklisted(HttpServletRequest request){
        uaBlacklist = uaBlacklistRepository.findAll().
                stream().map(item -> item.getUa()).collect(Collectors.toList());
        return uaBlacklist.stream().anyMatch(userAgent -> userAgent.equals(request.getHeader("User-Agent")));
    }




}
