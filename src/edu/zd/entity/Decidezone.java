// default package
package edu.zd.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * Decidezone entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="DECIDEZONE"
    ,schema="LOGISTIC"
)

public class Decidezone  implements java.io.Serializable {


    // Fields    

     private String id;
     private Staff staff;
     private String name;
     private Set<Partition> partitions = new HashSet<Partition>(0);


    // Constructors

    /** default constructor */
    public Decidezone() {
    }

    
    /** full constructor */
    public Decidezone(Staff staff, String name, Set<Partition> partitions) {
        this.staff = staff;
        this.name = name;
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
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="STAFF_ID")

    public Staff getStaff() {
        return this.staff;
    }
    
    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
    @Column(name="NAME", length=30)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="decidezone")

    public Set<Partition> getPartitions() {
        return this.partitions;
    }
    
    public void setPartitions(Set<Partition> partitions) {
        this.partitions = partitions;
    }
   








}