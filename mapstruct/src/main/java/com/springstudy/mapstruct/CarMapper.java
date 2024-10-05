package com.springstudy.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class); // 2

    @Mapping(source = "kind", target = "type")
    CarDto carToCarDto(Car car);
}