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

package vn.edu.usth.minigh.fragments

import kotlinx.coroutines.launch

import android.os.Bundle
import android.view.View

import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.github

class RepoListFragment : Fragment(R.layout.fragment_repo_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().getString("owner")?.let { owner ->
            lifecycleScope.launch {
                for (repo in github.repos(owner)) {
                    childFragmentManager.commit {
                        add<RepoPreviewFragment>(R.id.repo_list, args =
                            bundleOf(
                                "full name" to repo.full_name,
                                "description" to repo.description,
                                "language" to repo.language,
                                "forks count" to repo.forks_count,
                                "stargazers count" to repo.stargazers_count,
                                "watchers count" to repo.watchers_count,
                                "license" to repo.license?.spdx_id
                            )
                        )
                    }
                }
            }
        }
    }
}
