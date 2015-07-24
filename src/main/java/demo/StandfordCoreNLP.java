package demo;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

@EnableAutoConfiguration
@EnableBatchProcessing
@EnableConfigurationProperties
public class StandfordCoreNLP implements CommandLineRunner{
		//	get Applicateion context Example2
		@Autowired
		private ApplicationContext context;
		
		@Autowired
		private Environment env;

		public static void main(String[] args) {
			//SpringApplication.run(MongExample.class, args);
			
			SpringApplication application = new SpringApplication(StandfordCoreNLP.class);
			application.setWebEnvironment(false);
			ApplicationContext context = application.run(args);
			SpringApplication.exit(context);
		}
	   
		@Override
		public void run(String... args) throws Exception {
			PrintWriter out;
		    if (args.length > 1) {
		      out = new PrintWriter(args[1]);
		    } else {
		      out = new PrintWriter(System.out);
		    }
		    PrintWriter xmlOut = null;
		    if (args.length > 2) {
		      xmlOut = new PrintWriter(args[2]);
		    }

		    StanfordCoreNLP pipeline = new StanfordCoreNLP();
		    Annotation annotation;
		    if (args.length > 0) {
		      annotation = new Annotation(IOUtils.slurpFileNoExceptions(args[0]));
		    } else {
		      annotation = new Annotation("Kosgi Santosh sent an email to Stanford University. He didn't get a reply.");
		    }

		    pipeline.annotate(annotation);
		    pipeline.prettyPrint(annotation, out);
		    if (xmlOut != null) {
		      pipeline.xmlPrint(annotation, xmlOut);
		    }
		    // An Annotation is a Map and you can get and use the various analyses individually.
		    // For instance, this gets the parse tree of the first sentence in the text.
		    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		    if (sentences != null && sentences.size() > 0) {
		      CoreMap sentence = sentences.get(0);
		      Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
		      out.println();
		      out.println("The first sentence parsed is:");
		      tree.pennPrint(out);
		    }
		}
	}