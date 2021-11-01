package com.bjworld.groupware.siteuser.service.impl;

import java.util.List;
import com.bjworld.groupware.common.vo.DefaultVO;

public class SiteUserVO extends DefaultVO {
    private String seq;
private String userName;
private String userId;
private String userPwd;
private String userEmail;
private String userPhone;
private String userSex;
private String updatePwdDate;
private String userAddressZonecode;
private String userAddress;
private String userAddressDetail;
private String regDate;


    public String getSeq() { return seq; }
public void setSeq(String seq) { this.seq = seq; }
public String getUserName() { return userName; }
public void setUserName(String userName) { this.userName = userName; }
public String getUserId() { return userId; }
public void setUserId(String userId) { this.userId = userId; }
public String getUserPwd() { return userPwd; }
public void setUserPwd(String userPwd) { this.userPwd = userPwd; }
public String getUserEmail() { return userEmail; }
public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
public String getUserPhone() { return userPhone; }
public void setUserPhone(String userPhone) { this.userPhone = userPhone; }
public String getUserSex() { return userSex; }
public void setUserSex(String userSex) { this.userSex = userSex; }
public String getUpdatePwdDate() { return updatePwdDate; }
public void setUpdatePwdDate(String updatePwdDate) { this.updatePwdDate = updatePwdDate; }
public String getUserAddressZonecode() { return userAddressZonecode; }
public void setUserAddressZonecode(String userAddressZonecode) { this.userAddressZonecode = userAddressZonecode; }
public String getUserAddress() { return userAddress; }
public void setUserAddress(String userAddress) { this.userAddress = userAddress; }
public String getUserAddressDetail() { return userAddressDetail; }
public void setUserAddressDetail(String userAddressDetail) { this.userAddressDetail = userAddressDetail; }
public String getRegDate() { return regDate; }
public void setRegDate(String regDate) { this.regDate = regDate; }

}
