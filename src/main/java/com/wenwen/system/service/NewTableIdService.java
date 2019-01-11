package com.wenwen.system.service;

import com.wenwen.system.mapper.NewTableIdMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xiaoxinga
 * @date 2018/9/10 14:59
 * @since
 */
@Slf4j
@Service
public class NewTableIdService {

    final static DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Autowired
    private NewTableIdMapper newTableIdMapper;

    /**
     *
     * @param tableName  表名
     * @param columnName 列名
     * @param prefix     前缀
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getTableId(String tableName, String columnName, String prefix) {
        if (prefix.length() != 2) {
            log.info("前缀不等于两位数");
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime：" + localDateTime);
        String date = localDateTime.format(pattern);
        date = date.replace("-", "");
        String tableIdStart = prefix.toUpperCase() + date;
        String maxTableId = newTableIdMapper.getMaxTableId(tableName, columnName);
        int no = 0;
        if (!StringUtils.isEmpty(maxTableId) && maxTableId.substring(0,10).equals(tableIdStart)) {
            no = Integer.parseInt(maxTableId.substring(maxTableId.length() - 5, maxTableId.length()));
        }
        String tableIdEnd = String.format("%0" + 5 + "d", no + 1);
        String tableId = tableIdStart + tableIdEnd;
        if (no != 0) {
            newTableIdMapper.updateMaxId(tableId, tableName, columnName);
        } else {
            newTableIdMapper.insertMaxId(tableId, tableName, columnName);
        }
        return tableId;
    }

    public static void main(String[] args){
        NewTableIdService newTableIdService = new NewTableIdService();
        String tableId = newTableIdService.getTableId("student","id","st");
        System.out.println(tableId);
    }
}
