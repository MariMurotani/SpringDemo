package demo.libs;

import java.util.List;

import org.springframework.context.ApplicationContext;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;


public class EnglishParser {

	public String doParse(String value){
		StanfordCoreNLP pipeline = new StanfordCoreNLP();
		Annotation annotation;
	    annotation = new Annotation(value.trim());
	    
	    pipeline.annotate(annotation);
	    
	    Tree tree = null;
	    // An Annotation is a Map and you can get and use the various analyses individually.
	    // For instance, this gets the parse tree of the first sentence in the text.
	    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
	    if (sentences != null && sentences.size() > 0) {
	      CoreMap sentence = sentences.get(0);
	      tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
	    }
	    String result = "";
	    if(tree != null){
	    	result = tree.pennString();
	    }
	    return result;
	}
}
