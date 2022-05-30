package com.amandevel.spring.salonapi.utility;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public final class ObjectMapperUtil {

    private ObjectMapperUtil() {}

    private static ModelMapper modelMapper;
   static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
   }

   public static <T,O> O mapEntity (T entity, Class<O> outputClass) {
        return modelMapper.map(entity, outputClass);
   }

   public static <T,O> Collection<O> mapAllEntities (Collection<T> entities, Class<O> mappClass) {
        return entities.stream()
        .map((T entity) -> mapEntity(entity, mappClass))
        .collect(Collectors.toList());
        
        
   }

}


