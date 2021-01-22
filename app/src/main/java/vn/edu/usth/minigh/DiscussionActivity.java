/*
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
 */

package vn.edu.usth.minigh;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class DiscussionActivity extends BaseActivity {
    private Random r;

    public DiscussionActivity() {
        super(R.layout.activity_discussion);
        r = new Random(123456);
    }

    private CommentFragment generateComment() {
        // TODO: Empty this after the demo
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        String[] users = {"Huy-Ngo", "McSinyx", "Ming-Lonh", "PhuongPhg", "minhngo"};
        args.putString("username", users[r.nextInt(users.length)]);
        if (r.nextFloat() > 0.5) {
            args.putString("content", this.getResources().getString(R.string.lorem_long));
        } else {
            args.putString("content", this.getResources().getString(R.string.lorem_short));
        }
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = getIntent().getStringExtra("title");
        TextView ghname = (TextView) findViewById(R.id.discussTitle);
        ghname.setText(title);

        String descrip = getIntent().getStringExtra("description");
        TextView content = (TextView) findViewById(R.id.discussDescription);
        content.setText(descrip);

        int nComments = 7;
        for (int i = 0; i < nComments; i++) {
            Fragment fragment = generateComment();
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(
                    R.id.comment_container, fragment
            ).commit();
        }

    }
}
