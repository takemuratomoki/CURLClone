/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package curlclone;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.lang.StringBuilder;

public class App {

    public static void main(String[] args) {
    	String commm = "-v -d key=value http://httpbin.org/post";
        HTTPRequest h = new HTTPRequest();
        h.run(commm);
    }
}

class HTTPRequest{
	public void run(String command) {
		
		String filename = "";
		boolean Option[]= {false,false,false,false};
		FileWriter fw = null;
		
		//コマンド処理
		Pattern p = Pattern.compile("[\\s]+");
		String com[] = p.split(command);
		
		String strUrl = com[com.length-1];
		
		HttpURLConnection urlConn = null;
		InputStream in = null;
		BufferedReader reader = null;
		
		try {
			//url指定
			URL url = new URL(strUrl);
			
			//コネクション取得
			urlConn = (HttpURLConnection) url.openConnection();

			//初期状態としてGET
			urlConn.setRequestMethod("GET");
			

			
			//コマンド判定
			for(int i = 0 ; i < com.length -1 ; i ++ ) {
				
				if(com[i].charAt(0) == '-') {
				
					switch(com[i].charAt(1)) {
						case 'o'://ファイル出力を指定
							Option[0]=true;
							break;
							
						case 'v'://リクエスト、レスポンスのヘッダ、ボディを表示
							Option[1]=true;
							break;
							
						case 'X'://Postの指定
							Option[2]=true;
							
							if(com[i+1].equals("POST")) {
								urlConn.setRequestMethod("POST");
							}
							break;
							
						case 'd'://パラメーター付きポスト送信？
							Option[3]=true;
							break;
							
					}
				}			
			}
			
			//オプションの実行
			if(Option[0]) {//-o
				
			}
			if(Option[1]) {//-v
				//リクエストヘッダ取得(-vオプション？)
				Map<String , String> reqheaders = new HashMap<String , String>();
				reqheaders.put("HOST", url.getHost());
				reqheaders.put("Method", urlConn.getRequestMethod());
				Iterator reqheaderIt = reqheaders.keySet().iterator();
				String reqheader = "";
				while(reqheaderIt.hasNext()) {
					String headerKey = (String)reqheaderIt.next();
					reqheader += ">" + headerKey + ":" + reqheaders.get(headerKey)+"\r\n";
				}
				System.out.print(reqheader + "\r\n");
				
			}
			if(Option[2]) {//-X

			}
			if(Option[3]) {//-d
				urlConn.setRequestMethod("POST");
				urlConn.setDoOutput(true);
			}
			
			//コネクト実行
			urlConn.connect();
			
			
			//コネクト実行後オプションの実行
			if(Option[0]) {//-o
				
			}
			if(Option[1]) {//-v
				
			}
			if(Option[2]) {//-X
				
			}
			if(Option[3]) {//-d
				OutputStream os = urlConn.getOutputStream();//post用のoutputstreamを取得
				
				//Postするデータを入力
				String postStr = "key=value&key1=value1";
				PrintStream ps = new PrintStream(os);
				//データをPost
				ps.print(postStr);
				ps.close();
			}
			
			//以下読み出し
			int status = urlConn.getResponseCode();
//			
//			System.out.println("HTTPステータス："+ status);
			
			if(status == HttpURLConnection.HTTP_OK) {
				

				
				in = urlConn.getInputStream();
				
				InputStreamReader testes = new InputStreamReader(in);
				
				reader = new BufferedReader(new InputStreamReader(in));
				
				StringBuilder output = new StringBuilder();
				String line;
				

				//ステータスクリア後オプションの実行
				if(Option[1]) {//-v
					//レスポンスヘッダ取得(-vオプション？)
					Map resheaders = urlConn.getHeaderFields();
					Iterator resheaderIt = resheaders.keySet().iterator();
					String resheader = "";
					while(resheaderIt.hasNext()) {
						String headerKey = (String)resheaderIt.next();
						resheader += "<" + headerKey + ":" + resheaders.get(headerKey)+"\r\n";
					}
					System.out.print(resheader +"\r\n");
				}
				if(Option[2]) {//-X
					
				}
				if(Option[3]) {//-d
					
				}
				

				
				//ファイル出力�@
				if(Option[0]) {//-o
					fw = new FileWriter("testfilename.txt");
				}
				
				while((line = reader.readLine()) != null) {
//					output.append(line);
					System.out.println(line+"\n");
					
					//ファイル出力�A
					if(Option[0]) {
						fw.write(line+"\r\n");
					}
				}
				
				System.out.println(output.toString());
				
				//ファイル出力�B
				if(Option[0]) {
					fw.close();
				}
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(reader != null) {
					reader.close();
				}
				if(urlConn != null) {
					urlConn.disconnect();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
//	//オプションの実行
//	if(Option[0]) {//-o
//		
//	}
//	if(Option[1]) {//-v
//		
//	}
//	if(Option[2]) {//-X
//		
//	}
//	if(Option[3]) {//-d
//		
//	}
}