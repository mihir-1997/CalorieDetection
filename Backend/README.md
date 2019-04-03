# Backend

The backend is developed in Flask where we start a flask server to take images from Frontend, process it through two different Machine Learning models to find whether it contains food or not and if the image contains any food then the type of food gets detected which at the end is used to extract calories and other nutritional information from the database. This whole information is sent back to Frontend to render.

## Models

1. **ImageClassification:**
	* First model which takes image data as `224 x 224` array and process it to generate the probability of any food in the image
	* The model consists of 8 layers including input and output layers
	* To implement the model **tflearn** library is used

2. **YOLO**:
	* **YOLO** stands for **You Only Look for Once** and it is a famous **Object Detection** model 
	* What it means by **YOLO** is that this model scans the whole image just once to get all the objects that the image contains
	* Then it generates the probability of all objects in the image and determines whether any particular object is present in that or not
	* We take this model and trained it on our dataset on **Google Colab** and adjusted the parameters to achieve the desired accuracy
	* To know more about this model [click here](https://pjreddie.com/darknet/yolov2/ "YOLO")

# References

* https://pjreddie.com/darknet/yolov2/
* https://www.kaggle.com/kmader/food41
* https://flask.palletsprojects.com/en/1.1.x/quickstart/#a-minimal-application
