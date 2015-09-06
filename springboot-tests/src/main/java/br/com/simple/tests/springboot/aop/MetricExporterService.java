package br.com.simple.tests.springboot.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.reader.PrefixMetricReader;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MetricExporterService {

    private static final Logger LOGGER = LoggerFactory.getLogger("METRICS");

    private final MetricRepository repository;

    @Autowired
    public MetricExporterService(MetricRepository repository) {
        this.repository = repository;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 60000)
    void exportMetrics() {
        StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.groupingBy(Metric::getName, Collectors.summingLong(m -> m.getValue().longValue())))
                .forEach(this::log);
    }

    protected void log(String name, Number count) {
        LOGGER.debug("{}={}", name, count);
        repository.reset(name);
    }

}