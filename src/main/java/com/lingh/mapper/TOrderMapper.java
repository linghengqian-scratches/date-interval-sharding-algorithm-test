package com.lingh.mapper;

import com.lingh.pojo.TOrderPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
public interface TOrderMapper {
    @Select("select * from t_order where create_time >= #{startTime} and create_time <= #{endTime} order by create_time desc")
    List<TOrderPO> selectAllByDataDateInLocalDateTime(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Select("select * from t_order where create_time >= #{startTime} and create_time <= #{endTime} order by create_time desc")
    List<TOrderPO> selectAllByDataDateInDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Select("select * from t_order where create_time >= #{startTime} and create_time <= #{endTime} order by create_time desc")
    List<TOrderPO> selectAllByDataDateInString(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
