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

package name.gudong.demo;

import android.content.res.Resources;

/**
 * Created by GuDong on 05/11/2016 20:51.
 * Contact with gudong.name@gmail.com.
 */

public class Utils {
    public static int dp2px(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int sp2px(float sp) {
        float scale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

}
