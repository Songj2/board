package com.NC13.studyBoard.converter;

import com.NC13.studyBoard.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override //enum 을 db 에 어떤 값으로 넣을 것인지
    public String convertToDatabaseColumn(Role role) {
        if (Objects.isNull(role)){
            throw new NullPointerException("role이 널이다!!!!!!!!1");
        }
        return role.toString();
    }

    @Override // db 에서 읽힌 값에 따라 enum 과 매칭
    public Role convertToEntityAttribute(String dbData) {
        return Role.valueOf(dbData);
    }
}
