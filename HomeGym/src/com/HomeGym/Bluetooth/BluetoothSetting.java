package com.HomeGym.Bluetooth;

import java.util.LinkedList;

import com.HomeGym.Bluetooth.BluetoothSerialClient.OnScanListener;
import com.example.homegym.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BluetoothSetting {
	
	private Context btContext;
	private BluetoothStreamingHandler mBTHandler;
	private AlertDialog mDeviceListDialog;
	private LinkedList<BluetoothDevice> mBluetoothDevices = new LinkedList<BluetoothDevice>();
	private ArrayAdapter<String> mDeviceArrayAdapter;
	private ProgressDialog mLoadingDialog;
	
	public BluetoothSetting(Context applicationContext){
		// TODO Auto-generated constructor stub
		btContext = applicationContext;
		mBTHandler= new BTHandler();	
	}
	

	public void setDialog(){
		boolean connect = BluetoothSerialClient.getInstance().isConnection();
		if (!connect) {
				mDeviceListDialog.show();				
		} else {
				mBTHandler.close();
		}
	}

	public void sendStringData(String data) {

		data += '\0';
		byte[] buffer = data.getBytes();
		if(mBTHandler.write(buffer)) {
			Log.i("鞠澗暗艦", "左蛎柔艦雁");
		}
	}

	public void initDeviceListDialog() {
		mDeviceArrayAdapter = new ArrayAdapter<String>(btContext,R.layout.item_device);
		Log.v("更醤更醤", "訊照鞠澗汽1?");
		ListView listView = new ListView(btContext);
		listView.setAdapter(mDeviceArrayAdapter);
		Log.v("更醤更醤", "訊照鞠澗汽?2");
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.v("更醤更醤", "訊照鞠澗汽?3");
				String item =  (String) parent.getItemAtPosition(position); 
				for(BluetoothDevice device : mBluetoothDevices) {
					if(item.contains(device.getAddress())) {
						Log.v("更醤更醤", "訊照鞠澗汽?4");
						connect(device);
						mDeviceListDialog.cancel();
					}
				}
			}
		});
		AlertDialog.Builder builder = new AlertDialog.Builder(btContext);
		builder.setTitle("Select bluetooth device");
		builder.setView(listView);
		Log.v("更醤更醤", "訊照鞠澗汽6?");
		builder.setPositiveButton("Scan",new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int id) {
			  Log.v("更醤更醤", "酔酔酔焼びし虞っけ;ちだじ君;け岐だじ君;だじ�老�?");
			  scanDevices();
		  }
		 });
		mDeviceListDialog = builder.create();
		Log.v("更醤更醤", "訊照鞠澗汽7?");
		mDeviceListDialog.setCanceledOnTouchOutside(false);
	}
	

	public void initProgressDialog() {
		 mLoadingDialog = new ProgressDialog(btContext);
		 mLoadingDialog.setCancelable(true);
	}
	
	
	public void connect(BluetoothDevice device) {
		//mLoadingDialog.setMessage("Connecting....");
		//mLoadingDialog.setCancelable(false);
		//mLoadingDialog.show();
		Log.v("更醤更醤", "訊照鞠澗汽5?");
		BluetoothSerialClient btSet =  BluetoothSerialClient.getInstance();
		
		if(btSet.connect(btContext, device, mBTHandler)==true){
			alertDialog(true);
			String sString = "0";// 紳亀 閤焼神奄
			sendStringData(sString);
			
		}else{
			alertDialog(false);
		}
	}
	
	public void alertDialog(boolean isConnected){
		AlertDialog.Builder builder = new AlertDialog.Builder(btContext);     // 食奄辞 this澗 Activity税 this

		// 食奄辞 採斗澗 硝顕但税 紗失 竺舛
		if(isConnected==true){
		builder.setTitle("Connecting...")        // 薦鯉 竺舛
		.setMessage("鷺欠燈亜 尻衣鞠醸柔艦雁")        // 五室走 竺舛
		.setCancelable(false)        // 及稽 獄動 適遣獣 昼社 亜管 竺舛

		.setPositiveButton("溌昔", new DialogInterface.OnClickListener(){       

		 // 溌昔 獄動 適遣獣 竺舛
			public void onClick(DialogInterface dialog, int whichButton){
				dialog.cancel();
			}
		});

		AlertDialog dialog = builder.create();    // 硝顕但 梓端 持失

		dialog.show();    // 硝顕但 句酔奄
		}else{
			builder.setTitle("Connecting...")        // 薦鯉 竺舛
			.setMessage("鷺欠燈亜 尻衣鞠醸柔艦雁")        // 五室走 竺舛
			.setCancelable(false)        // 及稽 獄動 適遣獣 昼社 亜管 竺舛

			.setPositiveButton("溌昔", new DialogInterface.OnClickListener(){       

			 // 溌昔 獄動 適遣獣 竺舛
				public void onClick(DialogInterface dialog, int whichButton){
					dialog.cancel();
				}
			});


			AlertDialog dialog = builder.create();    // 硝顕但 梓端 持失

			dialog.show();    // 硝顕但 句酔奄
			}		
	}
	
	public void scanDevices() {
		Log.v("更醤更醤", "什諜 巨郊戚什 脊艦陥?");
		BluetoothSerialClient btSet = BluetoothSerialClient.getInstance();
		btSet.scanDevices(btContext, new OnScanListener() {
			String message ="";
			@Override
			public void onStart() {
				Log.d("Test", "Scan Start.");
				mLoadingDialog.show();
				message = "Scanning....";
				mLoadingDialog.setMessage("Scanning....");
				mLoadingDialog.setCancelable(true);
				mLoadingDialog.setCanceledOnTouchOutside(true);				
				mLoadingDialog.setOnCancelListener(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						BluetoothSerialClient btSet = BluetoothSerialClient.getInstance();
						btSet.cancelScan(btContext);
					}
				}); 
			}
			
			@Override
			public void onFoundDevice(BluetoothDevice bluetoothDevice) {
				addDeviceToArrayAdapter(bluetoothDevice);
				message += "\n" + bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress();
				mLoadingDialog.setMessage(message);
			}
			
			@Override
			public void onFinish() {
				Log.d("Test", "Scan finish.");
				message = "";
				mLoadingDialog.cancel();
				mLoadingDialog.setCancelable(false);
				mLoadingDialog.setOnCancelListener(null);
				mDeviceListDialog.show();
				Log.v("更醤更醤", "訊照鞠澗汽8?");
			}
		});
	}
	
	public void addDeviceToArrayAdapter(BluetoothDevice device) {
		if(mBluetoothDevices.contains(device)) { 
			mBluetoothDevices.remove(device);
			mDeviceArrayAdapter.remove(device.getName() + "\n" + device.getAddress());
		}
			mBluetoothDevices.add(device);
			mDeviceArrayAdapter.add(device.getName() + "\n" + device.getAddress() );
			mDeviceArrayAdapter.notifyDataSetChanged();
		
	}




	
}





