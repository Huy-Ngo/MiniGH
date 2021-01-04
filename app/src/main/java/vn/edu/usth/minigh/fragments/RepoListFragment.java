/* Fragment for listing repositories
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

// TODO: Empty this after the demo
import java.util.Random;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RepoListFragment extends Fragment {
    private int nRepos;
    private Random r;

    public RepoListFragment() {
    }
    public RepoListFragment(int nRepos) {
        // TODO: Empty this after the demo
        this.nRepos = nRepos;
        this.r = new Random(42069);
    }

    private RepoPreviewFragment generateRepos() {
        // TODO: Empty this after the demo
        RepoPreviewFragment fragment = new RepoPreviewFragment();
        Bundle args = new Bundle();
        String[] languages = {"Java", "JavaScript", "C", "C#", "C++",
            "PHP", "Kotlin", "Go", "Rust", "Python", "QML", "TeX", "Ruby",
            "Perl", "Pascal", "Lua", "MATLAB", "Makefile", "Dart", "Common Lisp",
            "Vim script", "Shell"};
        String[] licenses = {"MIT", "GPL-3.0", "AGPL-3.0", "LGPL-3.0"};
        args.putString("owner", "Huy-Ngo");
        args.putString("name", "MiniGH");
        args.putString("description", "A GitHub client");
        if (r.nextFloat() > 0.5) {
            args.putInt("stars", r.nextInt(20));
        } else {
            args.putInt("stars", 0);
        }
        if (r.nextFloat() > 0.5) {
            args.putInt("forks", r.nextInt(20));
        } else {
            args.putInt("forks", 0);
        }
        if (r.nextFloat() > 0.3) {
            int l = languages.length;
            args.putString("mainLanguage", languages[r.nextInt(l)]);
        } else {
            args.putString("mainLanguage", "");
        }
        if (r.nextFloat() > 0.5) {
            int l2 = licenses.length;
            args.putString("license", licenses[r.nextInt(l2)]);
        } else {
            args.putString("license", "");
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        for (int i = 0; i < this.nRepos; i++) {
            Fragment preview = this.generateRepos();
            getChildFragmentManager().beginTransaction().add(R.id.repo_list, preview).commit();
        }

        return inflater.inflate(R.layout.fragment_repo_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}
