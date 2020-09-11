package com.example.javacsv.controllers;

import com.example.javacsv.models.IndustryBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/csv-file")
public class CsvFile {

    private static Logger LOG = LoggerFactory.getLogger(CsvFile.class);

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("CSV FILE OK!");
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<List<IndustryBean>> submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        modelMap.addAttribute("file", file);

        LOG.info(file.getContentType());
        LOG.info(file.getName());
        LOG.info(file.getOriginalFilename());
        LOG.info("{}", file.getSize());

//        File targetFile = new File("src/main/resources/targetFile.tmp");
//
//        try (OutputStream os = new FileOutputStream(targetFile)) {
//            os.write(file.getBytes());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        List<List<String>> records = new ArrayList<List<String>>();
//        Reader reader = new InputStreamReader(file.getInputStream());
//        try (CSVReader csvReader = new CSVReader(reader);) {
//            String[] values = null;
//            while ((values = csvReader.readNext()) != null) {
//                records.add(Arrays.asList(values));
//            }
//        } catch (CsvValidationException e) {
//            e.printStackTrace();
//        }
//
//        LOG.info(records.get(0).get(0));

        BufferedReader br;
        InputStream is = file.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));

        HeaderColumnNameMappingStrategy<IndustryBean> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(IndustryBean.class);
        CsvToBean<IndustryBean> csvToBean = new CsvToBeanBuilder<IndustryBean>(br)
                .withMappingStrategy(strategy)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        List<IndustryBean> industrys = csvToBean.parse();

        LOG.info("{}", industrys.get(0).getIndustry_name_ANZSIC());
        LOG.info("industrys size: {}", industrys.size());


        return ResponseEntity.ok(industrys);

        //return "fileUploadView";
    }

}
