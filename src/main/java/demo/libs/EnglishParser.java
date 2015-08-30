package demo.libs;

import java.io.PrintStream;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.ApplicationContext;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

@Getter
@Setter
public class EnglishParser extends Thread{
private String text;
private String reult;

public interface MyCallback {
    void callbackCall(String text);
}
private MyCallback callback;


	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		super.run();
		
		StanfordCoreNLP pipeline = new StanfordCoreNLP();
		Annotation annotation;
	    annotation = new Annotation(text.trim());
	    
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
	    callback.callbackCall(result);
	}

}
