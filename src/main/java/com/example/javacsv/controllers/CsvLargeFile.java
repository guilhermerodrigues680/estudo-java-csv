package com.example.javacsv.controllers;

import com.example.javacsv.models.GeographicBean;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/csv-large-file")
public class CsvLargeFile {

    private static final Logger LOG = LoggerFactory.getLogger(CsvLargeFile.class);

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("CSV LARGE FILE OK!");
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<List<GeographicBean>> submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        modelMap.addAttribute("file", file);

        LOG.info(file.getContentType());
        LOG.info(file.getName());
        LOG.info(file.getOriginalFilename());
        LOG.info("{}", file.getSize());

        StopWatch timerCSV = new StopWatch();
        timerCSV.start();

        BufferedReader br;
        InputStream is = file.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));

        HeaderColumnNameMappingStrategy<GeographicBean> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(GeographicBean.class);
        CsvToBean<GeographicBean> csvToBean = new CsvToBeanBuilder<GeographicBean>(br)
                .withMappingStrategy(strategy)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        List<GeographicBean> geographicBeans = csvToBean.parse();

        timerCSV.stop();
        LOG.info("tempo gasto csv: {} ms", timerCSV.getTotalTimeMillis());

        LOG.info("{}", geographicBeans.get(0).getArea());
        LOG.info("Geographic size: {}", geographicBeans.size());

        StopWatch timerFilterCSV = new StopWatch();
        timerFilterCSV.start();

        Predicate<GeographicBean> byCustom = geo -> geo.getYear() == 2000
                && geo.getGeo_count() == 0
                && geo.getEc_count() == 0;

        List<GeographicBean> geoFiltered = geographicBeans.stream().filter(byCustom)
                .collect(Collectors.toList());

        timerFilterCSV.stop();
        LOG.info("tempo gasto filter csv: {} ms", timerFilterCSV.getTotalTimeMillis());

        LOG.info("geoFiltered size: {}", geoFiltered.size());

        // -> Filtro de repetidos
        List<GeographicBean> geoFilteredDuplicate = geographicBeans.stream()
                .distinct()
                .collect(Collectors.toList());

        LOG.info("geoFilteredDuplicate size: {}", geoFilteredDuplicate.size());

        // <- Filtro de repetidos

        return ResponseEntity.ok(geoFiltered);
    }

}
