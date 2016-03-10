package com.tyrael.laundry.reports.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

/**
 *
 * @author Mark Baldwin B. Martinez on Mar 9, 2016
 *
 */
@MappedSuperclass
public class BaseMeta {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(name = "deleted")
    @Type(type = "yes_no")
    protected boolean deleted;

    //    @Column(name = "meta_current_flag")
    //    @Type(type = "yes_no")
    //    protected boolean metaCurrentFlag;
    //
    //    @Column(name = "meta_effective_dt")
    //    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    //    protected LocalDateTime metaEffectiveDateTime;
    //
    //    @Column(name = "meta_expiration_dt")
    //    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    //    protected LocalDateTime metaExpirationDateTime;
    //
    //    @Column(name = "meta_version")
    //    protected Long metaVersion;

    @Column(name = "meta_created_dt")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    protected LocalDateTime metaCreatedDt;

    @Column(name = "meta_updated_dt")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    protected LocalDateTime metaUpdatedDt;

}
