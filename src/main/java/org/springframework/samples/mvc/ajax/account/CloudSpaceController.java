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
import com.vmware.vim25.HostListSummary;
import com.vmware.vim25.HostListSummaryQuickStats;
import com.vmware.vim25.VirtualMachineRuntimeInfo;
import com.vmware.vim25.VirtualMachinePowerState;
import org.codehaus.jackson.map.ObjectMapper;

@Controller
@RequestMapping(value="/vm")
public class CloudSpaceController {
	
	private Map<Long, VirtualMachine> vms = new ConcurrentHashMap<Long, VirtualMachine>();
	private  ManagedEntity[] vmsArray = null;
	
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
	
	@RequestMapping(value="/vms",method=RequestMethod.GET)
	public ModelAndView getVMList()  throws Exception {
    List<String> vmNames = new ArrayList<String>();
    ServiceInstance si = new ServiceInstance(new URL("https://cmpe-admin-console.engr.sjsu.edu/sdk"), "student", "mooc@2013", true);
    Folder rootFolder = si.getRootFolder();
    ManagedEntity[] vms = new InventoryNavigator(rootFolder).searchManagedEntities(
                new String[][] { {"VirtualMachine", "name" }, }, true);
                vmsArray = vms;
        for(int i=0; i<vms.length; i++)
        {
          
            vmNames.add(vms[i].getName());
        }
        ModelAndView mv = new ModelAndView("sample");
        mv.addObject("vmdropdownvalues", vmNames);
       
        VirtualMachine vm = (VirtualMachine) vms[0];
        VirtualMachineRuntimeInfo vmri = (VirtualMachineRuntimeInfo)vm.getRuntime();
        if(vmri.getPowerState() == VirtualMachinePowerState.poweredOn){
            mv.addObject("message", vm.getName() + " is currently Running...");
        }
        else if(vmri.getPowerState() == VirtualMachinePowerState.poweredOff){
             mv.addObject("message", vm.getName() + " is currently poweredOff...");
        }
        else if(vmri.getPowerState() == VirtualMachinePowerState.suspended){
              mv.addObject("message", vm.getName() +  " is currently Suspended...");
        }
     return mv;
	}
 
 	@RequestMapping(value="/vmsByState",method=RequestMethod.GET)
	public ModelAndView getVMListByState()  throws Exception {
    HashMap runningVal = new HashMap();
    HashMap suspendedVal = new HashMap();
    HashMap poweredoffVal = new HashMap();
    List runningValList = new ArrayList();
    List suspendedValList = new ArrayList();
    List poweredoffValList = new ArrayList();



    ServiceInstance si = new ServiceInstance(new URL("https://cmpe-admin-console.engr.sjsu.edu/sdk"), "student", "mooc@2013", true);
    Folder rootFolder = si.getRootFolder();
    ManagedEntity[] vms = new InventoryNavigator(rootFolder).searchManagedEntities(
                new String[][] { {"VirtualMachine", "name" }, }, true);
                vmsArray = vms;
        for(int i=0; i<vms.length; i++)
        {
        VirtualMachine vm = (VirtualMachine) vms[i];
        VirtualMachineRuntimeInfo vmri = (VirtualMachineRuntimeInfo)vm.getRuntime();
        
        if(vmri.getPowerState() == VirtualMachinePowerState.poweredOn){
         runningVal = new HashMap();

           runningVal.put("vmname", vm.getName());
           runningVal.put("guestosname", vm.getConfig().getGuestFullName());
           runningVal.put("memory", vm.getConfig().getHardware().getMemoryMB());
           runningVal.put("cpu", vm.getConfig().getHardware().getNumCPU());
           runningValList.add(runningVal);
        }
       else if(vmri.getPowerState() == VirtualMachinePowerState.suspended){
        runningVal = new HashMap();

            suspendedVal.put("vmname", vm.getName());
           suspendedVal.put("guestosname", vm.getConfig().getGuestFullName());
           suspendedVal.put("memory", vm.getConfig().getHardware().getMemoryMB());
           suspendedVal.put("cpu", vm.getConfig().getHardware().getNumCPU());
             suspendedValList.add(suspendedVal);
        }
       else if(vmri.getPowerState() == VirtualMachinePowerState.poweredOff){
        poweredoffVal = new HashMap();

           poweredoffVal.put("vmname", vm.getName());
           poweredoffVal.put("guestosname", vm.getConfig().getGuestFullName());
           poweredoffVal.put("memory", vm.getConfig().getHardware().getMemoryMB());
           poweredoffVal.put("cpu", vm.getConfig().getHardware().getNumCPU());
           poweredoffValList.add(poweredoffVal);
        }
        }
        ModelAndView mv = new ModelAndView("viewvmsbystate");
      /*  if(runningVal.size()==0){
          runningVal.add("No Vms");
        }
          if(suspendedVal.size()==0){
          suspendedVal.add("No Vms");
        }
           if(poweredoffVal.size()==0){
          poweredoffVal.add("No Vms");
        }*/
        mv.addObject("runningval", runningValList);
        mv.addObject("suspendedval", suspendedValList);
        mv.addObject("poweredoffval", poweredoffValList);
        

     return mv;
	}

 
 @RequestMapping(value="/hosts",method=RequestMethod.GET)
	public ModelAndView getHostList()  throws Exception {
    List<String> vmNames = new ArrayList<String>();
    ServiceInstance si = new ServiceInstance(new URL("https://cmpe-admin-console.engr.sjsu.edu/sdk"), "student", "mooc@2013", true);
    Folder rootFolder = si.getRootFolder();
    ManagedEntity[] hosts = new InventoryNavigator(rootFolder).searchManagedEntities(
                new String[][] { {"HostSystem", "name" }, }, true);
        for(int i=0; i<hosts.length; i++)
        {
          
            vmNames.add(hosts[i].getName());
        }
          HashMap map = new HashMap();
         // if (hosts[i].getName().contains(hoststr)){
             HostSystem  host  = (HostSystem) hosts[0] ;
             HostListSummary hls = host.getSummary();
             HostListSummaryQuickStats qstats = hls.getQuickStats();

             map.put("Host Name", host.getName());
             map.put("CPU Fairness",qstats.getDistributedCpuFairness()) ;    
             map.put("Memory Fairness",qstats.getDistributedMemoryFairness()) ;  
             map.put("CPU Usage (MHz)",qstats.getOverallCpuUsage()) ;
             map.put("Memory Usage (MB)",qstats.getOverallMemoryUsage()) ;
        //  }
         ModelAndView mv = new ModelAndView("viewhosts");
         mv.addObject("vmdropdownvalues", vmNames);
          mv.addObject("mapval", map);
        
    // return new ModelAndView("viewhosts", "vmdropdownvalues", vmNames);
      return mv;

	}

