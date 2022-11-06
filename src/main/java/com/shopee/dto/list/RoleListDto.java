package com.shopee.dto.list;

import com.shopee.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleListDto {
    private int total = 0;

    private List<RoleEntity> role_list = new ArrayList<>();

    public RoleListDto(int total, List<RoleEntity> role_list) {
        this.total = total;
        this.role_list = role_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RoleEntity> getRole_list() {
        return role_list;
    }

    public void setRole_list(List<RoleEntity> role_list) {
        this.role_list = role_list;
    }
}
