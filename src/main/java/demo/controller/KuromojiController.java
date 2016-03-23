package demo.controller;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
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
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.ja.JapaneseAnalyzer;
import org.apache.lucene.analysis.ja.JapaneseTokenizer;
import org.apache.lucene.analysis.ja.tokenattributes.PartOfSpeechAttribute;
import org.apache.lucene.analysis.ja.tokenattributes.ReadingAttribute;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

import demo.dto.Status;

@EnableAutoConfiguration
@EnableConfigurationProperties
@Controller
@RequestMapping("/kuromoji")
public class KuromojiController {
	@Autowired
	private ApplicationContext context;
	
	/**
	 * curl -d text="日本経済新聞でうちの猫が掲載された" -d pass="pass" http://localhost:8080/kuromoji/parse
	 * curl -d text="Now My Cat is sleeping" -d pass="pass" http://localhost:8080/kuromoji/parse
	 * */
	@RequestMapping(value="parse",method=RequestMethod.POST)
	public @ResponseBody Status cmsStartAuth(@RequestParam(value="text", required=true, defaultValue="Stranger") String text) {
		// TextをReaderに変換
        Reader reader = new StringReader(text);
 		// StandardAnalyzerで解析
        //TokenStream stream = new StandardTokenizer(Version.LUCENE_36, reader);
        //JapaneseAnalyzerで解析（userdicはnull、ModeはNORMALを指定）
		try {
	        // JapaneseAnalyzerで解析（userdicはnull、ModeはNORMALを指定）
			JapaneseTokenizer stream = new JapaneseTokenizer(null, true,JapaneseTokenizer.Mode.NORMAL);
			stream.setReader(reader);
			stream.reset();
	      while (stream.incrementToken()) {
	    	  CharTermAttribute termAtt = stream.getAttribute(CharTermAttribute.class);
				ReadingAttribute rAtt = stream.getAttribute(ReadingAttribute.class);
				OffsetAttribute oAtt = stream.getAttribute(OffsetAttribute.class);
				PartOfSpeechAttribute psAtt = stream.getAttribute(PartOfSpeechAttribute.class);
				
				String str = termAtt.toString();
				String yomi = rAtt.getReading();
				int sOffset = oAtt.startOffset();
				int eOffset = oAtt.endOffset();
				String pos = psAtt.getPartOfSpeech();
				
				System.out.println(
						"|" + str + "\t\t" +
						"|" + Integer.toString(sOffset) + "\t" + 
						"|" + Integer.toString(eOffset) + "\t" +
						"|" + yomi + "\t\t" + 
						"|" + pos + "\t"
						);
	      }
	      
		} catch (Exception e) {
			System.out.println(e.getMessage());
	    }
	    return new Status("OK");
	}
}