/*
 * Copyright (c) 2024.
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

logger.info(`context: ${context}`);
console.log('JS console is available');

const request = context.request;

if (request.pathParams.qux) {
    // echo the value of the 'qux' path parameter as a response header
    respond()
        .withStatusCode(203)
        .withHeader('X-Echo-Qux', request.pathParams.qux);

} else if (request.queryParams.foo) {
    // echo the value of the 'foo' request parameter as a response header
    respond()
        .withStatusCode(200)
        .withHeader('X-Echo-Foo', request.queryParams.foo);

} else if (request.headers.baz) {
    // echo the value of the 'baz' request header as a response header
    respond()
        .withStatusCode(202)
        .withHeader('X-Echo-Baz', request.headers.baz);

} else if (request.normalisedHeaders.corge) {
    // echo the value of the 'corge' request header as a response header
    // note: the key is lowercase in normalisedHeaders, regardless of the request header casing
    respond()
        .withStatusCode(202)
        .withHeader('X-Echo-Corge', request.normalisedHeaders.corge);

} else if (env.example) {
    // echo the value of the 'example' environment variable as a response header
    respond()
        .withStatusCode(204)
        .withHeader('X-Echo-Env-Var', env.example);

} else {
    // check bound variable
    if (hello === 'world') {
        // respond with status code and file
        respond()
            .withStatusCode(201).and()
            .withFile('foo.bar')
            .withHeader('MyHeader', 'AwesomeHeader')
            .skipDefaultBehaviour();
    }
}
