package com.hrw.android.player.activity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;

import com.hrw.android.player.R;
import com.hrw.android.player.adapter.MusicListAdapter;
import com.hrw.android.player.dao.AudioDao;
import com.hrw.android.player.dao.impl.AudioDaoImpl;
import com.hrw.android.player.domain.Audio;
import com.hrw.android.player.utils.Constants;
import com.hrw.android.player.utils.Constants.PopupMenu;

public class LocalMusicListActivity extends BaseListActivity {
	private AudioDao audioDao = new AudioDaoImpl(this);
	private ImageButton back_btn;
	private List<Audio> musicList;
	private String[] choices;
	private MusicListAdapter adapter;
	Set<Integer> popUpMenu = new HashSet<Integer>();

	private void initPopupMenu() {
		popUpMenu.add(PopupMenu.ADD_ALL_TO.getMenu());
		popUpMenu.add(PopupMenu.CREATE_LIST.getMenu());
		popUpMenu.add(PopupMenu.EXIT.getMenu());
		popUpMenu.add(PopupMenu.HELP.getMenu());
		popUpMenu.add(PopupMenu.SETTING.getMenu());
	}

	private void initButtons() {
		final TabActivity tabActivity = (TabActivity) getParent();
		final Intent toMenuListActivity = new Intent(this,
				MenuListActivity.class);

		back_btn = (ImageButton) tabActivity.findViewById(R.id.list_back);
		back_btn.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {

				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					TabHost.TabSpec tab_spec_menu_list = tabActivity
							.getTabHost()
							.newTabSpec(
									Constants.TAB_SPEC_TAG.MAIN_SPEC_TAG
											.getId())
							.setIndicator(
									Constants.TAB_SPEC_TAG.MAIN_SPEC_TAG
											.getId());
					tab_spec_menu_list.setContent(toMenuListActivity);
					tabActivity.getTabHost().addTab(tab_spec_menu_list);
					tabActivity.getTabHost().setCurrentTabByTag(
							Constants.TAB_SPEC_TAG.MAIN_SPEC_TAG.getId());
					Intent updateUiIntent = new Intent(
							Constants.UPDATE_UI_ACTION);
					sendBroadcast(updateUiIntent);
				}
				return false;
			}
		});
	}

	private void initListAdapter() {
		musicList = audioDao.getLocalAudioList();
		adapter = new MusicListAdapter(this, musicList, null);
		setListAdapter(adapter);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_audio_list);
		initPopupMenu();
		initButtons();
		initListAdapter();
	}

	@Override
	protected void onResume() {
		super.onResume();
		back_btn.setVisibility(ImageButton.VISIBLE);
		TextView top_title_tv = (TextView) this.getParent().findViewById(
				R.id.top_title);
		top_title_tv.setText(R.string.menu_local_music);
		adapter.notifyDataSetChanged();
	}

	@Override
	protected Set<Integer> getPopUpMenu() {
		if (adapter.getCheckedBoxPositionIds().size() > 0) {
			popUpMenu.add(PopupMenu.ADD_TO.getMenu());
		} else {
			popUpMenu.remove(PopupMenu.ADD_TO.getMenu());
		}
		return popUpMenu;
	}
}
