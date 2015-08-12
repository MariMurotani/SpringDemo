package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import utils.transition.EnableTransCheck;
import utils.transition.ScreenTrans;
import ch.qos.logback.core.Context;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

@EnableAutoConfiguration
@EnableConfigurationProperties
@Controller
@RequestMapping("/parse")
@EnableTransCheck
public class ParserController {
	@Autowired
	private ApplicationContext context;
	
	/**
	 * http://localhost:8080/parse/index.html
	 * */
	@ScreenTrans(usetoken=true)
	@RequestMapping(value="index",method=RequestMethod.GET)
    public String index(Model model) {
		model.addAttribute("tree", "");
		  
		/*dump("クラス", clazz.getDeclaredAnnotations());

		Annotation[][] ma = ms[0].getParameterAnnotations();
		dump("引数1", ma[0]);
		dump("引数2", ma[1]);
		*/
		
		return "parse/index";
	}
	
	@ScreenTrans(referer="/parse/index")
	@RequestMapping(value="doParse",method=RequestMethod.POST)
	public String doParse(@RequestParam(value="text", required=true) String value,Model model){
		model.addAttribute("tree", "");
		
		StanfordCoreNLP pipeline = new StanfordCoreNLP();
	    Annotation annotation;
	    annotation = new Annotation(value.trim());
	    
	    pipeline.annotate(annotation);
	    
	    ApplicationContext test = context;
		
	    // An Annotation is a Map and you can get and use the various analyses individually.
	    // For instance, this gets the parse tree of the first sentence in the text.
	    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
	    if (sentences != null && sentences.size() > 0) {
	      CoreMap sentence = sentences.get(0);
	      Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
		  model.addAttribute("tree", tree.pennString());
	    }		
		return "parse/index";
	}
	
	public static void dump(String message, Annotation[] as) {
		System.out.println(message);
		for (Annotation a : as) {
			System.out.println(a);
		}
	}

}
