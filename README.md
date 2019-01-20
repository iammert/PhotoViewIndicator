# PhotoViewIndicator
Indicator for PhotoView Library https://github.com/chrisbanes/PhotoView

<img src="https://raw.githubusercontent.com/iammert/PhotoViewIndicator/master/art/art.png"/>

[GIF](https://github.com/iammert/PhotoViewIndicator/blob/master/art/2019_01_20_23_43_14.gif)

## Usage
```java
val photoView: PhotoView = findViewById(R.id.photoView)
val indicator: PhotoViewIndicator = findViewById(R.id.indicator)
indicator.setPhotoView(photoView)
```

## Setup
```
allprojects {
    repositories {
	    maven { url 'https://jitpack.io' }
    }
}
```
```
dependencies {
    implementation 'com.github.iammert:PhotoViewIndicator:1.0.0'
}
```

License
--------


    Copyright 2019 Mert Şimşek

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




