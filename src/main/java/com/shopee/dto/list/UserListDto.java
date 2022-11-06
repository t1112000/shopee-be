package com.shopee.dto.list;

import com.shopee.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserListDto {
    private int total = 0;

    private List<UserEntity> user_list = new ArrayList<>();

    public UserListDto(int total, List<UserEntity> user_list) {
        this.total = total;
        this.user_list = user_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<UserEntity> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<UserEntity> user_list) {
        this.user_list = user_list;
    }
}
