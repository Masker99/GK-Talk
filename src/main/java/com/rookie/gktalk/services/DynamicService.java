package com.rookie.gktalk.services;

import com.rookie.gktalk.dto.DynamicDto;
import com.rookie.gktalk.pojo.Dynamic;

import java.util.List;

public interface DynamicService {
    int addOneDynamic(Dynamic dynamic);
    int deleteOneDynamic(int dynamic_id);
    List<DynamicDto> getListOfDynamic();
}
