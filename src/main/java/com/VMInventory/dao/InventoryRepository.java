package com.VMInventory.dao;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.VMInventory.entity.Inventory;




public interface InventoryRepository extends JpaRepository<Inventory,String> {
    @Query("select i from Inventory i " +
            "where upper(i.supplier) like upper(concat(?1, '%')) and (i.stock > 0) " +
            "and (?2 is null or upper(i.name) like upper(?2))" +
            "and ( ?3 is null or upper(i.code) like upper(?3)) and (?4 is null or i.exp > ?4)")
    Page<Inventory> getBySupplier(@NonNull String supplier,@Nullable String name ,  @Nullable String code, @Nullable LocalDate exp, Pageable pageable);



    @Query("select i from Inventory i " +
            "where (upper(i.supplier) contains in ?1) and (i.stock > 0) " +
            "and ( ?2 is null or upper(i.code) like upper(?2)) and (?3 is null or i.exp > ?3)")
    Page<Inventory> getBySupplierList(@NonNull List<String> supplier, @Nullable String code, @Nullable LocalDate exp, Pageable pageable);

}


