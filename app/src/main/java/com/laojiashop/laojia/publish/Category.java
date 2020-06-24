package com.laojiashop.laojia.publish;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by andy on 2018/7/4.
 */

public class Category implements Serializable  , Parcelable {
    @SerializedName("cate_name")
    private String cate_name;
    @SerializedName("children")
    private List<Category> children;
    @SerializedName("type")
    private int type;

    @SerializedName("cate_id")
    int cate_id;
    @SerializedName("cate_icon")
    String cate_icon;

    private boolean isSelect;
    private String mSortPath;


    protected Category(Parcel in) {
        cate_name = in.readString();
        children = in.createTypedArrayList(Category.CREATOR);
        type = in.readInt();
        cate_id = in.readInt();
        cate_icon = in.readString();
        isSelect = in.readByte() != 0;
        mSortPath = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_icon() {
        return cate_icon;
    }

    public void setCate_icon(String cate_icon) {
        this.cate_icon = cate_icon;
    }

    public int getType() {
        return type;
    }

    public Category(String cate_name, String cate_icon) {
        this.cate_name = cate_name;
        this.cate_icon = cate_icon;
    }

    public Category(String cate_name, List<Category> children, boolean isSelect, String mSortPath) {
        this.cate_name = cate_name;
        this.children = children;
        this.isSelect = isSelect;
        this.mSortPath = mSortPath;
    }



    public boolean isSelect() {
        return isSelect;
    }

    public String getmSortPath() {
        return mSortPath;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public void setmSortPath(String mSortPath) {
        this.mSortPath = mSortPath;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }


    public String getPickerViewText() {
        return cate_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return cate_id == category.cate_id &&
                isSelect == category.isSelect &&
                Objects.equals(cate_name, category.cate_name) &&
                Objects.equals(children, category.children) &&
                Objects.equals(cate_icon, category.cate_icon) &&
                Objects.equals(mSortPath, category.mSortPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cate_name, children, cate_id, cate_icon, isSelect, mSortPath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cate_name);
        dest.writeTypedList(children);
        dest.writeInt(type);
        dest.writeInt(cate_id);
        dest.writeString(cate_icon);
        dest.writeByte((byte) (isSelect ? 1 : 0));
        dest.writeString(mSortPath);
    }
}
