package br.com.devmedia.blog.util;

import java.text.Normalizer;

public class ReplaceString {

	public static String formatarPermalink(String value) {
		
		// Persist�ncia com JPA! -> persistencia_com_jpa
		
		String link = value.trim();
		
		link = link.toLowerCase();
		
		link = Normalizer.normalize(link, Normalizer.Form.NFD);
		
		link = link.replaceAll("\\s", "_");
		
		link = link.replaceAll("\\W", "");
		
		link = link.replaceAll("\\_+", "_");
		
		return link;
	}
}
