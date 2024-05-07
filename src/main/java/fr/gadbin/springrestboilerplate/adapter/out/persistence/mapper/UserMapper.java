package fr.gadbin.springrestboilerplate.adapter.out.persistence.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.gadbin.springrestboilerplate.adapter.out.persistence.entity.UserEntity;
import fr.gadbin.springrestboilerplate.application.model.User;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    User toDto(UserEntity user);

    List<User> toListDto(List<UserEntity> users);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    UserEntity toEntity(User client);
}