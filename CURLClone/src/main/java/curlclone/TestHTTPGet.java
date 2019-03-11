//package curlclone;
//
//import java.net.URL;
//import java.net.URLConnection;
//import java.io.InputStreamReader;
//import java.io.PrintStream;
//import java.io.BufferedReader;
//
//public class TestHTTPGet {
//	
//	public static void main(String[] args) {
//	
//	URL TestURL= null;
//	String msgbuf = "";
//	BufferedReader inmsg = null;
//	
//	System.out.println("-----Connect-----");
//	
//	try {
//		TestURL = new URL("https://www.yahoo.co.jp/");
//		
//		//接続と出力設定
//		URLConnection con = TestURL.openConnection();
//		
//		con.setDoOutput(true);
//		
//		//出力ストリームを取得
//		PrintStream out = new PrintStream(con.getOutputStream());
//		
//		//出力
//		msgbuf = "bun=This is Test PROGRAM";
//		out.print(msgbuf);
//		out.close();
//		
//		inmsg = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		
//		String line;
//		System.out.println("-----result-----");
//		while((line = inmsg.readLine()) != null) {
//			//コンソール出力
//			System.out.println(line);
//		}
//		System.out.println("-----end-----");
//		
//		inmsg.close();
//	}catch (Exception e) {
//		System.out.println("-----Error Occured-----");
//		e.printStackTrace();
//		System.out.println("-----abnomally end-----");
//	}
//	
//	}
//}
