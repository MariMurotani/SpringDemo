package utils.transition;

import java.util.Comparator;
import java.util.Map;

import org.springframework.util.PathMatcher;

public class ExtendedAntPathMatcher implements PathMatcher {

	@Override
	public boolean isPattern(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean match(String pattern, String path) {
		// TODO Auto-generated method stub
		System.out.println(pattern);
		System.out.println(path);
		return false;
	}

	@Override
	public boolean matchStart(String pattern, String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String extractPathWithinPattern(String pattern, String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> extractUriTemplateVariables(String pattern,
			String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<String> getPatternComparator(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String combine(String pattern1, String pattern2) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
