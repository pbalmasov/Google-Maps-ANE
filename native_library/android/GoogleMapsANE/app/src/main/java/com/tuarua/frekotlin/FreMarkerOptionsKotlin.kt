/*
 *  Copyright 2017 Tua Rua Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.tuarua.frekotlin

import android.graphics.Bitmap
import com.adobe.fre.FREObject
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.tuarua.frekotlin.display.Bitmap

class FreMarkerOptionsKotlin() : FreObjectKotlin() {
    constructor(freObject: FREObject?) : this() {
        rawValue = freObject
    }

    override val value: MarkerOptions
        @Throws(FreException::class)
        get() {
            val rv = rawValue
            if (rv != null) {
                val coordinate = LatLng(rv.getProp("coordinate"))
                val title = String(rv.getProp("title"))
                val snippet = String(rv.getProp("snippet"))
                val draggable = Boolean(rv.getProp("isDraggable")) == true
                val flat = Boolean(rv.getProp("isFlat")) == true

                val alpha = Float(rv.getProp("alpha")) ?: 1.0F
                val rotation = Float(rv.getProp("rotation")) ?: 0.0F

                val colorFre = rv.getProp("color")
                val color = colorFre?.toHSV() ?: 0.0F

                val iconFre = rv.getProp("icon")
                var icon: Bitmap? = null
                try {
                    icon = Bitmap(iconFre)
                } catch (e: FreException) {
                    throw e
                } catch (e: Exception) {
                    throw FreException(e)
                }

                return MarkerOptions()
                        .position(coordinate)
                        .title(title)
                        .snippet(snippet)
                        .draggable(draggable)
                        .flat(flat)
                        .alpha(alpha)
                        .icon(if (icon is Bitmap) BitmapDescriptorFactory
                                .fromBitmap(icon) else BitmapDescriptorFactory
                                .defaultMarker(color))
                        .rotation(rotation)
            }
            return MarkerOptions()
        }
}

fun MarkerOptions(freObject: FREObject?): MarkerOptions = FreMarkerOptionsKotlin(freObject = freObject).value