 @RequestMapping(value="/datacenters",method=RequestMethod.GET)
	public ModelAndView getDataCentreList()  throws Exception {
    List<String> hostNames = new ArrayList<String>();
    ServiceInstance si = new ServiceInstance(new URL("https://cmpe-admin-console.engr.sjsu.edu/sdk"), "student", "mooc@2013", true);
    Folder rootFolder = si.getRootFolder();
    ManagedEntity[] hosts = new InventoryNavigator(rootFolder).searchManagedEntities(
                new String[][] { {"Datacenter", "name" }, }, true);
        for(int i=0; i<hosts.length; i++)
        {
          
            hostNames.add(hosts[i].getName());
        }
     return new ModelAndView("viewdatacenter", "vmdropdownvalues", hostNames);
	}

 @RequestMapping(value="/hostsummary",method=RequestMethod.GET)
	public @ResponseBody String getHostSummary(@RequestParam String hoststr)  throws Exception {
 List<String> hostNames = new ArrayList<String>();
    ServiceInstance si = new ServiceInstance(new URL("https://cmpe-admin-console.engr.sjsu.edu/sdk"), "student", "mooc@2013", true);
    Folder rootFolder = si.getRootFolder();

    ManagedEntity[] hosts = new InventoryNavigator(rootFolder).searchManagedEntities(
                new String[][] { {"HostSystem", "name" }, }, true);
      // HostSystem host = new HostSystem();
       HashMap map = new HashMap();
        for(int i=0; i<hosts.length; i++)
        {
          System.out.println("host name is " + hosts[i].getName());
          if (hosts[i].getName().contains(hoststr)){
             HostSystem  host  = (HostSystem) hosts[i] ;
             HostListSummary hls = host.getSummary();
             HostListSummaryQuickStats qstats = hls.getQuickStats();
             map.put("Host Name", host.getName());
             map.put("CPU Fairness",qstats.getDistributedCpuFairness()) ;    
             map.put("Memory Fairness",qstats.getDistributedMemoryFairness()) ;  
             map.put("CPU Usage (MHz)",qstats.getOverallCpuUsage()) ;
             map.put("Memory Usage (MB)",qstats.getOverallMemoryUsage()) ;
             break;
          }
        }
        ObjectMapper mapper = new ObjectMapper();
return "{\"mapval\":" +  mapper.writeValueAsString(map) + "}";
//return map;
// return new ModelAndView("viewhostsummary", "mapval", map);
}

 @RequestMapping(value="/vmstatus",method=RequestMethod.GET)
	public @ResponseBody String getHostStatus(@RequestParam String vmname)  throws Exception {
                
       VirtualMachine vm = null;
       String msg = "{ \"message\" : \"Status not found..\" }";
        for(int i=0; i<vmsArray.length; i++)
        {
          if(vmsArray[i].getName().equalsIgnoreCase(vmname)){
          vm = (VirtualMachine) vmsArray[i];
           break;

           }
        }
        VirtualMachineRuntimeInfo vmri = (VirtualMachineRuntimeInfo)vm.getRuntime();
        if(vmri.getPowerState() == VirtualMachinePowerState.poweredOn){
           String msgStr = "\"" + vm.getName() + " is currently Running..." +  "\"";
            msg =  "{ \"message\" :" +  msgStr + "}";
        }
        else if(vmri.getPowerState() == VirtualMachinePowerState.poweredOff){
  
          String msgStr =  "\"" + vm.getName() + " is currently PoweredOff..." +  "\"";
           msg =  "{ \"message\" :" +  msgStr + "}";
        }
        else if(vmri.getPowerState() == VirtualMachinePowerState.suspended){
  
            String msgStr =  "\"" + vm.getName() + " is currently Suspended..." +  "\"";
             msg =  "{ \"message\" :" +  msgStr + "}";
        }
 
 return msg;
}




}
