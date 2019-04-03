# Calorie Detection and Guide to Food

## Motivation

It is a well-known quote **"A healthy outside starts from the inside"**. This means that our health depends on what we eat throughout the day. And this is true now more than ever, as people live in this fast life. So it is extremely important that whatever we eat, our body should get some kind of nutritional benefit from it. But, the question is how do we know that whatever we eat is healthy. In other words, how do we know the nutritional information about our diet? Here comes our app which lets you know all the necessary nutritional information regarding the food you eat. You just take a picture of what you eat and the app will tell you all the nutritional insights of that particular food.

## Abstract

**Calorie Detection and Guide to Food** is an android application for people who are health conscious or who want to improve their daily food habits to be fit. The app has two major functionalities: 

1. **Finding Calories of food**:
    * The app lets the user take an image of any food they consume on a daily basis and immediately calculates calories of it. Also, the app tracks the daily consumed food items and their calories so that it can recommend a highly balanced daily diet to the user. Users can set goals for daily calories to lose or gain weight and the app helps them to keep up with their goals

2. **Nutritional Information**:
    * This feature allows the users to get general nutritional insights into the food they eat every day. The app has a large database storing this information.
    
## Approach

We used Machine Learning along with Image Processing for this project. When the user takes an image of food, it gets fed to the first **Convolutional Neural Network** to find the presence of food. If there is no food item found in the image the users get alerted about the same. If the food item is present in the image then the image is fed to the second Convolutional Neural Network to find the amount of calories it contains. Each time a clicks a picture that image is compressed as preprocessing and then sent to the server so that there is no lag in response due to a larger image size.

## Output

[Follow](Output/ "Output") here
 
## Technology used

* Android
* Machine Learning
* Python
* Image Processing
* OpenCV
* Flask

## Team

* Mihir Patel
* Anuj Patel

## References

* https://pjreddie.com/darknet/yolov2/
* https://developer.android.com/training/basics/firstapp
* https://www.kaggle.com/kmader/food41
* https://github.com/developer0hye/Yolo_Label
* https://flask.palletsprojects.com/en/1.1.x/quickstart/#a-minimal-application