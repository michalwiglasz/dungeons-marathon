from processing import process_file
import cv2
import os

in_dir = './new2'
out_dir = './results2'


def main():
    for f in os.listdir(in_dir):
        if f[-4:] == '.jpg':
            print os.path.join(in_dir, f)

            letter, numbers = process_file(os.path.join(in_dir, f), f)
            out = os.path.join(out_dir, os.path.basename(f))
            if letter is not None:
                cv2.imwrite(out.replace('.jpg', '.letter.jpg'), letter)
                for i, number in enumerate(numbers):
                    cv2.imwrite(out.replace('.jpg', '.number.%d.jpg' % (i,)), number)


if __name__ == '__main__':
    main()
