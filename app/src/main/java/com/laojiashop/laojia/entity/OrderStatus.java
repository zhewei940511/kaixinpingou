package com.laojiashop.laojia.entity;

public enum OrderStatus {
    DAI_FU_KUAN(1), YI_TUI_KUAN(2), DAI_SHOU_HUO(3), YI_QIAN_SHOU(4),GUO_QI(91);
    public int status;

    OrderStatus(int status) {
        this.status = status;
    }
}
