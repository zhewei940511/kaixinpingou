package com.laojiashop.laojia.entity;

public enum  WithdRecordStatus {
    DAI_SHENHE(1),YI_BOHUI(2),DAI_DAKUAN(3),DA_KUANSHIBAI(4),YI_TUIHUI(5),JIE_SUANCHENGGONG(9);
    public int status;
    WithdRecordStatus(int status)
    {
        this.status=status;
    }
}
