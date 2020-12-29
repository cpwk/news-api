package com.maidao.edu.news.springbootdemo.api.trainee.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
@Entity
@Table(name = "trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "education_type")
    private Byte educationType;

    @Column(name = "student_num")
    private String studentNum;

    @Column(name = "gender")
    private Byte gender;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "idnumber")
    private String idnumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "img")
    private String img;

    @Column(name = "username")
    private String username;

    @JSONField(serialize = false)
    @Column(name = "password")
    private String password;

    @Column(name = "in_school")
    private Byte inSchool;

    @Column(name = "term")
    private String term;

    @Column(name = "status")
    private Byte status;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "admission_at")
    private Long admissionAt;

    @Column(name = "signin_at")
    private Long signinAt;

    @Transient
    private String newpassword;

    public Trainee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getSigninAt() {
        return signinAt;
    }

    public void setSigninAt(Long signinAt) {
        this.signinAt = signinAt;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Byte getEducationType() {
        return educationType;
    }

    public void setEducationType(Byte educationType) {
        this.educationType = educationType;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getInSchool() {
        return inSchool;
    }

    public void setInSchool(Byte inSchool) {
        this.inSchool = inSchool;
    }

    public Long getAdmissionAt() {
        return admissionAt;
    }

    public void setAdmissionAt(Long admissionAt) {
        this.admissionAt = admissionAt;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
