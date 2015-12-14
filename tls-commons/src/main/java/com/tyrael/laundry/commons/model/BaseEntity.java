package com.tyrael.laundry.commons.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.tyrael.laundry.commons.util.AuthenticationUtil;

/**
 * 
 * @author Mark Martinez, created Nov 28, 2015
 *
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    @Column(name = "deleted")
    @Type(type = "yes_no")
    protected boolean deleted = false;

    @Column(name = "created_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime dateCreated;

    @Column(name = "updated_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime dateUpdated;

    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "updated_by")
    protected String updatedBy;

    @PrePersist
    public void create() {
        this.dateCreated = DateTime.now();
        this.dateUpdated = DateTime.now();
        this.createdBy = AuthenticationUtil.getLoggedInUsername();
        this.updatedBy = AuthenticationUtil.getLoggedInUsername();
    }

    @PreUpdate
    public void update() {
        this.dateUpdated = DateTime.now();
        this.updatedBy = AuthenticationUtil.getLoggedInUsername();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public DateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(DateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

