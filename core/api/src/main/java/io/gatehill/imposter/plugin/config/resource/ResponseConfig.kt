/*
 * Copyright (c) 2016-2021.
 *
 * This file is part of Imposter.
 *
 * "Commons Clause" License Condition v1.0
 *
 * The Software is provided to you by the Licensor under the License, as
 * defined below, subject to the following condition.
 *
 * Without limiting other conditions in the License, the grant of rights
 * under the License will not include, and the License does not grant to
 * you, the right to Sell the Software.
 *
 * For purposes of the foregoing, "Sell" means practicing any or all of
 * the rights granted to you under the License to provide to third parties,
 * for a fee or other consideration (including without limitation fees for
 * hosting or consulting/support services related to the Software), a
 * product or service whose value derives, entirely or substantially, from
 * the functionality of the Software. Any license notice or attribution
 * required by the License must also include this Commons Clause License
 * Condition notice.
 *
 * Software: Imposter
 *
 * License: GNU Lesser General Public License version 3
 *
 * Licensor: Peter Cornish
 *
 * Imposter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Imposter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Imposter.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.gatehill.imposter.plugin.config.resource

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import io.gatehill.imposter.script.FailureSimulationType
import io.gatehill.imposter.script.PerformanceSimulationConfig

/**
 * @author Pete Cornish
 */
open class ResponseConfig {
    @JsonProperty("file")
    @JsonAlias("staticFile")
    var file: String? = null

    @JsonProperty("content")
    @JsonAlias("staticData")
    var content: String? = null

    @JsonProperty("dir")
    val dir: String? = null

    @JsonProperty("template")
    var isTemplate: Boolean? = null

    /**
     * Shorthand for a processing step of type 'script'.
     */
    @JsonProperty("scriptFile")
    var scriptFile: String? = null

    @JsonProperty("statusCode")
    var statusCode: Int? = null

    @JsonProperty("headers")
    var headers: Map<String, String>? = null

    @JsonProperty("delay")
    var performanceDelay: PerformanceSimulationConfig? = null

    @JsonProperty("fail")
    var failureType: FailureSimulationType? = null

    /**
     * @return `true` if properties of the response configuration have been set
     */
    open fun hasConfiguration(): Boolean = arrayOf(
        this.content,
        this.failureType,
        this.file,
        this.headers,
        this.performanceDelay,
        this.scriptFile,
        this.statusCode,
        this.isTemplate,
    ).any { it != null }

    /**
     * Sets a response header. If the [headers] map is `null`, it
     * is first initialised.
     */
    fun setHeader(name: String, value: String) {
        val h = headers?.toMutableMap() ?: mutableMapOf()
        h[name] = value
        headers = h
    }

    override fun toString(): String {
        return "ResponseConfig(file=$file, content=$content, dir=$dir, isTemplate=$isTemplate, scriptFile=$scriptFile, statusCode=$statusCode, headers=$headers, performanceDelay=$performanceDelay, failureType=$failureType)"
    }
}
