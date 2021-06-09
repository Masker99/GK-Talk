package com.rookie.gktalk.mapper;

import com.rookie.gktalk.dto.DynamicDto;
import com.rookie.gktalk.pojo.Dynamic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicMapper {
    int addDynamic(Dynamic dynamic);
    int deleteDynamic(int dynamic_id);
    int updateDynamic(Dynamic dynamic);
    List<DynamicDto> getDynamics();
}
