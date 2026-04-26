package com.avnmsoft.shadowsocks.plugin.vkturn

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.github.shadowsocks.plugin.PluginOptions

class ConfigFragment : PreferenceFragmentCompat() {
    private val link by lazy { findPreference<EditTextPreference>("link")!! }

    val options get() = PluginOptions().apply {
        val domainValue = link.text?.trim().takeIf { !it.isNullOrEmpty() }
        putWithDefault("link", domainValue)
    }

    fun onInitializePluginOptions(options: PluginOptions) {
        link.text = options["link"]
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.config)
        link.setOnBindEditTextListener { it.inputType = InputType.TYPE_TEXT_VARIATION_URI }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(listView) { v, insets ->
            insets.apply {
                v.updatePadding(bottom = getInsets(WindowInsetsCompat.Type.navigationBars()).bottom)
            }
        }
    }
}
