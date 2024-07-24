package com.npst.config.server.dao;

import com.npst.config.server.constants.CommonConstants;
import com.npst.config.server.dto.IndividualListDto;
import com.npst.config.server.dto.PropertiesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class PropertiesDao {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PropertiesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String,Object>> fetchGroupedList(String sqlQuery){
        log.debug(CommonConstants.SQL_QUERY_TEMPLATE, sqlQuery);
        return jdbcTemplate.queryForList(sqlQuery);
    }

    public List<Map<String, Object>> fetchDetailsList(String sqlQuery, IndividualListDto individualListDto) {
        log.debug(CommonConstants.SQL_QUERY_TEMPLATE, sqlQuery);
        return jdbcTemplate.queryForList(sqlQuery,individualListDto.getApplicationName(), individualListDto.getProfile(), individualListDto.getLabel());
    }

    public int  updateDetailById(String sqlQuery, PropertiesDto req) {
        log.debug(CommonConstants.SQL_QUERY_TEMPLATE, sqlQuery);
        return jdbcTemplate.update(sqlQuery,req.getValue(),req.getId(), req.getKey());
    }

    public int insertDetail(String sqlQuery, PropertiesDto propertiesDto) {
        log.debug(CommonConstants.SQL_QUERY_TEMPLATE, sqlQuery);
        // CREATED_ON, created_by, APPLICATION, PROFILE, LABEL, `KEY`, VALUE
        return jdbcTemplate.update(sqlQuery, LocalDateTime.now(ZoneId.of("Asia/Kolkata")),
                propertiesDto.getApplication(),
                propertiesDto.getProfile(), propertiesDto.getLabel(), propertiesDto.getKey(),
                propertiesDto.getValue());
    }
}
