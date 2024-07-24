package com.npst.config.server.service;

import com.npst.config.server.annotations.AuditLog;
import com.npst.config.server.dao.PropertiesDao;
import com.npst.config.server.dto.IndividualListDto;
import com.npst.config.server.dto.PropertiesDto;
import com.npst.config.server.query.PropertiesQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PropertiesService {

    private final PropertiesDao propertiesDao;

    private final DiscoveryClient discoveryClient;



    @Autowired
    public PropertiesService(PropertiesDao propertiesDao,DiscoveryClient discoveryClient){
        this.propertiesDao = propertiesDao;
        this.discoveryClient=discoveryClient;
    }

    @AuditLog
    public List<Map<String,Object>> fetchGroupDetails() {
//        throw new RuntimeException();
        List<Map<String,Object>> resp = propertiesDao.fetchGroupedList(PropertiesQuery.FETCH_GROUPED_LIST);
        log.info("Fetch Properties Group Details :: {}", resp);
        return resp;
    }


    @AuditLog
    public Map<String,Object> fetchDetails(IndividualListDto individualListDto) {

        Map<String,Object> resp = new HashMap<>();
        List<Map<String,Object>> appPropDetails = propertiesDao.fetchDetailsList(PropertiesQuery.FETCH_REQUESTED_DETAILS, individualListDto);
        resp.put("appPropDetails", appPropDetails);
        Map<String, Object> properties = appPropDetails.stream()
                .collect(Collectors.toMap(
                        e -> String.valueOf(e.get("KEY")),
                        e -> e.get("Value")
                ));
        //appPropDetails.add(properties);
        resp.put("properties", properties);
        log.info("Fetch Properties Details :: {}", appPropDetails);
        return resp;
    }

    public Map<String,Object> updateDetailById(PropertiesDto req) {
        int affectedRows = propertiesDao.updateDetailById(PropertiesQuery.UPDATE_DETAIL_BY_ID,req);
        log.info("Affected Rows :: {}",affectedRows);
        Map<String,Object> respDataList = new HashMap<>();
        boolean isAffectedOne = affectedRows==1;
        if(isAffectedOne){
            this.discoveryClient.getInstances(req.getApplication()).stream().forEach(e->{
                String uri = e.getUri().toString();
                log.info("{} Application - uri : {} ",req.getApplication(),uri);

                RestTemplate restTemplate= new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
                ResponseEntity<List> responseEntity = restTemplate.exchange(uri+"/actuator/refresh", HttpMethod.POST,requestEntity, List.class);
                List respBody = responseEntity.getBody();
                log.info("refresh Response :: {}",respBody);
                respDataList.put(uri,respBody);
            });
        }
        return respDataList;
    }

    public int addDetails(PropertiesDto propertiesDto) {
       int resp = propertiesDao.insertDetail(PropertiesQuery.INSERT_DETAIL, propertiesDto);
        log.info("Inserted Properties Details affected data :: {}", resp);
        return resp;
    }
}
