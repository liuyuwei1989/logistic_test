package edu.zd.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * Region entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="REGION"
    ,schema="LOGISTIC"
)

public class Region  implements java.io.Serializable {


    // Fields    

     private String id;
     private String province;
     private String city;
     private String distincts;
     private String postcode;
     private String shotcode;
     private String citycode;
     private Set<Partition> partitions = new HashSet<Partition>(0);


    // Constructors

    /** default constructor */
    public Region() {
    }

    
    /** full constructor */
    public Region(String province, String city, String distincts, String postcode, String shotcode, String citycode, Set<Partition> partitions) {
        this.province = province;
        this.city = city;
        this.distincts = distincts;
        this.postcode = postcode;
        this.shotcode = shotcode;
        this.citycode = citycode;
        this.partitions = partitions;
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
    
    @Column(name="PROVINCE", length=50)

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="CITY", length=50)

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="DISTINCTS", length=50)

    public String getDistincts() {
        return this.distincts;
    }
    
    public void setDistincts(String distincts) {
        this.distincts = distincts;
    }
    
    @Column(name="POSTCODE", length=50)

    public String getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    @Column(name="SHOTCODE", length=50)

    public String getShotcode() {
        return this.shotcode;
    }
    
    public void setShotcode(String shotcode) {
        this.shotcode = shotcode;
    }
    
    @Column(name="CITYCODE", length=30)

    public String getCitycode() {
        return this.citycode;
    }
    
    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="region")

    public Set<Partition> getPartitions() {
        return this.partitions;
    }
    
    public void setPartitions(Set<Partition> partitions) {
        this.partitions = partitions;
    }
   








}