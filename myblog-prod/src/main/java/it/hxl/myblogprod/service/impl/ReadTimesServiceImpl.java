package it.hxl.myblogprod.service.impl;

import it.hxl.myblogprod.entity.ReadTimes;
import it.hxl.myblogprod.mapper.ReadTimesMapper;
import it.hxl.myblogprod.service.ReadTimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("readTimesService")
public class ReadTimesServiceImpl implements ReadTimesService {

    @Autowired
    private ReadTimesMapper readTimesMapper;

    @Override
    public int insertReadTimes(ReadTimes readTimes) {
        ReadTimes readTimes1 = readTimesMapper.findReadTimeByMacAddr(readTimes.getMacAddr(), readTimes.getBlog().getId());
        int row = 0;
        if (readTimes1 == null){
            row = readTimesMapper.insertReadTimes(readTimes);
        }
        return row;
    }
}
