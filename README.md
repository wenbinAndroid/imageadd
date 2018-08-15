### imageadd
emmm,事情是这个样的,上次不是修改了[photopicker](https://github.com/wenbinAndroid/photopicker)了吗,然后又觉得如果要使用图片的上传功能,又要使用他自带的添加图片和图库功能,觉得这样并不是很友好,最好是能将增加图片上传和图库分开来,所以就写了这个..

#### 效果图是酱紫的


#### 我只提供选择图片的功能,把图库和用什么框架来显示都交给了使用者,你可以随意的切换图库和图片加载框架,这2个并不冲突,我将添加图片的这个功能,分成了普通模式和加载网络图片的模式,两者的区别就是可编辑和不可编辑的.

##### 普通模式

````java

public interface PhotoEditActionListener {
    void deleteImage(int position);

    void lookImage(int position, List<String> image);

    void addImage(AddPhotoManage manage, int position);

    String loadImage(String image, int position, ImageView iv);

}
java````
