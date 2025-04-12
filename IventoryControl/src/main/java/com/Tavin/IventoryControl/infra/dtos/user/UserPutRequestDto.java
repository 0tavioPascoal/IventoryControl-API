package com.Tavin.IventoryControl.infra.dtos.user;

public record UserPutRequestDto(String id, String password, String email, String username) {
}
