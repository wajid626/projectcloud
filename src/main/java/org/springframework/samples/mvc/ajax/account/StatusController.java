package org.springframework.samples.mvc.ajax.account;

import java.util.Collections;
import java.util.HashMap;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.net.URL;
import java.util.RandomAccess;
import java.lang.Iterable;
import java.util.AbstractMap;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.servlet.ModelAndView;
//imimport java.langn.IOException;port java.lagn.IOException;

import com.vmware.vim25.mo.*;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;
import com.vmware.vim25.VirtualMachineCloneSpec;
import com.vmware.vim25.VirtualMachineRelocateSpec;

@Controller
@RequestMapping(value="/status")
public class StatusController {
	
	private Map<Long, VirtualMachine> vms = new ConcurrentHashMap<Long, VirtualMachine>();
	
	
	@RequestMapping(value="/c", method=RequestMethod.GET)
	public String getCreateForm(Model model) {
//		model.addAttribute(new VirtualMachine());
		return "vm/createVM";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody String create(@RequestBody VirtualMachine vMachine, HttpServletResponse response) {
			//vms.put(vMachine.getId(), vMachine );
			return "SUCCESS";
	}
 
 @RequestMapping(value="/changeStatus/{op}/{vmname}",method=RequestMethod.GET)
	public ModelAndView changeStatus(@PathVariable String op, @PathVariable String  vmname)  throws Exception {
    String args[] = {"https://cmpe-admin-console.engr.sjsu.edu/sdk","student","mooc@2013"};
        

    ServiceInstance si = new ServiceInstance(
        new URL(args[0]), args[1], args[2], true);

    Folder rootFolder = si.getRootFolder();
    VirtualMachine vm = (VirtualMachine) new InventoryNavigator(
      rootFolder).searchManagedEntity("VirtualMachine", vmname);


    if(vm==null)
    {
      System.out.println("No VM " + vmname + " found");
      si.getServerConnection().logout();
      return new ModelAndView("status", "message", "VM not found..");
    }
    Task task = null;
  
   if("clone".equalsIgnoreCase(op))
   {
   VirtualMachineCloneSpec cloneSpec =  new VirtualMachineCloneSpec();
    cloneSpec.setLocation(new VirtualMachineRelocateSpec());
    cloneSpec.setPowerOn(false);
    cloneSpec.setTemplate(false);
    task = vm.cloneVM_Task((Folder) vm.getParent(), vmname+"_clone", cloneSpec); 
     String status = task.waitForMe();
    if(status==Task.SUCCESS)
    {
        
        return new ModelAndView("status", "message", "VM cloned successfully");
    }
   }
   
    if("poweron".equalsIgnoreCase(op))
    {
          task = vm.powerOnVM_Task(null);
     if(task.waitForTask()==Task.SUCCESS)
      {
       
        return new ModelAndView("status", "message", "VM powered on successfully");

      } 
      }
    else if("suspend".equalsIgnoreCase(op))
    {
       task = vm.suspendVM_Task();
      if(task.waitForTask()==Task.SUCCESS)
      {
        System.out.println(vmname + " suspended");
         return new ModelAndView("status", "message", "VM suspended successfully");
      }
    }
    else if("poweroff".equalsIgnoreCase(op))
    {
       task = vm.powerOffVM_Task();
      if(task.waitForTask()==Task.SUCCESS)
      {
        System.out.println(vmname + " suspended");
         return new ModelAndView("status", "message", "VM powered off successfully");
      }
    }
    else
    {
      System.out.println("Invalid operation. Exiting...");
    }
   si.getServerConnection().logout();

 return new ModelAndView("status", "message", "Operation failed");
}
}
