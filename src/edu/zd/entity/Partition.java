package edu.zd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * Partition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="PARTITION"
    ,schema="LOGISTIC"
)

public class Partition  implements java.io.Serializable {


    // Fields    

     private String id;
     private Region region;
     private Decidezone decidezone;
     private String addresskey;
     private String startnum;
     private String endnum;
     private String single;
     private String position;


    // Constructors

    /** default constructor */
    public Partition() {
    }

    
    /** full constructor */
    public Partition(Region region, Decidezone decidezone, String addresskey, String startnum, String endnum, String single, String position) {
        this.region = region;
        this.decidezone = decidezone;
        this.addresskey = addresskey;
        this.startnum = startnum;
        this.endnum = endnum;
        this.single = single;
        this.position = position;
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
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="REGION_ID")

    public Region getRegion() {
        return this.region;
    }
    
    public void setRegion(Region region) {
        this.region = region;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="DECIDEZONE_ID")

    public Decidezone getDecidezone() {
        return this.decidezone;
    }
    
    public void setDecidezone(Decidezone decidezone) {
        this.decidezone = decidezone;
    }
    
    @Column(name="ADDRESSKEY", length=100)

    public String getAddresskey() {
        return this.addresskey;
    }
    
    public void setAddresskey(String addresskey) {
        this.addresskey = addresskey;
    }
    
    @Column(name="STARTNUM", length=30)

    public String getStartnum() {
        return this.startnum;
    }
    
    public void setStartnum(String startnum) {
        this.startnum = startnum;
    }
    
    @Column(name="ENDNUM", length=30)

    public String getEndnum() {
        return this.endnum;
    }
    
    public void setEndnum(String endnum) {
        this.endnum = endnum;
    }
    
    @Column(name="SINGLE", length=1)

    public String getSingle() {
        return this.single;
    }
    
    public void setSingle(String single) {
        this.single = single;
    }
    
    @Column(name="POSITION")

    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
   








}