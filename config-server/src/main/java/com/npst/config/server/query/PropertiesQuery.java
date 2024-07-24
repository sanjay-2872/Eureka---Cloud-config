package com.npst.config.server.query;

public class PropertiesQuery {

    

    private PropertiesQuery(){
    }

    public static final String FETCH_REQUESTED_DETAILS = "SELECT ID, CREATED_ON, APPLICATION, PROFILE, LABEL, `KEY`, VALUE FROM PROPERTIES where APPLICATION = ? and PROFILE = ? and LABEL = ?";

    public static final String FETCH_GROUPED_LIST = "select count(*) as Total_Properties, APPLICATION, PROFILE, LABEL from PROPERTIES group by APPLICATION, PROFILE, LABEL";

    public static final String INSERT_DETAIL = "INSERT INTO PROPERTIES " +
            "(CREATED_ON, APPLICATION, PROFILE, LABEL, `KEY`, VALUE) VALUES " +
            "(?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_DETAIL_BY_ID = "update PROPERTIES set VALUE = ? where id = ? and `KEY` = ?";
}
