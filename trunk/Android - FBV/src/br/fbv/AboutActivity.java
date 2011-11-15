package br.fbv;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutActivity extends Activity {

	WebView webView;
	
	private class browserNotes extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		webView = (WebView) findViewById(R.id.webView);
		webView.setWebViewClient(new browserNotes());
		webView.loadUrl("http://www.google.com.br");
	}

}
