package rxjava.simpledemo.com.entity;

/**
 * calssName
 * Created by wangwq.
 */

public class LoginEntity extends BaseEntity {

    private Integer cid;
    private String city;
    private String cityId;
    private String signType;

    //token
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "cid=" + cid +
                ", city='" + city + '\'' +
                ", cityId='" + cityId + '\'' +
                ", signType='" + signType + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
