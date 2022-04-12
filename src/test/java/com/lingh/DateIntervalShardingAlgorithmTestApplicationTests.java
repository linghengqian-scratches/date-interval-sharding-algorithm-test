package com.lingh;

import com.google.common.collect.Range;
import com.lingh.mapper.TOrderMapper;
import com.lingh.pojo.TOrderPO;
import org.apache.shardingsphere.sharding.algorithm.sharding.datetime.IntervalShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@SpringBootTest
class DateIntervalShardingAlgorithmTestApplicationTests {
    @Autowired
    private TOrderMapper tOrderMapper;

    @Test
    void testDate1() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2022, Calendar.FEBRUARY, 17, 7, 30, 0);
        Date start = startCalendar.getTime();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(2022, Calendar.FEBRUARY, 18, 7, 30, 0);
        Date end = endCalendar.getTime();
        tOrderMapper.selectAllByDataDateInDate(start, end).forEach(System.out::println);
    }

    @Test
    void testDate2() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date start = simpleDateFormat.parse("2022-02-17 07:30:00.000");
        Date end = simpleDateFormat.parse("2022-02-18 07:30:00.100");
        tOrderMapper.selectAllByDataDateInDate(start, end).forEach(System.out::println);
    }

    @Test
    void testLocalDateTime() {
        LocalDateTime start = LocalDateTime.of(2022, 2, 17, 7, 30, 0);
        LocalDateTime end = LocalDateTime.of(2022, 2, 18, 7, 30, 0);
        List<TOrderPO> tOrderPOS = tOrderMapper.selectAllByDataDateInLocalDateTime(start, end);
        tOrderPOS.forEach(System.out::println);
    }

    @Test
    void testString() {
        String start = "2022-02-17 07:30:00.000";
        String end = "2022-02-18 07:30:00.100";
        tOrderMapper.selectAllByDataDateInString(start, end).forEach(System.out::println);
    }

    @Test
    void testAlgorithm() {
        LocalDate startTime = LocalDate.of(2022, 2, 16);
        LocalDate endTime = LocalDate.of(2022, 2, 20);
        IntervalShardingAlgorithm intervalShardingAlgorithm = new IntervalShardingAlgorithm();
        intervalShardingAlgorithm.getProps().setProperty("datetime-pattern", "yyyy-MM-dd HH:mm:ss.SSS");
        intervalShardingAlgorithm.getProps().setProperty("datetime-lower", "2022-02-16 00:00:00.000");
        intervalShardingAlgorithm.getProps().setProperty("datetime-upper", "2022-02-20 23:59:59.999");
        intervalShardingAlgorithm.getProps().setProperty("sharding-suffix-pattern", "_yyyyMMdd");
        intervalShardingAlgorithm.getProps().setProperty("datetime-interval-amount", "1");
        intervalShardingAlgorithm.getProps().setProperty("datetime-interval-unit", "DAYS");
        intervalShardingAlgorithm.init();
        List<String> availableTables = LongStream.range(0, ChronoUnit.DAYS.between(startTime, endTime))
                .mapToObj(startTime::plusDays)
                .map(localDate -> "t_order_" + localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .collect(Collectors.toList());
        Collection<String> actual = intervalShardingAlgorithm.doSharding(availableTables,
                new RangeShardingValue<>("t_order", "create_time",
                        Range.closed(LocalDateTime.of(2022, 2, 17, 7, 30, 0),
                                LocalDateTime.of(2022, 2, 18, 7, 30, 0))));
        Assert.isTrue(actual.size() == 2, "error in assert!");
    }
}
