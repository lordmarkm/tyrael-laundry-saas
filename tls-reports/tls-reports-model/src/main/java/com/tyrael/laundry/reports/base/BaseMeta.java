package com.tyrael.laundry.reports.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

/**
 * @author Mark Martinez, created Oct 23, 2015
 */
@MappedSuperclass
public class BaseMeta {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(name = "meta_effective_dt")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    protected LocalDateTime metaEffectiveDateTime;

    @Column(name = "meta_expiration_dt")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    protected LocalDateTime metaExpirationDateTime;

    @Column(name = "meta_version")
    protected Long metaVersion;

    @Column(name = "meta_created_dt")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    protected LocalDateTime metaCreatedDateTime;

    @Column(name = "meta_updated_dt")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    protected LocalDateTime metaUpdatedDt;

}
