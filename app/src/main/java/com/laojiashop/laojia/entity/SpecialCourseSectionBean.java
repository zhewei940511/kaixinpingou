package com.laojiashop.laojia.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * 实体类
 */
public class SpecialCourseSectionBean extends SectionEntity<HomePageBean.SpecialBean.GoodsListBean> {

    public SpecialCourseSectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }
    public SpecialCourseSectionBean(HomePageBean.SpecialBean.GoodsListBean bean) {
        super(bean);
    }

}
