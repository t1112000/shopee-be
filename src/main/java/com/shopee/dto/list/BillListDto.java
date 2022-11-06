package com.shopee.dto.list;

import com.shopee.entity.BillEntity;

import java.util.ArrayList;
import java.util.List;

public class BillListDto {
    private int total = 0;

    private List<BillEntity> bill_list = new ArrayList<>();

    public BillListDto(int total, List<BillEntity> bill_list) {
        this.total = total;
        this.bill_list = bill_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BillEntity> getBill_list() {
        return bill_list;
    }

    public void setBill_list(List<BillEntity> bill_list) {
        this.bill_list = bill_list;
    }
}
