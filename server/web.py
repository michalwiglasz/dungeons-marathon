import cv2
import os
import tempfile
from processing import process_stream
from flask import Flask, request
from pybrain.tools.xml.networkreader import NetworkReader


app = Flask(__name__)
net = NetworkReader.readFrom('network.xml')
net_nums = NetworkReader.readFrom('network_nums.xml')
CLASSES = ['A', 'C', 'L']
NUMS = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
ALLOWED_CHARS = '0-9' + ''.join(CLASSES)


@app.route("/img", methods=['POST'])
def img():
    if 'img' in request.files:
        img = request.files['img']
        if isinstance(img.stream, file):
            letter, numbers = process_stream(img.stream)

        else:  # possibly StringIO
            handle, tmp = tempfile.mkstemp('.jpg', prefix="image.", dir='./usr')
            os.write(handle, img.stream.getvalue())
            letter, numbers = process_stream(tmp)
            os.close(handle)

    else:
        # from android app
        image_data = request.data
        _, tmp = tempfile.mkstemp('.jpg', prefix="image.", dir='./usr')
        print tmp
        with open(tmp, mode='wb') as f:
            f.write(image_data)
        letter, numbers = process_stream(tmp)

    if letter is not None:
        #cv2.imwrite('letter.png', letter)
        #cv2.imwrite('number.png', number)
        _, tmp = tempfile.mkstemp('.jpg', prefix="letter.", dir='./usr')
        cv2.imwrite(tmp, letter)
        letter = tuple([int(item) for sublist in letter for item in sublist])
        result = net.activate(letter)
        print result
        result_letter = sorted(zip(result, CLASSES), reverse=1)[0][1]

        result_number = []
        for i, number in enumerate(numbers):
            _, tmp = tempfile.mkstemp('.jpg', prefix="num.", dir='./usr')
            cv2.imwrite(tmp, number)
            number = tuple([int(item) for sublist in number for item in sublist])
            result = net_nums.activate(number)
            print result
            result_number.append(sorted(zip(result, NUMS), reverse=1)[0][1])

        result = result_letter + ''.join(result_number)

    else:
        result = "???"

    print str(result)
    return str(result)


@app.route("/test")
def test():
    return '<form action="img" method=post enctype="multipart/form-data"><input type=file name=img><input type=submit>'


if __name__ == "__main__":
    app.run(host='0.0.0.0', debug=True)
