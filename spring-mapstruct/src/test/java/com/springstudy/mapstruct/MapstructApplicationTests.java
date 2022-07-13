package com.springstudy.mapstruct;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MapstructApplicationTests {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Test
    void contextLoads() {
    }

    @Test
    public void shouldMapCarToDto() {

        //given
        Car car = new Car(1L, "carName", "carType");

        //when
        CarDto carDto = INSTANCE.carToCarDto(car);

        //then
        assertThat(carDto).isNotNull();
        assertThat(carDto.getId()).isEqualTo(1L);
        assertThat(carDto.getName()).isEqualTo("carName");
        assertThat(carDto.getType()).isEqualTo("carType");
    }
}
