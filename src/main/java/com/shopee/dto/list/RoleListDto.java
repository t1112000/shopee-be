package com.shopee.dto.list;

import com.shopee.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleListDto {
    private int total = 0;

    private List<RoleEntity> roles = new ArrayList<>();

    public RoleListDto(int total, List<RoleEntity> roles) {
        this.total = total;
        this.roles = roles;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
