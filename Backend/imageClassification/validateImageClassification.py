import numpy as np
import os
from PIL import Image
from imageClassification.model_imageClassification import makeModel


def checkForFood(pathImage):

    model = makeModel()
    model.load(os.path.join('model', 'imageClassification.tflearn'))
    image = Image.open(pathImage)
    image_gray = image.convert('L')
    image_gray = image_gray.resize((224, 224))
    image_array = np.array(image_gray).reshape(-1, 224, 224, 1)
    out = model.predict(image_array)
    if out[0].argmax() == 0:
        return False
    elif out[0].argmax() == 1:
        return True
