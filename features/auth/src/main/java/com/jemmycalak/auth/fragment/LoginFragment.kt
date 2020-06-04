package com.jemmycalak.auth.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jemmycalak.auth.R
import com.jemmycalak.auth.databinding.FragmentLoginBinding
import com.jemmycalak.auth.viewmodel.LoginViewModel
import com.jemmycalak.common.base.BaseFragment
import com.jemmycalak.common.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private val vModel : LoginViewModel by viewModel()
    private lateinit var binding: FragmentLoginBinding

    override fun getViewModel(): BaseViewModel = vModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLoginBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            viewModel = vModel
        }.root
    }
}