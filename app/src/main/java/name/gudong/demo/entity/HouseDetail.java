/*
 * Copyright 2016 GuDong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package name.gudong.demo.entity;

import java.util.List;

/**
 * Created by GuDong on 27/10/2016 22:24.
 * Contact with gudong.name@gmail.com.
 */

public class HouseDetail {
    public String desc;
    public List<String>photos;

    public  Param param;

    public List<String> comments;

    public static class Param{
        public long createTime;
        public String city;
        public String owner;
        public String address;
        public int area;
    }
}
