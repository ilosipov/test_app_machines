package com.ilosipov.machines.network

import com.ilosipov.machines.model.Machine
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Интерфейс LatheAPI
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2020
 * @version $Id$
 */

interface LatheAPI {

    @GET("lathe_json")
    fun lathe() : Single<List<Machine>>
}