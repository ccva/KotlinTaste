package com.va.perfect.net.dao.postcode;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/10
 */

public class PostCodeInfo implements Serializable, Parcelable {


    /**
     * list : [{"PostNumber":"201322","Province":"上海市","City":"上海市","District":"南汇区","Address":"六灶镇鹿溪村"},{"PostNumber":"201300","Province":"上海市","City":"上海市","District":"南汇区","Address":"气象路"},{"PostNumber":"201315","Province":"上海市","City":"上海市","District":"南汇区","Address":"川周公路(2693弄3号(中侨学院对面))"},{"PostNumber":"201315","Province":"上海市","City":"上海市","District":"南汇区","Address":"川周公路(2878号)"},{"PostNumber":"201315","Province":"上海市","City":"上海市","District":"南汇区","Address":"川周公路(2928弄22号202室)"},{"PostNumber":"201315","Province":"上海市","City":"上海市","District":"南汇区","Address":"川周公路(2928弄25号702室)"},{"PostNumber":"201315","Province":"上海市","City":"上海市","District":"南汇区","Address":"川周公路(2号楼306室)"},{"PostNumber":"201315","Province":"上海市","City":"上海市","District":"南汇区","Address":"川周公路(3041号)"},{"PostNumber":"201315","Province":"上海市","City":"上海市","District":"南汇区","Address":"川周公路(3069号)"},{"PostNumber":"201315","Province":"上海市","City":"上海市","District":"南汇区","Address":"康巴路"},{"PostNumber":"201315","Province":"上海市","City":"上海市","District":"南汇区","Address":"康桥绿缘别墅"},{"PostNumber":"201300","Province":"上海市","City":"上海市","District":"南汇区","Address":"听潮路"},{"PostNumber":"201313","Province":"上海市","City":"上海市","District":"南汇区","Address":"万祥镇万耘路"},{"PostNumber":"201318","Province":"上海市","City":"上海市","District":"南汇区","Address":"椿樟街"},{"PostNumber":"201300","Province":"上海市","City":"上海市","District":"南汇区","Address":"南粮新村"},{"PostNumber":"201306","Province":"上海市","City":"上海市","District":"南汇区","Address":"渔港路"},{"PostNumber":"201324","Province":"上海市","City":"上海市","District":"南汇区","Address":"盐仓老街"},{"PostNumber":"201321","Province":"上海市","City":"上海市","District":"南汇区","Address":"周浦镇瓦屑镇北路"},{"PostNumber":"201301","Province":"上海市","City":"上海市","District":"南汇区","Address":"川南奉公路6156-6164号(双号)"},{"PostNumber":"201300","Province":"上海市","City":"上海市","District":"南汇区","Address":"川南奉公路(6116号)"}]
     * totalcount : 1724
     * totalpage : 87
     * currentpage : 1
     * pagesize : 20
     */

    private int totalcount;
    private int totalpage;
    private int currentpage;
    private int pagesize;
    private List<PostCodeBean> list;

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public List<PostCodeBean> getList() {
        return list;
    }

    public void setList(List<PostCodeBean> list) {
        this.list = list;
    }



    @Override
    public String toString() {
        return "PostCodeInfo{" +
                "totalcount=" + totalcount +
                ", totalpage=" + totalpage +
                ", currentpage=" + currentpage +
                ", pagesize=" + pagesize +
                ", list=" + list +
                '}';
    }

    public static class PostCodeBean implements Serializable, Parcelable {
        /**
         * PostNumber : 201322
         * Province : 上海市
         * City : 上海市
         * District : 南汇区
         * Address : 六灶镇鹿溪村
         */

        private String PostNumber;
        private String Province;
        private String City;
        private String District;
        private String Address;

        public String getPostNumber() {
            return PostNumber;
        }

        public void setPostNumber(String PostNumber) {
            this.PostNumber = PostNumber;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getDistrict() {
            return District;
        }

        public void setDistrict(String District) {
            this.District = District;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        @Override
        public String toString() {
            return "PostCodeBean{" +
                    "PostNumber='" + PostNumber + '\'' +
                    ", Province='" + Province + '\'' +
                    ", City='" + City + '\'' +
                    ", District='" + District + '\'' +
                    ", Address='" + Address + '\'' +
                    '}';
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.PostNumber);
            dest.writeString(this.Province);
            dest.writeString(this.City);
            dest.writeString(this.District);
            dest.writeString(this.Address);
        }

        public PostCodeBean() {
        }

        protected PostCodeBean(Parcel in) {
            this.PostNumber = in.readString();
            this.Province = in.readString();
            this.City = in.readString();
            this.District = in.readString();
            this.Address = in.readString();
        }

        public static final Creator<PostCodeBean> CREATOR = new Creator<PostCodeBean>() {
            @Override
            public PostCodeBean createFromParcel(Parcel source) {
                return new PostCodeBean(source);
            }

            @Override
            public PostCodeBean[] newArray(int size) {
                return new PostCodeBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.totalcount);
        dest.writeInt(this.totalpage);
        dest.writeInt(this.currentpage);
        dest.writeInt(this.pagesize);
        dest.writeList(this.list);
    }

    public PostCodeInfo() {
    }

    protected PostCodeInfo(Parcel in) {
        this.totalcount = in.readInt();
        this.totalpage = in.readInt();
        this.currentpage = in.readInt();
        this.pagesize = in.readInt();
        this.list = new ArrayList<PostCodeBean>();
        in.readList(this.list, PostCodeBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PostCodeInfo> CREATOR = new Parcelable.Creator<PostCodeInfo>() {
        @Override
        public PostCodeInfo createFromParcel(Parcel source) {
            return new PostCodeInfo(source);
        }

        @Override
        public PostCodeInfo[] newArray(int size) {
            return new PostCodeInfo[size];
        }
    };
}
