package com.vitulc.productcontroller.dtos;

import com.vitulc.productcontroller.enums.UserRole;

public record RegisterResponseRecordDto(String username, String password, UserRole role) {
}
