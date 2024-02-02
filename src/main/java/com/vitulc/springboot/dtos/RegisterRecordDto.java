package com.vitulc.springboot.dtos;

import com.vitulc.springboot.enums.UserRole;

public record RegisterRecordDto(String username, String password, UserRole role) {
}
