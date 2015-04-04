package com.readboy.multshare;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;

public class MainActivity extends Activity {
	
	//微信appid
	private final static String APP_ID = "wx5c734d1f6b8e6db3";
	
	private Button button = null;
	
	//第三方app和微信通信的接口
	private IWXAPI api = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button = (Button)findViewById(R.id.share_button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				regToWx();
				sendToWx();
			}
		});
	}
	
	//注册到微信
	protected void regToWx() {
		//通过WXAPIFactory工厂，获取IWXAPI实例
		api = WXAPIFactory.createWXAPI(this, APP_ID, true);
		api.registerApp(APP_ID);
	}

	protected void sendToWx(){
		//初始化一个WXTextObject对象
		WXTextObject textObj = new WXTextObject();
		textObj.text = "textObj";
		
		//用WXTextObjext对象初始化一个WXMediaMessage对象
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = textObj;
		msg.description ="来自读书郎便签的分享内容";
		
		//构造一个Req
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = String.valueOf(System.currentTimeMillis());
		req.message = msg;
		
		//调用api接口发送数据到微信
		api.sendReq(req);
	}
	
	
}
