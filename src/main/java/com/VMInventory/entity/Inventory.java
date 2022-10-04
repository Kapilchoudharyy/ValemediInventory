package com.VMInventory.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Inventory")
public class Inventory {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	 private String code;
	 private String name;
	 private String batch;
	 private double stock;
	 private int deal;
	 private int free;
	 private double mrp;
	 private double rate;

	 private LocalDate exp;
	 private String company;
	 private String supplier;

	public Inventory() {
		super();
	}
	 
	 
	 public Inventory(String code, String name, String batch, double stock, int deal, int free, double mrp, double rate, LocalDate exp,
					  String company, String supplier) {
		super();
		this.code = code;
		this.name = name;
		this.batch = batch;
		this.stock = stock;
		this.deal = deal;
		this.free = free;
		this.mrp = mrp;
		this.rate = rate;
		this.exp = exp;
		this.company = company;
		this.supplier = supplier;
	}


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}
	public int getDeal() {
		return deal;
	}
	public void setDeal(int deal) {
		this.deal = deal;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public LocalDate getExp() {
		return exp;
	}


	public void setExp(LocalDate exp) {
		this.exp = exp;
	}


	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}


	@Override
	public String toString() {
		return "SampeleInventory [code=" + code + ", name=" + name + ", batch=" + batch + ", stock=" + stock + ", deal="
				+ deal + ", free=" + free + ", mrp=" + mrp + ", rate=" + rate + ", exp=" + exp + ", company=" + company
				+ ", supplier=" + supplier + "]";
	}


}
