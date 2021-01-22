/* Fragment for previewing repo
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

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.TextView

import androidx.fragment.app.Fragment

import vn.edu.usth.minigh.LANGUAGE_COLOR
import vn.edu.usth.minigh.R

class RepoPreviewFragment() : Fragment(R.layout.fragment_repo_preview) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = requireArguments()
        view.findViewById<TextView>(R.id.repo_name_text).text =
            args.getString("full name")

        val description = view.findViewById<TextView>(R.id.description_text)
        args.getString("description")?.also {
            description.text = it
        } ?: run {
            description.visibility = GONE
        }

        args.getString("language")?.also {
            view.findViewById<TextView>(R.id.language_text).text = it
            LANGUAGE_COLOR[it]?.toInt()?.also { color ->
                view.findViewById<TextView>(R.id.language_icon)
                    .setTextColor(color)
            }
        } ?: run {
            view.findViewById<View>(R.id.language).visibility = GONE
        }

        args.getString("license")?.also {
            view.findViewById<TextView>(R.id.license_text).text = if (it == "NOASSERTION") "Other" else it
        } ?: run {
            view.findViewById<View>(R.id.license).visibility = GONE
        }

        val forks_count = args.getInt("forks count")
        if (forks_count > 0) {
            view.findViewById<TextView>(R.id.forks_count).text =
                forks_count.toString()
        } else {
            view.findViewById<View>(R.id.forks).visibility = GONE
        }

        val stargazers_count = args.getInt("stargazers count")
        if (stargazers_count > 0) {
            view.findViewById<TextView>(R.id.stargazers_count).text =
                stargazers_count.toString()
        } else {
            view.findViewById<View>(R.id.stargazers).visibility = GONE
        }
    }
}
