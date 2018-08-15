
![emmn](https://github.com/wenbinAndroid/imageadd/blob/master/sample/emmm.png)事情是这个样的,上次不是修改了[photopicker](https://github.com/wenbinAndroid/photopicker)了吗,然后又觉得如果要使用图片的上传功能,又要使用他自带的添加图片和图库功能,觉得这样并不是很友好,最好是能将增加图片上传和图库分开来,所以就写了这个..

#### 效果图是酱紫的
##### 这里使用了[知乎开源图库](https://github.com/zhihu/Matisse),还有[Glide](https://github.com/bumptech/glide),你们可以不用使用这个2个,也可以使用自己原来使用的.

![效果图](https://github.com/wenbinAndroid/imageadd/blob/master/sample/S80815-12005904.gif)

##### ![emmn](https://github.com/wenbinAndroid/imageadd/blob/master/sample/emmm.png)好像有点掉帧是吧.....
#### 我只提供选择图片的功能,把使用什么图库和用什么框架来显示都交给了使用者,你可以随意的切换图库和图片加载框架,这2个并不冲突,我将添加图片的这个功能,分成了普通模式和加载网络图片的模式,两者的区别就是可编辑和不可编辑的.

##### 普通模式

````java

public interface PhotoEditActionListener {
    //删除回调
    void deleteImage(int position);
    //查看预览图
    void previewImage(int position, List<String> image);
    //添加图片
    void addImage(AddPhotoManage manage, int position);
    //显示图片    
    String loadImage(String image, int position, ImageView iv);

}
````

#### 网络模式

````java

public interface PhotoResourceListener {
    //加载图片
    String loadImage(String image, int position, ImageView iv);
    //预览图片
    void previewImage(int position, List<String> image);
}

````
