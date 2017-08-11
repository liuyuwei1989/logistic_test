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
 * Staff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="STAFF"
    ,schema="LOGISTIC"
)

public class Staff  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private String telephone;
     private String haspda;
     private String deltag;
     private String station;
     private String standard;
     private Set<Decidezone> decidezones = new HashSet<Decidezone>(0);


    // Constructors

    /** default constructor */
    public Staff() {
    }

    
    /** full constructor */
    public Staff(String name, String telephone, String haspda, String deltag, String station, String standard, Set<Decidezone> decidezones) {
        this.name = name;
        this.telephone = telephone;
        this.haspda = haspda;
        this.deltag = deltag;
        this.station = station;
        this.standard = standard;
        this.decidezones = decidezones;
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
    
    @Column(name="NAME", length=20)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="TELEPHONE", length=20)

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name="HASPDA", length=1)

    public String getHaspda() {
        return this.haspda;
    }
    
    public void setHaspda(String haspda) {
        this.haspda = haspda;
    }
    
    @Column(name="DELTAG", length=1)

    public String getDeltag() {
        return this.deltag;
    }
    
    public void setDeltag(String deltag) {
        this.deltag = deltag;
    }
    
    @Column(name="STATION", length=40)

    public String getStation() {
        return this.station;
    }
    
    public void setStation(String station) {
        this.station = station;
    }
    
    @Column(name="STANDARD", length=100)

    public String getStandard() {
        return this.standard;
    }
    
    public void setStandard(String standard) {
        this.standard = standard;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="staff")

    public Set<Decidezone> getDecidezones() {
        return this.decidezones;
    }
    
    public void setDecidezones(Set<Decidezone> decidezones) {
        this.decidezones = decidezones;
    }
   








}