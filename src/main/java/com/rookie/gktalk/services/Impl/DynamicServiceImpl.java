package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.dto.DynamicDto;
import com.rookie.gktalk.mapper.DynamicMapper;
import com.rookie.gktalk.pojo.Dynamic;
import com.rookie.gktalk.services.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicServiceImpl implements DynamicService {
    @Autowired
    DynamicMapper dynamicMapper;

    @Override
    public int addOneDynamic(Dynamic dynamic){
       return dynamicMapper.addDynamic(dynamic);
    }

    @Override
    public int deleteOneDynamic(int dynamic_id){
        return dynamicMapper.deleteDynamic(dynamic_id);
    }

    @Override
    public List<DynamicDto> getListOfDynamic(){
        return dynamicMapper.getDynamics();
    }
}
