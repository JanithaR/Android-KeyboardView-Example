/*
 * Copyright (C) 2011 - Riccardo Ciovati
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.anddev.tutorial;

import android.app.Activity;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

/***
 * Listener da associare ad un oggetto KeyboardView in modo tale che quando
 * viene premuto un tasto il corrispondente evento viene girato all'activity
 * passata al costruttore
 */
public class BasicOnKeyboardActionListener implements OnKeyboardActionListener {

	private KeyboardWidgetTutorialActivity mTargetActivity;

	/***
	 * 
	 * @param targetActivity
	 *            Activity a cui deve essere girato l'evento
	 *            "pressione di un tasto sulla tastiera"
	 */
	public BasicOnKeyboardActionListener(KeyboardWidgetTutorialActivity targetActivity) {
		mTargetActivity = targetActivity;
	}

	@Override
	public void swipeUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onText(CharSequence text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRelease(int primaryCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPress(int primaryCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKey(int primaryCode, int[] keyCodes) {
		Log.d("XXX", "" + primaryCode);
		
		View focusCurrent = mTargetActivity.getWindow().getCurrentFocus();
	    if( focusCurrent==null || focusCurrent.getClass()!=EditText.class ) return;
	    EditText edittext = (EditText) focusCurrent;
	    Editable editable = edittext.getText();
	    int start = edittext.getSelectionStart();
	    
//		long eventTime = System.currentTimeMillis();
//		KeyEvent event = new KeyEvent(eventTime, eventTime,
//				KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0,
//				KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);
//
//		mTargetActivity.dispatchKeyEvent(event);
	    
	    if (primaryCode == 50001) {
	    	if( editable!=null && start>0 ) editable.delete(start - 1, start);
		} else if (primaryCode == -1) {
			mTargetActivity.shiftKeyboard();
		} else if (primaryCode == 50002) {
			mTargetActivity.toggleKeyborad();
		} else {
			editable.insert(start, Character.toString((char) primaryCode));
		}
	}
}