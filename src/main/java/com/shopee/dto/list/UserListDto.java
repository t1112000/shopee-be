package com.shopee.dto.list;

import com.shopee.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserListDto {
    private int total = 0;

    private List<UserEntity> users = new ArrayList<>();

    public UserListDto(int total, List<UserEntity> users) {
        this.total = total;
        this.users = users;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
