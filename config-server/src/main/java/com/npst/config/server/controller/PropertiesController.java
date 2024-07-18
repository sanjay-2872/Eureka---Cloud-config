package com.npst.config.server.controller;

import com.npst.config.server.constants.ResponseConstants;
import com.npst.config.server.dto.IndividualListDto;
import com.npst.config.server.dto.PropertiesDto;
import com.npst.config.server.dto.ResponseMessage;
import com.npst.config.server.service.PropertiesService;
import com.npst.config.server.utils.ResponseUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/config/server/properties/")
public class PropertiesController {

    private final PropertiesService propertiesService;

    @Autowired
    public PropertiesController(PropertiesService propertiesService){
        this.propertiesService=propertiesService;
    }


    @GetMapping("/list/group/")
    public ResponseEntity<ResponseMessage> listAllGrouped(){
        log.info("Request received for Group All Properties List");
        List<Map<String,Object>> propertiesList = propertiesService.fetchGroupDetails();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.successResponseMsg(ResponseConstants.SUCCESS_OK,propertiesList));
    }


    @PostMapping(value = "/list/details/",consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseMessage> listAllDetails(@Valid @RequestBody IndividualListDto individualListDto){
        log.info("Request For List Properties Details :: {}", individualListDto);
        List<Map<String,Object>> propertiesList = propertiesService.fetchDetails(individualListDto);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.successResponseMsg(ResponseConstants.SUCCESS_OK,propertiesList));
    }

    @PostMapping(value = "/update/detail/",consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseMessage> addDetails(@Valid @RequestBody PropertiesDto propertiesDto){
        log.info("Request For Add Properties Detail :: {}", propertiesDto);
        Map<String, Object> resp = propertiesService.updateDetailById(propertiesDto);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.successResponseMsg(ResponseConstants.SUCCESS_OK,resp));
    }


}
