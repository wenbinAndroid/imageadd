
![emmn](https://github.com/wenbinAndroid/imageadd/blob/master/sample/emmm.png)事情是这个样的,上次不是修改了[photopicker](https://github.com/wenbinAndroid/photopicker)了吗,然后又觉得如果要使用图片的上传功能,又要使用他自带的添加图片和图库功能,觉得这样并不是很友好,最好是能将增加图片上传和图库分开来,所以就写了这个..

#### 效果图是酱紫的
##### 这里使用了[知乎开源图库](https://github.com/zhihu/Matisse),还有[Glide](https://github.com/bumptech/glide),你们可以不用使用这个2个,也可以使用自己原来使用的.

![效果图](https://github.com/wenbinAndroid/imageadd/blob/master/sample/S80815-12005904.gif)

##### ![emmn](https://github.com/wenbinAndroid/imageadd/blob/master/sample/emmm.png)好像有点掉帧是吧.....

##### 如何使用

Step 1. 
```` java
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.wenbinAndroid:imageadd:v1.0.0'
	}
          
````
#### 我只提供选择图片的功能,把使用什么图库和用什么框架来显示都交给了使用者,你可以随意的切换图库和图片加载框架,这2个并不冲突,我将添加图片的这个功能,分成了普通模式和加载网络图片的模式,两者的区别就是可编辑和不可编辑的.

#### 使用方法

##### 普通模式

````java
AddPhotoView addView=mAddView.getAddPhotoManage();
addView.setPhotoEditActionListener(new PhotoEditActionListener() {
            @Override
            public void deleteImage(int position) {
                //删除图片回调
            }

            @Override
            public void previewImage(int position, List<String> image) {
                //预览图片 这个界面是根据网易的写的..
                Intent intent = new Intent(NormalActivity.this, PreViewActivity.class);
                intent.putStringArrayListExtra(PreViewActivity.IMAGE, (ArrayList<String>) image);
                intent.putExtra(PreViewActivity.POSITION, position);
                startActivity(intent);
            }

            @Override
            public void addImage(AddPhotoManage manage, int position) {
                //添加图片
                //这里是对读写权限和照相机权限进行判断,然后使用知乎的图片选择库,代码太多我就不写出来了
            }

            @Override
            public void loadImage(String image, int position, ImageView iv) {
                //加载图片
               RequestOptions options = new RequestOptions();
               options.placeholder(R.mipmap.ic_loading).error(R.mipmap.ic_loading);
               Glide.with(NormalActivity.this).load(image).apply(options).into(iv);
              
            }
        });
````

#### 网络模式

````java
List<String> imageList=new Arrays<>();
 addView.setData(imageList.size(), new PhotoResourceListener() {
                    @Override
                    public String loadImage(String image, int position, ImageView iv) {
                    //加载图片 这里要将获取到的图片返回,不然图片在预览的时候回出错
                     String reallyImage = imageList.get(position);
                     RequestOptions options = new RequestOptions();
                     options.placeholder(R.mipmap.ic_loading).error(R.mipmap.ic_loading);
                     Glide.with(LoadActivity.this).load(reallyImage).apply(options).into(iv);
                     return reallyImage;
                    }

                    @Override
                    public void previewImage(int position, List<String> image) {
                    //预览图片   这个界面是根据网易的写的..
                    Intent intent = new Intent(LoadActivity.this, PreViewActivity.class);
                    intent.putStringArrayListExtra(PreViewActivity.IMAGE, (ArrayList<String>) image);
                    intent.putExtra(PreViewActivity.POSITION, position);
                    startActivity(intent);

                    }
                });

````
#### 自定义的属性

name | format | description
------------ | ------------- | -------------
add_photo_max_count | integer | 最大显示张数(默认6张)
add_photo_horizontal_count | integer | 一行显示多少张(默认4张)
add_photo_edit_mode | boolean | 是否是编辑模式(默认是)
add_photo_add_icon | reference | 添加按钮的图标(默认自带)
add_photo_horizontal_margin | dimension | 每个item的水平间距(默认10dp)
add_photo_vertical_margin | dimension | 每行的垂直间距(默认10dp)
add_photo_delete_icon | reference | 删除按钮的图标(默认自带)


#### 思路
##### 这个就是一个普通的RecyclerView,在网络模式下,传入图片的数量,使用Task生成相应数量的item,然后通过item的视图显示回调去通知相应的图片显示
