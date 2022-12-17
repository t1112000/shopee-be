package com.shopee.dto.list;

import com.shopee.entity.BillEntity;

import java.util.ArrayList;
import java.util.List;

public class BillListDto {
    private int total = 0;

    private List<BillEntity> bills = new ArrayList<>();

    public BillListDto(int total, List<BillEntity> bills) {
        this.total = total;
        this.bills = bills;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BillEntity> getBills() {
        return bills;
    }

    public void setBills(List<BillEntity> bills) {
        this.bills = bills;
    }
}
