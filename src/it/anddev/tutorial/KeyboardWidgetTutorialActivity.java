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
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ToggleButton;

public class KeyboardWidgetTutorialActivity extends Activity {

	private CustomKeyboardView mKeyboardView;
	private EditText mTargetView;
	private Keyboard capitalKeyboard;
	private Keyboard simpleKeyboard;
	private Keyboard numericKeyboard;
	private ToggleButton mToggleButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		capitalKeyboard = new Keyboard(this, R.xml.kbd_qwerty_shifted);
		simpleKeyboard = new Keyboard(this, R.xml.kbd_qwerty);
		numericKeyboard = new Keyboard(this, R.xml.kbd_numeric);
		
		mTargetView = (EditText) findViewById(R.id.target);
		mTargetView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				
				if (mToggleButton.isChecked()) {
					imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
					showKeyboardWithAnimation();
				} else {
					imm.showSoftInput(mTargetView, InputMethodManager.SHOW_IMPLICIT);
					hideKeyboardWithAnimation();
				}
			}
		});

		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(capitalKeyboard);
		mKeyboardView
				.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
						this));
		
		mToggleButton = (ToggleButton) findViewById(R.id.toggleButton);
		mToggleButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				
				if (mToggleButton.getText().toString().equals("Show")) {
					imm.showSoftInput(mTargetView, InputMethodManager.SHOW_IMPLICIT);
					hideKeyboardWithAnimation();
				} else {
					imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
					showKeyboardWithAnimation();
				}
				
//				mToggleButton.toggle();
			}
		});
		
		mTargetView.setCustomSelectionActionModeCallback(new Callback() {
			
			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}
			
			@Override
			public void onDestroyActionMode(ActionMode mode) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}
	
	private boolean _isShifted = true;
	private boolean _customShown;

	/***
	 * Mostra la tastiera a schermo con una animazione di slide dal basso
	 */
	private void showKeyboardWithAnimation() {
//		if (mKeyboardView.getVisibility() == View.GONE) {
			Animation animation = AnimationUtils
					.loadAnimation(KeyboardWidgetTutorialActivity.this,
							R.anim.slide_in_bottom);
			mKeyboardView.showWithAnimation(animation);
			
			_customShown = true;
//		}
	}
	
	private void hideKeyboardWithAnimation() {
//		if (mKeyboardView.getVisibility() == View.GONE) {
		Animation animation = AnimationUtils
				.loadAnimation(KeyboardWidgetTutorialActivity.this,
						R.anim.slide_out_bottom);
		mKeyboardView.hideWithAnimation(animation);
		
		_customShown = false;
//		}
	}
	
	public void shiftKeyboard() {
		_isShifted = !_isShifted;
		
		if (_isShifted) {
			mKeyboardView.setKeyboard(capitalKeyboard);
		} else {
			mKeyboardView.setKeyboard(simpleKeyboard);
		}
	}
	
	private boolean _isAlphabetic = true;

	public void toggleKeyborad() {
		_isAlphabetic = !_isAlphabetic;
		
		if (_isAlphabetic) {
			mKeyboardView.setKeyboard(capitalKeyboard);
		} else {
			mKeyboardView.setKeyboard(numericKeyboard);
		}
	}
}