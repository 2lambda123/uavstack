/*-
 * <<
 * UAVStack
 * ==
 * Copyright (C) 2016 - 2017 UAVStack
 * ==
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * >>
 */

package com.creditease.uav.util;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.creditease.agent.helpers.ReflectionHelper;

public class TransformWapperUtil {

    /*
     * 为response脱壳，将Wapper脱到指定的壳，若该Wapper不包含指定的壳，则脱到原生壳为止
     */
    public static HttpServletResponse moveWapper(String resWapperName, HttpServletResponse response) {

        while (HttpServletResponseWrapper.class.isAssignableFrom(response.getClass())) {

            if (!resWapperName.equals(response.getClass().getName())) {

                response = (HttpServletResponse) ReflectionHelper.getField(response.getClass(), response, "response");
            }
            else {
                return response;
            }
        }
        return response;
    }
}