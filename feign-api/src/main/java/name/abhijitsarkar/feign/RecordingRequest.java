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

package name.abhijitsarkar.feign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * @author Abhijit Sarkar
 */
@Getter
@NoArgsConstructor
@ToString
@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.ImmutableField", "PMD.SingularField"})
public class RecordingRequest extends Request {
    @Id
    private String id;

    public RecordingRequest(Request request, String id) {
        super();
        path = request.getPath();
        method = request.getMethod();
        queryParams = request.getQueryParams();
        headers = request.getHeaders();
        body = request.getBody();

        this.id = id;
    }
}