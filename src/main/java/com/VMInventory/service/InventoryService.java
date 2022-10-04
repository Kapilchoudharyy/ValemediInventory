package com.VMInventory.service;



import java.io.IOException;
import java.time.LocalDate;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.VMInventory.dao.InventoryRepository;
import com.VMInventory.entity.Inventory;
import com.VMInventory.entity.InventoryFindCriteria;
import com.VMInventory.entity.InventoryPaginatedResponse;
import com.VMInventory.exception.InventoryFetchException;
import com.VMInventory.exception.InventoryUploadException;
import com.VMInventory.helper.CSVHelper;



@Service
public class InventoryService {
    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public static final Logger logger = LoggerFactory.getLogger(InventoryService.class);
    public void save(MultipartFile file) throws IOException {
        try {
            if(!CSVHelper.hasCSVFormat(file)) throw new InventoryUploadException("file should be csv format");
            logger.info("uploading file {} to database" , file.getName());
            List<Inventory> valueMediRepository = CSVHelper.csvToSampleInventory(file.getInputStream());
            repository.saveAll(valueMediRepository);
        } catch (IOException e) {
            throw new IOException("fail to store csv data: " + e.getMessage());
        }
    }

    public InventoryPaginatedResponse getBySupplierAndStock(String supplierName, InventoryFindCriteria criteria) {
        logger.info("getting items for supplier {} and criteria {}" , supplierName , criteria);
        if(criteria.getPage()<1){
            throw new InventoryFetchException("pageSize should be greater or equal to 1");
        }
        Pageable pageable = PageRequest.of(criteria.getPage() - 1, criteria.getSize());
        LocalDate exp = (criteria.isNotExpired() != null && criteria.isNotExpired()) ? LocalDate.now() : null;

        try {
            Page<Inventory> pageResponse = repository.getBySupplier(supplierName,
                    criteria.getName(),criteria.getCode(), exp, pageable);
            return new InventoryPaginatedResponse(
                    pageResponse.getTotalElements(),
                    pageResponse.getNumberOfElements(),
                    pageResponse.getSize(),
                    pageResponse.toList(),
                    criteria
            );
        } catch (Exception ex ) {
            logger.error("error getting items for supplier {} and criteria {}" , supplierName , criteria);
            throw ex;
        }
    }


}