#from pybrain.tools.shortcuts import buildNetwork
from pybrain.datasets import ClassificationDataSet
#from pybrain.supervised.trainers import BackpropTrainer
from pybrain.tools.neuralnets import NNclassifier

import cv2

import os


IMG_SIZE = 40
CLASSES = ['A', 'C', 'L']

#net = buildNetwork(IMG_SIZE * IMG_SIZE, 800, len(CLASSES))
training_set = ClassificationDataSet(IMG_SIZE * IMG_SIZE, nb_classes=len(CLASSES), class_labels=CLASSES)

for i, cls in enumerate(CLASSES):
    directory = os.path.join('train', cls)
    for f in os.listdir(directory):
        img = os.path.join(directory, f)
        img, _, _ = cv2.split(cv2.imread(os.path.join(directory, f)))
        img = tuple([int(item) for sublist in img for item in sublist])
        training_set.addSample(img, (i,))

print "%d items in dataset" % (len(training_set),)

classifier = NNclassifier(training_set)
classifier.setupNN()
classifier.runTraining()
classifier.saveNetwork('network.xml')
