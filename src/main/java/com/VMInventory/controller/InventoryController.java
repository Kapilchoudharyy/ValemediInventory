package com.VMInventory.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.VMInventory.entity.InventoryFindCriteria;
import com.VMInventory.entity.InventoryPaginatedResponse;
import com.VMInventory.message.ResponseMessage;
import com.VMInventory.service.InventoryService;



@Controller
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    public static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @PostMapping("/csv")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String message;
        try {
            logger.info("uploading file {} to database" , file.getName());
            inventoryService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception ex) {
            logger.error("error in uploading file {} to database " , file.getName());
            throw ex;
        }
    }

    @GetMapping(value = "/{supplier}", produces = {"application/json"})
    public ResponseEntity<InventoryPaginatedResponse> getAllSampleInventory(
            @PathVariable("supplier") String supplier,
            @Valid InventoryFindCriteria findCriteria
    ) {
        try {
            if (findCriteria == null) {
                findCriteria = new InventoryFindCriteria();
            }
            logger.info("finding items for supplier {} pageNo {} ", supplier, findCriteria.getPage());
            InventoryPaginatedResponse response = inventoryService.getBySupplierAndStock(supplier, findCriteria);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error in getting items for supplier {}", supplier);
            throw e;
        }
    }
}
