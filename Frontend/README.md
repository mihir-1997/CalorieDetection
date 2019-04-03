# Fronend

We have developed an **Android** application so that users can easily take pictures of food items and get nutritional information within seconds. The app is packed with basic information like how much calories should a person take per day based on criteria like age, gender, and how much work he/she does in a day. Apart from this, we also provide information like what kind of food gives what kind of nutrition e.g. if you want to focus on getting **Vitamin C** then eat broccoli, cantaloupe, cauliflower, kale, kiwi, orange juice, papaya, etc. This further helps people to concentrate on particular needs. So, all in all, our app helps users to keep track of what they eat throughout the day and provide essential guidance on what to eat to stay healthy.

```
The APK is also attached in this repo
```

## App Layout

1. **Home Screen**:
	* This is the first screen which will be shown to users whenever they open the app
	* This screen is further divided in three sections,
		1. **Calorie Requirement**:
			* This is a simple navigation which will take users to the new screen so that they can get insights into **Calorie Requirements**
		2. **Nutrition Info**:
			* This is another navigation which will take users to the screen where they can search for any particular nutritional sources
		3. **Camera Icon**:
			* This icon is used to launch the camera (by user's consent) and capture image which then will be sent to the server for processing
	* [Sample Ouput](../output/Output-3.png "Home Screen")

2. **Output Screen**:
	* As the name suggests, this screen shows the output from backend which includes,
		1. Name of food
		2. Image of food
		3. Nutritional Information like Calories, Total Fat, Cholesterol, Protein, etc.
	* [Sample Ouput](../output/Output-1.png "Output")

# References

* https://developer.android.com/training/basics/firstapp
* https://developer.android.com/training/volley