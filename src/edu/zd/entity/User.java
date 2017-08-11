package edu.zd.entity;
// default package

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_USER"
    ,schema="LOGISTIC"
)

public class User  implements java.io.Serializable {


    // Fields    

     private String id;
     private String username;
     private String password;
     private Double salary;
     private Timestamp birthday;
     private String sex;
     private String company;
     private String telphone;
     private String msg;


    // Constructors

    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(String username, String password, Double salary, Timestamp birthday, String sex, String company, String telphone, String msg) {
        this.username = username;
        this.password = password;
        this.salary = salary;
        this.birthday = birthday;
        this.sex = sex;
        this.company = company;
        this.telphone = telphone;
        this.msg = msg;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    
    @Column(name="ID", unique=true, nullable=false, length=32)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="USERNAME", length=32)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="PASSWORD", length=32)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="SALARY", precision=126, scale=0)

    public Double getSalary() {
        return this.salary;
    }
    
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
    @Column(name="BIRTHDAY", length=7)

    public Timestamp getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="SEX", length=10)

    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    @Column(name="COMPANY", length=40)

    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    @Column(name="TELPHONE", length=11)

    public String getTelphone() {
        return this.telphone;
    }
    
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
    
    @Column(name="MSG")

    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
   
    @Transient
    public String getFormateBirthday(){
    	if(birthday!=null){
    		return new SimpleDateFormat("yyyy-MM-dd").format(birthday);
    	}else{
    		return "生日未设置";
    	}
    }






}