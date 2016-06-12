/*
 * Copyright (c) 2016, the original author or authors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *  *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * A copy of the GNU General Public License accompanies this software,
 * and is also available at http://www.gnu.org/licenses.
 *
 */

package name.abhijitsarkar.feign.core.model;

import lombok.Getter;

/**
 * @author Abhijit Sarkar
 */
@Getter
public class FeignMapping extends IgnorableRequestProperties {
    private RequestProperties request;
    private ResponseProperties response;

    public FeignMapping() {
        setRequest(request);
        setResponse(response);
    }

    public void setRequest(RequestProperties requestProperties) {
        this.request = (requestProperties == null) ? new RequestProperties() : requestProperties;
    }

    public void setResponse(ResponseProperties responseProperties) {
        this.response = (responseProperties == null) ? new ResponseProperties() : responseProperties;
    }
}
