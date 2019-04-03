# import tensorflow as tf
from tflearn.layers.core import input_data, fully_connected, dropout
from tflearn.layers.conv import conv_2d, max_pool_2d
from tflearn.layers.estimator import regression
import tflearn


def makeModel():

    # tf.reset_default_graph()
    covnet = input_data(shape=[None, 224, 224, 1], name='input')
    covnet = conv_2d(covnet, 32, filter_size=(3, 3), strides=1, padding='SAME', activation='relu')
    covnet = max_pool_2d(covnet, 5)
    covnet = conv_2d(covnet, 64, filter_size=(3, 3), strides=1, padding='SAME', activation='relu')
    covnet = max_pool_2d(covnet, 5)
    covnet = conv_2d(covnet, 128, filter_size=(3, 3), strides=1, padding='SAME', activation='relu')
    covnet = max_pool_2d(covnet, 5)
    covnet = conv_2d(covnet, 64, filter_size=(3, 3), strides=1, padding='SAME', activation='relu')
    covnet = max_pool_2d(covnet, 5)
    covnet = conv_2d(covnet, 32, filter_size=(3, 3), strides=1, padding='SAME', activation='relu')
    covnet = max_pool_2d(covnet, 5)
    covnet = fully_connected(covnet, 1024, activation='relu')
    covnet = dropout(covnet, 0.8)
    covnet = fully_connected(covnet, 2, activation='softmax')
    covnet = regression(covnet, optimizer='adam', learning_rate=0.001, loss='categorical_crossentropy')
    model = tflearn.DNN(covnet)

    return model
