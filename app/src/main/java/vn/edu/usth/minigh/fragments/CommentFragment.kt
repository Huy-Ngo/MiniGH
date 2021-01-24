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
package vn.edu.usth.minigh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 * Use the [CommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentFragment : Fragment() {
    private var mUsername: String? = null
    private var mProfilePicture = 0
    private var mContent: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mUsername = requireArguments().getString(ARG_PARAM1)
            mProfilePicture = requireArguments().getInt(ARG_PARAM2)
            mContent = requireArguments().getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_comment, container, false)
        val username = view.findViewById<TextView>(R.id.username)
        username.text = mUsername
        val content = view.findViewById<TextView>(R.id.content);
        content.text = mContent;
        return view
    }

    companion object {
        private const val ARG_PARAM1 = "username"
        private const val ARG_PARAM2 = "profilePicture"
        private const val ARG_PARAM3 = "content"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param username Parameter 1.
         * @param profilePicture Parameter 2.
         * @param content Parameter 3.
         * @return A new instance of fragment CommentFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(username: String?, profilePicture: Int, content: String?): CommentFragment {
            val fragment = CommentFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, username)
            args.putInt(ARG_PARAM2, profilePicture)
            args.putString(ARG_PARAM3, content)
            fragment.arguments = args
            return fragment
        }
    }
}