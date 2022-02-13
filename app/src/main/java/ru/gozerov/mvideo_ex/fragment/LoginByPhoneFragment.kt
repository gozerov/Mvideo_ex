package ru.gozerov.mvideo_ex.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import ru.gozerov.mvideo_ex.databinding.FragmentLoginByPhoneBinding

class LoginByPhoneFragment: Fragment() {

    private lateinit var binding: FragmentLoginByPhoneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginByPhoneBinding.inflate(inflater, container, false)

        with(binding.phoneInputTextField) {
            if (editableText.isEmpty())
                binding.continueLoginButton.isEnabled = false
            inputType = InputType.TYPE_CLASS_PHONE
            doOnTextChanged { text, _, _, _ ->
                binding.continueLoginButton.isEnabled = !(text == null || text.isEmpty())
            }
        }

        return binding.root
    }

}