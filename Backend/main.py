#!/usr/bin/env python

import os
from flask import Flask, render_template, request, redirect, url_for, jsonify
import darknet
from imageClassification.validateImageClassification import checkForFood
from flask_mysqldb import MySQL

app = Flask(__name__)

UPLOAD_FOLDER = os.path.join('static/images')
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = 'root'
app.config['MYSQL_DB'] = 'calorie'

mysql = MySQL(app)


@app.route('/')
def hello_world():
    return render_template('file_upload.html')


@app.route('/upload', methods=['POST'])
def upload_file():
    list_nutritions = ["item", "amount_per", "calories", "fat", "cholesterol", "sodium", "potassium", "carbohydrates",
                       "protein"]
    file = request.files['image']
    f = os.path.join(app.config['UPLOAD_FOLDER'], file.filename)
    file.save(f)
    nutrition_info = []
    if checkForFood(f):
        output = imageProcessing(f)
        print(output)
        for item in output:
            try:
                cur = mysql.connection.cursor()
                cur.execute("SELECT * from Nutritions_info WHERE item=%s", (item[0].decode('utf-8'),))
                db_out = cur.fetchall()
                print(db_out[0])
                cur.close()
            except:
                return jsonify("Error while connecting database")
            dict_nutririons = dict(zip(list_nutritions, db_out[0][1:]))
            nutrition_info.append(dict_nutririons)
        return jsonify(nutrition_info)
    else:
        return jsonify("no labels found")


def imageProcessing(image):
    return darknet.getImageAndModel(image)


if __name__ == '__main__':
    app.run(debug=True)
