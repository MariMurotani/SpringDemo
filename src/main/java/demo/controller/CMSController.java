package demo.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.dto.AddedKey;
import demo.dto.Status;
import demo.model.CMS;
import demo.model.PathList;
import demo.service.CMSService;
import demo.service.UserService;

@EnableAutoConfiguration
@EnableConfigurationProperties
@Controller
@RequestMapping("/cms")
public class CMSController {
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private CMSService cmsService;

	private final AtomicLong counter = new AtomicLong();
	
	/**
	 * curl -d path="/images/201506/" -d name="test.png" http://localhost:8080/cms/addData
	 * */
	@RequestMapping(value="addData",method=RequestMethod.POST)
    public @ResponseBody AddedKey cmsAddData(@RequestParam(value="path", required=true, defaultValue="Stranger") String path,@RequestParam(value="name", defaultValue="") String name) {
		try{
			path = path.trim();
			String left1 = path.substring(0,1);
			if(!left1.equals("/")){
				path = "/" + path;
			}
			
			String value = cmsService.AddNewRecoad(path, name);
			AddedKey adk = new AddedKey(value);
			return adk;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}catch(Error e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * curl -d "" http://localhost:8080/cms/getAllPaths
	 * */
	@RequestMapping(value="getAllPaths",method=RequestMethod.POST)
    public @ResponseBody List<PathList> getAllPaths() {
		try{
			List<PathList> res = cmsService.GetAllPaths();
			return res;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}catch(Error e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * curl -d path="/images/201506/" -d name="test.png" http://localhost:8080/cms/getAllPath
	 * */
	@RequestMapping(value="getAllPath",method=RequestMethod.POST)
    public @ResponseBody List<CMS> getAllPath(@RequestParam(value="path", required=true) String path,@RequestParam(value="name", defaultValue="") String name) {
		try{
			List<CMS> res = this.cmsService.GetAllByPath(path, name);
			return res;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}catch(Error e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * curl -d key="55988ca43004579cf155fd1b" -d ext=".html" -d mime="text/html" -d data="test" http://localhost:8080/cms/updateData
	 * */
	@RequestMapping(value="updateData",method=RequestMethod.POST)
    public @ResponseBody Status cmsUpdateData(
    		@RequestParam(value="key", required=true) String key,
    		@RequestParam(value="ext", defaultValue="") String ext,
    		@RequestParam(value="mime", defaultValue="") String mime,
    		@RequestParam(value="data", defaultValue="") String data) {
		try{
			String sdata = data;
			Byte[] bdata= null;
			this.cmsService.UpdateRecoad(key, ext, mime, sdata, bdata);
			return new Status("OK");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}catch(Error e){
			System.out.println(e.getMessage());
		}
		return null;
	}

}
