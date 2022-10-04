package com.VMInventory.helper;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.VMInventory.entity.Inventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVHelper {
    public static final String TYPE = "text/csv";
    static final String[] HEADERS = {"code", "name", "batch", "stock", "deal", "free", "mrp", "rate", "exp", "company", "supplier"};
    static Logger logger = LoggerFactory.getLogger(CSVHelper.class);
    static DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private CSVHelper() {
    }

    public static boolean hasCSVFormat(MultipartFile file) {
        return(TYPE.equals(file.getContentType()));
    }

    public static List<Inventory> csvToSampleInventory(InputStream is) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            List<Inventory> inventories = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withHeader(HEADERS)
                    .withFirstRecordAsHeader().withTrim()
                    .parse(fileReader);

            for (CSVRecord csvRecord : csvRecords) {
                Inventory inventory = new Inventory(
                        csvRecord.get("code"), csvRecord.get("name"), csvRecord.get("batch"), Double.parseDouble(csvRecord.get("stock")),
                        Integer.parseInt(csvRecord.get("deal")), Integer.parseInt(csvRecord.get("free")),
                        Double.parseDouble(csvRecord.get("mrp")), Double.parseDouble(csvRecord.get("rate")),
                       getLocalDateFromString(csvRecord.get("exp")),
                        csvRecord.get("company"), csvRecord.get("supplier")
                );
                inventories.add(inventory);
            }
            return inventories;
        } catch (IOException e) {
            logger.error("cant upload csv to database ");
            throw e;
        }
    }
    public static LocalDate getLocalDateFromString(String dateString) {
        try {
            if (dateString.contains("/")) {
                return LocalDate.parse(dateString, dateTimeformatter);
            } else if (dateString.contains("-")) {
                return LocalDate.parse(dateString);
            } else {
                return null;
            }
        } catch (DateTimeParseException e) {
            logger.info("cant parse string to date {}", dateString);
            return null;
        }
    }

}
