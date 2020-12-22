/* Activity for user profile
 * Copyright (C) 2020  Ngô Ngọc Đức Huy
 * This file is part of MiniGH.
 *
 * MiniGH is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MiniGH is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with MiniGH.  If not, see <https://www.gnu.org/licenses/>.
**/
package vn.edu.usth.minigh;

import android.content.Intent;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import info.androidhive.fontawesome.FontTextView;

public class ProfileActivity extends BaseActivity {
    public ProfileActivity() {
        super(R.layout.activity_profile);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        TextView txt_toolbar = (TextView) findViewById(R.id.main_text_bar);
        txt_toolbar.setText("Profile");
        LinearLayout layout = this.findViewById(R.id.profile);
        layout.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.secondaryColor));
      
        PagerAdapter adapter = new ProfileViewPagerAdapter(
                this.getApplicationContext(),
                getSupportFragmentManager()
        );
        ViewPager pager = findViewById(R.id.profile_view_pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.profile_tab_layout);
        tabLayout.setupWithViewPager(pager);
    }

    public void goToRepo(View view) {
        Intent intent = new Intent(this, RepoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
