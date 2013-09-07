package org.springframework.samples.mvc.ajax.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class VirtualMachine {

	/**
    * @return the harddisk
    */
   public Integer getHarddisk()
   {
      return harddisk;
   }

   /**
    * @param harddisk the harddisk to set
    */
   public void setHarddisk(Integer harddisk)
   {
      this.harddisk = harddisk;
   }

   /**
    * @return the id
    */
   public Long getId()
   {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(Long id)
   {
      this.id = id;
   }

   /**
    * @return the name
    */
   public String getName()
   {
      return name;
   }

   /**
    * @param name the name to set
    */
   public void setName(String name)
   {
      this.name = name;
   }

   /**
    * @return the ram
    */
   public Integer getRam()
   {
      return ram;
   }

   /**
    * @param ram the ram to set
    */
   public void setRam(Integer ram)
   {
      this.ram = ram;
   }

   /**
    * @return the vcpu
    */
   public Integer getVcpu()
   {
      return vcpu;
   }

   /**
    * @param vcpu the vcpu to set
    */
   public void setVcpu(Integer vcpu)
   {
      this.vcpu = vcpu;
   }

   private Long id;
		
	private String name;

	private Integer ram;
  
  private Integer harddisk;
  
  private Integer vcpu;

	
}