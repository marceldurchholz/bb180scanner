/*
The MIT License (MIT)

Copyright (c) 2013 marceldurchholz - info@digitalverve.de

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package io.github.marceldurchholz.cordova.plugins.bb180scanner;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


// from test app
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


// import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
//import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CordovaResourceApi;


public class BBAPI {
	
	//+ RESULT
	public static final int BBAPI_SUCCESS				= 0;
	public static final int BBAPI_FAILED				= -1;
	public static final int BBAPI_NOT_SUPPORTED			= -2;
	public static final int BBAPI_ERROR_CHECKSUM		= -3;
	public static final int BBAPI_ERROR_NO_RESPONSE		= -4;
	public static final int BBAPI_BATTERY_LOW			= -5;
	//MSR
	public static final int BBAPI_ERROR_MSR_TIMEOUT			= -6;
	public static final int BBAPI_ERROR_MSR_BAD_DATA		= -7;
	public static final int BBAPI_ERROR_MSR_NO_DATA			= -8;
	public static final int BBAPI_ERROR_MSR_BUSY			= -9;
	public static final int BBAPI_ERROR_MSR_COPY_FAULT		= -10;
	//Barcode
	public static final int BBAPI_ERROR_BARCODE_DECODING_TIMEOUT	= -6;
	public static final int BBAPI_ERROR_BARCODE_USE_TIMEOUT			= -7;
	public static final int BBAPI_ERROR_BARCODE_ALREADY_OPENED		= -8;
	public static final int BBAPI_ERROR_BARCODE_CAMERA_USED			= -9;
	//- RESULT
	
	//+ DEVICE KEY CODE
	public static final int KEYCODE_SCAN_PRESS = 0;
	public static final int KEYCODE_SCAN_RELEASE = 1;
	public static final int KEYCODE_PTT_PRESS = 2;
	public static final int KEYCODE_PTT_RELEASE = 3;
	public static final int KEYCODE_VOLUME_PRESS = 4;
	public static final int KEYCODE_VOLUME_RELEASE = 5;
	//- DEVICE KEY CODE
	
	//+ MSR MODE
	public static final int MSR_MODE_READING_TIMEOUT			= 0;
	//- MSR MODE
	
	//+ BARCODE MODE
	public static final int BARCODE_MODE_PREFIX					= 500;
	public static final int BARCODE_MODE_SUFFIX 				= BARCODE_MODE_PREFIX + 1;
	public static final int BARCODE_MODE_SOUND 					= BARCODE_MODE_PREFIX + 2;
	public static final int BARCODE_MODE_TRIGGER 				= BARCODE_MODE_PREFIX + 3;
	public static final int BARCODE_MODE_AIMER 					= BARCODE_MODE_PREFIX + 4;
	public static final int BARCODE_MODE_ILLUMINATION 			= BARCODE_MODE_PREFIX + 5;
	public static final int BARCODE_MODE_DECODE_TIMEOUT 		= BARCODE_MODE_PREFIX + 6;
	public static final int BARCODE_MODE_SAME_SYMBOL_TIMEOUT 	= BARCODE_MODE_PREFIX + 7;
	
	public static final int BARCODE_MODE_POSTAMBLE 							= BARCODE_MODE_PREFIX + 43;
	public static final int BARCODE_MODE_SUBAMBLE 							= BARCODE_MODE_PREFIX + 44;
	public static final int BARCODE_MODE_DATA_WEDGE 						= BARCODE_MODE_PREFIX + 45;
	public static final int BARCODE_MODE_USE_TIMEOUT						= BARCODE_MODE_PREFIX + 46;
	public static final int BARCODE_MODE_DATA_WEDGE_TYPE 					= BARCODE_MODE_PREFIX + 47;
	//- BARCODE MODE
	
	//+ BARCODE SYMBOLOGY
	public static final int SYMBOLOGY_UPC_A 							= 1;
	public static final int SYMBOLOGY_UPC_E 							= 2;
	public static final int SYMBOLOGY_UPC_E1 							= 3;
	public static final int SYMBOLOGY_EAN8 								= 4;
	public static final int SYMBOLOGY_EAN13 							= 5;
	public static final int SYMBOLOGY_BOOKLAND 							= 6;
	public static final int SYMBOLOGY_SUPPLEMENTAL_CODE 				= 7;
	public static final int SYMBOLOGY_CODE39 							= 8;
	public static final int SYMBOLOGY_CODE93 							= 9;
	public static final int SYMBOLOGY_CODE128 							= 10;
	public static final int SYMBOLOGY_INTERLEAVED2OF5 					= 11;
	public static final int SYMBOLOGY_CODABAR 							= 12;
	public static final int SYMBOLOGY_CODE11 							= 13;
	public static final int SYMBOLOGY_MSI 								= 14;
	public static final int SYMBOLOGY_GS1 								= 15;
	public static final int SYMBOLOGY_PDF417 							= 16;
	public static final int SYMBOLOGY_ISBT128 							= 17;
	public static final int SYMBOLOGY_COMPOSITE_CC_C 					= 18;
	public static final int SYMBOLOGY_MATRIX2OF5 						= 19;
	public static final int SYMBOLOGY_DATAMATRIX 						= 20;
	public static final int SYMBOLOGY_MAXICODE 							= 21;
	public static final int SYMBOLOGY_AZTECCODE 						= 22;
	public static final int SYMBOLOGY_MICROPDF 							= 23;
	public static final int SYMBOLOGY_QRCODE 							= 24;
	public static final int SYMBOLOGY_TRIOPTIC_CODE 					= 25;
	public static final int SYMBOLOGY_DISCRETE2OF5 						= 26;
	public static final int SYMBOLOGY_USPS4CB 							= 27;
	public static final int SYMBOLOGY_AUSTRALIA_POST 					= 28;
	public static final int SYMBOLOGY_UK_POST 							= 29;
	public static final int SYMBOLOGY_CHINESE_POST 						= 30;
	public static final int SYMBOLOGY_JAPANESE_POST 					= 31;
	public static final int SYMBOLOGY_NETHERLANDS_POST 					= 32;
	public static final int SYMBOLOGY_KOREAN_POST 						= 33;
	public static final int SYMBOLOGY_US_POSTNET 						= 34;
	public static final int SYMBOLOGY_US_PLANET 						= 35;
	
	public static final int SYMBOLOGY_UPC_A_PREAMBLE 					= 36;
	public static final int SYMBOLOGY_UPC_A_TRANSMIT_CHECK_DIGIT 		= 37;
	public static final int SYMBOLOGY_UPC_E_PREAMBLE 					= 38;
	public static final int SYMBOLOGY_UPC_E_TRANSMIT_CHECK_DIGIT 		= 39;
	public static final int SYMBOLOGY_UPC_E1_PREAMBLE 					= 40;
	public static final int SYMBOLOGY_UPC_E1_TRANSMIT_CHECK_DIGIT 		= 41;
	public static final int SYMBOLOGY_EAN8_EXTEND 						= 42;
	public static final int SYMBOLOGY_EAN_TRANSMIT_ISSN 				= 43;
	public static final int SYMBOLOGY_BOOKLAND_ISBN 					= 44;
	public static final int SYMBOLOGY_SUPPLEMENTAL_REDUNDANCY 			= 45;
	public static final int SYMBOLOGY_SUPPLEMENTAL_AIM_ID 				= 46;
	public static final int SYMBOLOGY_CODE39_LENGTH_MIN 				= 47;
	public static final int SYMBOLOGY_CODE39_LENGTH_MAX 				= 48;
	public static final int SYMBOLOGY_CODE39_CHECK_DIGIT 				= 49;
	public static final int SYMBOLOGY_CODE39_TRANSMIT_CHECK_DIGIT 		= 50;
	public static final int SYMBOLOGY_CODE39_FULL_ASCII 				= 51;
	public static final int SYMBOLOGY_CODE93_LENGTH_MIN 				= 52;
	public static final int SYMBOLOGY_CODE93_LENGTH_MAX 				= 53;
	public static final int SYMBOLOGY_CODE128_LENGTH_MIN 				= 54;
	public static final int SYMBOLOGY_CODE128_LENGTH_MAX 				= 55;
	public static final int SYMBOLOGY_CODE128_EMULATION 				= 56;
	public static final int SYMBOLOGY_INTERLEAVED2OF5_LENGTH_MIN 		= 57;
	public static final int SYMBOLOGY_INTERLEAVED2OF5_LENGTH_MAX 		= 58;
	public static final int SYMBOLOGY_INTERLEAVED2OF5_CHECK_DIGIT 		= 59;
	public static final int SYMBOLOGY_INTERLEAVED2OF5_TRANSMIT_CHECK_DIGIT = 60;
	public static final int SYMBOLOGY_CODABAR_LENGTH_MIN 				= 61;
	public static final int SYMBOLOGY_CODABAR_LENGTH_MAX 				= 62;
	public static final int SYMBOLOGY_CODABAR_CLSI_EDITING 				= 63;
	public static final int SYMBOLOGY_CODABAR_NOTIS_EDITING 			= 64;
	public static final int SYMBOLOGY_CODE11_LENGTH_MIN 				= 65;
	public static final int SYMBOLOGY_CODE11_LENGTH_MAX 				= 66;
	public static final int SYMBOLOGY_CODE11_CHECK_DIGIT 				= 67;
	public static final int SYMBOLOGY_CODE11_TRANSMIT_CHECK_DIGIT 		= 68;
	public static final int SYMBOLOGY_MSI_LENGTH_MIN 					= 69;
	public static final int SYMBOLOGY_MSI_LENGTH_MAX 					= 70;
	public static final int SYMBOLOGY_MSI_CHECK_DIGIT 					= 71;
	public static final int SYMBOLOGY_MSI_TRANSMIT_CHECK_DIGIT 			= 72;
	public static final int SYMBOLOGY_MSI_CHECK_DIGIT_ALGORITHM 		= 73;
	public static final int SYMBOLOGY_GS1_LIMITED 						= 74;
	public static final int SYMBOLOGY_GS1_LIMITED_SECURITY_LEVEL 		= 75;
	public static final int SYMBOLOGY_ISBT128_CONCATENATION 			= 76;
	public static final int SYMBOLOGY_ISBT128_CHECK_TABLE 				= 77;
	public static final int SYMBOLOGY_ISBT128_CONCATENATION_REDUNDANCY 	= 78;
	public static final int SYMBOLOGY_MATRIX2OF5_LENGTH_MIN 			= 79;
	public static final int SYMBOLOGY_MATRIX2OF5_LENGTH_MAX 			= 80;
	public static final int SYMBOLOGY_MATRIX2OF5_SUPPLEMENTAL_REDUNDANCY = 81;
	public static final int SYMBOLOGY_MATRIX2OF5_CHECK_DIGIT 			= 82;
	public static final int SYMBOLOGY_MATRIX2OF5_TRANSMIT_CHECK_DIGIT 	= 83;
	public static final int SYMBOLOGY_COMPOSITE_CC_AB 					= 84;
	public static final int SYMBOLOGY_COMPOSITE_TLC_39 					= 85;
	public static final int SYMBOLOGY_COMPOSITE_UPC 					= 86;
	public static final int SYMBOLOGY_DATAMATRIX_INVERSE 				= 87;
	public static final int SYMBOLOGY_DATAMATRIX_ONLY 					= 88;
	public static final int SYMBOLOGY_DISCRETE2OF5_LENGTH_MIN 			= 89;
	public static final int SYMBOLOGY_DISCRETE2OF5_LENGTH_MAX 			= 90;
	public static final int SYMBOLOGY_US_TRANSMIT_CHECK_DIGIT 			= 91;
	public static final int SYMBOLOGY_QRCODE_INVERSE 					= 92;
	public static final int SYMBOLOGY_SPECIFIC_SECURITY 				= 93;
	public static final int SYMBOLOGY_SPECIFIC_INTERCHARACTER 			= 94;
	public static final int SYMBOLOGY_COUPON_REPORT 					= 95;
	public static final int SYMBOLOGY_CONVERT_UPCE_TO_A 				= 96;
	public static final int SYMBOLOGY_CONVERT_UPCE1_TO_A 				= 97;
	public static final int SYMBOLOGY_CONVERT_CODE39_TO_32 				= 98;
	public static final int SYMBOLOGY_CONVERT_I2OF5_TO_EAN13 			= 99;
	public static final int SYMBOLOGY_CONVERT_GS1_TO_UPCEAN 			= 100;
	public static final int SYMBOLOGY_GS1_DATABAR_EXPANDED 				= 101;
	public static final int SYMBOLOGY_GS1_128_EMULATION_FOR_UCC_COMPOSITE_CODE = 102;
	public static final int SYMBOLOGY_INVERSE_1D 						= 103;
	public static final int SYMBOLOGY_UPU_FICS_POSTAL 					= 104;
	public static final int SYMBOLOGY_UPC_COMPOSITE_MODE 				= 105;
	public static final int SYMBOLOGY_AZTEC_INVERSE 					= 106;
	public static final int SYMBOLOGY_SPECIFIC_REDUNDANCY_LEVEL 		= 107;
	public static final int SYMBOLOGY_AUSTRALIA_POST_FORMAT 			= 108;
	public static final int SYMBOLOGY_TRANSMIT_UK_POST_CHECK_DIGIT 		= 109;
	public static final int SYMBOLOGY_CODE32_PREFIX 					= 110;
	public static final int SYMBOLOGY_USER_SUPPLEMENTAL_1 				= 111;
	public static final int SYMBOLOGY_USER_SUPPLEMENTAL_2 				= 112;
	public static final int SYMBOLOGY_MICROQR							= 113;
	
	public static final int SYMBOLOGY_HANXIN 							= 134;
	public static final int SYMBOLOGY_HANXIN_INVERSE 					= 135;
	//- BARCODE SYMBOLOGY
}




public class Constants {
	
	public static final String ACTION_BARCODE_OPEN = "kr.co.bluebird.android.bbapi.action.BARCODE_OPEN";
	public static final String ACTION_BARCODE_CLOSE = "kr.co.bluebird.android.bbapi.action.BARCODE_CLOSE";
	public static final String ACTION_BARCODE_SET_TRIGGER = "kr.co.bluebird.android.bbapi.action.BARCODE_SET_TRIGGER";
	public static final String ACTION_BARCODE_SET_DEFAULT_PROFILE = "kr.co.bluebird.android.bbapi.action.BARCODE_SET_DEFAULT_PROFILE";
	public static final String ACTION_BARCODE_SETTING_CHANGED = "kr.co.bluebird.android.bbapi.action.BARCODE_SETTING_CHANGED";
	public static final String ACTION_BARCODE_CALLBACK_REQUEST_SUCCESS = "kr.co.bluebird.android.bbapi.action.BARCODE_CALLBACK_REQUEST_SUCCESS";
	public static final String ACTION_BARCODE_CALLBACK_REQUEST_FAILED = "kr.co.bluebird.android.bbapi.action.BARCODE_CALLBACK_REQUEST_FAILED";
	public static final String ACTION_BARCODE_CALLBACK_DECODING_DATA = "kr.co.bluebird.android.bbapi.action.BARCODE_CALLBACK_DECODING_DATA";
	public static final String ACTION_BARCODE_GET_STATUS = "kr.co.bluebird.android.action.BARCODE_GET_STATUS";
	public static final String ACTION_BARCODE_CALLBACK_GET_STATUS = "kr.co.bluebird.android.action.BARCODE_CALLBACK_GET_STATUS";
	
	public static final String EXTRA_BARCODE_BOOT_COMPLETE = "EXTRA_BARCODE_BOOT_COMPLETE";
	public static final String EXTRA_BARCODE_PROFILE_NAME = "EXTRA_BARCODE_PROFILE_NAME";
	public static final String EXTRA_BARCODE_TRIGGER = "EXTRA_BARCODE_TRIGGER";
	public static final String EXTRA_BARCODE_DECODING_DATA = "EXTRA_BARCODE_DECODING_DATA";
	public static final String EXTRA_HANDLE = "EXTRA_HANDLE";
	public static final String EXTRA_INT_DATA2 = "EXTRA_INT_DATA2";
	public static final String EXTRA_STR_DATA1 = "EXTRA_STR_DATA1";
	public static final String EXTRA_INT_DATA3 = "EXTRA_INT_DATA3";
	
	//20140527
	public static final String ACTION_BARCODE_SET_PARAMETER = "kr.co.bluebird.android.bbapi.action.BARCODE_SET_PARAMETER";
	public static final String ACTION_BARCODE_GET_PARAMETER = "kr.co.bluebird.android.bbapi.action.BARCODE_GET_PARAMETER";
	public static final String ACTION_BARCODE_CALLBACK_PARAMETER = "kr.co.bluebird.android.bbapi.action.BARCODE_CALLBACK_PARAMETER";
	
	public static final int ERROR_FAILED				= -1;
	public static final int ERROR_NOT_SUPPORTED			= -2;
	public static final int ERROR_NO_RESPONSE			= -4;
	public static final int ERROR_BATTERY_LOW			= -5;
	public static final int ERROR_BARCODE_DECODING_TIMEOUT			= -6;
	public static final int ERROR_BARCODE_ERROR_USE_TIMEOUT			= -7;
	public static final int ERROR_BARCODE_ERROR_ALREADY_OPENED		= -8;
}

public class bb180scanner extends CordovaPlugin {

	/**
	 * Executes the request and returns a boolean.
	 * 
	 * @param action
	 *            The action to execute.
	 * @param args
	 *            JSONArry of arguments for the plugin.
	 * @param callbackContext
	 *            The callback context used when calling back into JavaScript.
	 * @return boolean.
	 */


	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("softScanOn")) {
			this._softScanOn(args.getString(0), args.getString(1), callbackContext);
		} 
		else if (action.equals("open")) {
			this._open(args.getString(0), args.getString(1), callbackContext);
		} 
		else if (action.equals("uninstall")) {
			this._uninstall(args.getString(0), callbackContext);
		}
		else if (action.equals("appIsInstalled")) {
			JSONObject successObj = new JSONObject();
			if (this._appIsInstalled(args.getString(0))) {
				successObj.put("status", PluginResult.Status.OK.ordinal());
				successObj.put("message", "Installed");
			}
			else {
				successObj.put("status", PluginResult.Status.NO_RESULT.ordinal());
				successObj.put("message", "Not installed");
			}
			callbackContext.success(successObj);
		}
		else {
			JSONObject errorObj = new JSONObject();
			errorObj.put("status", PluginResult.Status.INVALID_ACTION.ordinal());
			errorObj.put("message", "Invalid action");
			callbackContext.error(errorObj);
		}
		return true;
	}


	private void _softScanOn(String fileArg, String contentType, CallbackContext callbackContext) throws JSONException {
		// JSONObject errorObj = new JSONObject();
		// errorObj.put("status", PluginResult.Status.ERROR.ordinal());
		// errorObj.put("message", "File not found");
		// callbackContext.error(errorObj);
		JSONObject successObj = new JSONObject();
		successObj.put("status", PluginResult.Status.NO_RESULT.ordinal());
		successObj.put("message", "This is a success message from native java backend");
		callbackContext.success(successObj);
	}


	private void _open(String fileArg, String contentType, CallbackContext callbackContext) throws JSONException {
		String fileName = "";
		try {
			CordovaResourceApi resourceApi = webView.getResourceApi();
			Uri fileUri = resourceApi.remapUri(Uri.parse(fileArg));
			fileName = this.stripFileProtocol(fileUri.toString());
		} catch (Exception e) {
			fileName = fileArg;
		}
		File file = new File(fileName);
		if (file.exists()) {
			try {
				Uri path = Uri.fromFile(file);
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(path, contentType);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				/*
				 * @see
				 * http://stackoverflow.com/questions/14321376/open-an-activity-from-a-cordovaplugin
				 */
				cordova.getActivity().startActivity(intent);
				//cordova.getActivity().startActivity(Intent.createChooser(intent,"Open File in..."));
				callbackContext.success();
			} catch (android.content.ActivityNotFoundException e) {
				JSONObject errorObj = new JSONObject();
				errorObj.put("status", PluginResult.Status.ERROR.ordinal());
				errorObj.put("message", "Activity not found: " + e.getMessage());
				callbackContext.error(errorObj);
			}
		} else {
			JSONObject errorObj = new JSONObject();
			errorObj.put("status", PluginResult.Status.ERROR.ordinal());
			errorObj.put("message", "File not found");
			callbackContext.error(errorObj);
		}
	}
	
	private void _uninstall(String packageId, CallbackContext callbackContext) throws JSONException {
		if (this._appIsInstalled(packageId)) {
			Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
			intent.setData(Uri.parse("package:" + packageId));
			cordova.getActivity().startActivity(intent);
			callbackContext.success();
		}
		else {
			JSONObject errorObj = new JSONObject();
			errorObj.put("status", PluginResult.Status.ERROR.ordinal());
			errorObj.put("message", "This package is not installed");
			callbackContext.error(errorObj);
		}
	}
	
	private boolean _appIsInstalled(String packageId) {
		PackageManager pm = cordova.getActivity().getPackageManager();
        boolean appInstalled = false;
        try {
            pm.getPackageInfo(packageId, PackageManager.GET_ACTIVITIES);
            appInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            appInstalled = false;
        }
        return appInstalled;
	}

	private String stripFileProtocol(String uriString) {
		if (uriString.startsWith("file://")) {
			uriString = uriString.substring(7);
		}
		return uriString;
	}

}
