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
	
	//΢��appid
	private final static String APP_ID = "wx5c734d1f6b8e6db3";
	
	private Button button = null;
	
	//������app��΢��ͨ�ŵĽӿ�
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
	
	//ע�ᵽ΢��
	protected void regToWx() {
		//ͨ��WXAPIFactory��������ȡIWXAPIʵ��
		api = WXAPIFactory.createWXAPI(this, APP_ID, true);
		api.registerApp(APP_ID);
	}

	protected void sendToWx(){
		//��ʼ��һ��WXTextObject����
		WXTextObject textObj = new WXTextObject();
		textObj.text = "textObj";
		
		//��WXTextObjext�����ʼ��һ��WXMediaMessage����
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = textObj;
		msg.description ="���Զ����ɱ�ǩ�ķ�������";
		
		//����һ��Req
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = String.valueOf(System.currentTimeMillis());
		req.message = msg;
		
		//����api�ӿڷ������ݵ�΢��
		api.sendReq(req);
	}
	
	
}
