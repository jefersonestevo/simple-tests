package br.com.simple.tests.springboot.aop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class MetricExporterServiceTest {

    private MetricExporterService metricExporterService;

    private MetricRepository repository;

    @Before
    public void init() {
        repository = mock(MetricRepository.class);

        metricExporterService = spy(new MetricExporterService(repository));

        List<Metric<?>> metrics = new ArrayList<>();
        metrics.add(new Metric<Number>("test.1", 4));
        metrics.add(new Metric<Number>("test.2", 2));
        metrics.add(new Metric<Number>("test.1", 3));
        metrics.add(new Metric<Number>("test.3", 1));
        when(repository.findAll()).thenReturn(metrics);
    }

    @Test
    public void testExportMetrics() throws Exception {
        doNothing().when(metricExporterService).log(anyString(), anyLong());

        metricExporterService.exportMetrics();

        verify(metricExporterService).log(eq("test.1"), eq(7l));
        verify(metricExporterService).log(eq("test.2"), eq(2l));
        verify(metricExporterService).log(eq("test.3"), eq(1l));
    }
}