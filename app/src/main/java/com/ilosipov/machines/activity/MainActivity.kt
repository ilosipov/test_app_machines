package com.ilosipov.machines.activity

import androidx.fragment.app.Fragment
import com.ilosipov.machines.BaseActivity
import com.ilosipov.machines.fragment.MainFragment

/**
 * Класс MainActivity - стартовая активити
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2020
 * @version $Id$
 */

class MainActivity : BaseActivity() {

    override fun createFragment() : Fragment = MainFragment()
}
