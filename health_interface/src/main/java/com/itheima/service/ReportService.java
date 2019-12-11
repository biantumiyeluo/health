package com.itheima.service;

import java.util.Map;

public interface ReportService {
    //获取运营数据
    Map<String, Object> getBusinessReportData() throws Exception;

    Map<String, Object> exportBusinessReport() throws Exception;
}
