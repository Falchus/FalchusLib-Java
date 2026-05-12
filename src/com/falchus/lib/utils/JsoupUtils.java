package com.falchus.lib.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsoupUtils {
	
	public static String getScriptDataById(@NonNull String html, @NonNull String id) {
		Document doc = Jsoup.parse(html);
		Element script = doc.getElementById(id);
		return script != null
				? script.data()
				: null;
	}
}
