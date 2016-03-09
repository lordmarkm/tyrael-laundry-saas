package com.tyrael.laundry.reports.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Sofia Ang
 */
@Entity(name = "date_dimension")
public class DateDimension {

    /**
     * YYYYMMDD format
     */
    @Id
    protected int id;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    protected LocalDate date;

    @Column(name = "day_num_in_week")
    protected int dayNumInWeek;

    @Column(name = "day_name")
    protected String dayName;

    @Column(name = "day_num_in_month")
    protected int dayNumInMonth;

    @Column(name = "week_num_in_year")
    protected int weekNumInYear;

    @Column(name = "month_num")
    protected int monthNum;

    @Column(name = "month_name")
    protected String monthName;

    @Column(name = "quarter_num")
    protected int quarterNum;

    @Column
    protected int year;

    @Column(name = "fiscal_day_num_in_month")
    protected int fiscalDayNumInMonth;

    @Column(name = "fiscal_week_num_in_year")
    protected int fiscalWeekNumInYear;

    @Column(name = "fiscal_month_num")
    protected int fiscalMonthNum;

    @Column(name = "fiscal_quarter_num")
    protected int fiscalQuarterNum;

    @Column(name = "fiscal_year")
    protected int fiscalYear;

    @Column(name = "weekday_flag")
    protected String weekdayFlag;

    @Column(name = "holiday_flag")
    protected String holidayFlag;

}