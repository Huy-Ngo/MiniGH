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

    public RepoListFragment(int nRepos) {
        // TODO: Empty this after the demo
        this.nRepos = nRepos;
    }

    private RepoPreviewFragment generateRepos() {
        RepoPreviewFragment fragment = new RepoPreviewFragment();
        Bundle args = new Bundle();
        args.putString("owner", "Huy-Ngo");
        args.putString("name", "MiniGH");
        args.putString("description", "A GitHub client");
        args.putInt("stars", 5);
        args.putInt("forks", 1);
        args.putString("mainLanguage", "java");
        args.putString("license", "AGPL-3.0");
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