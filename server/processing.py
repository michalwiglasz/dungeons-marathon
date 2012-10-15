import numpy
import cv
import cv2

import os
import tempfile

from math import fabs


def process_file(in_file, name='unnamed.jpg'):
    image_color = cv2.imread(in_file)
    return process_image(image_color, name)


def process_stream(in_stream):
    image_color = cv2.imdecode(numpy.fromfile(in_stream, numpy.uint8), 1)
    return process_image(image_color)


#def draw_contours(in_file):
#    image_color = cv2.imread(in_file)


def process_image(image_color, name='unnamed.jpg'):
    #image_color = cv2.resize(image_color, (1280, 848))
    image_color = cv2.blur(image_color, (3, 3))

    image_hsv = array2cv(cv2.cvtColor(image_color, cv.CV_BGR2HSV))

    mask1 = cv.CreateImage(cv.GetSize(image_hsv), 8, 1)
    mask2 = cv.CreateImage(cv.GetSize(image_hsv), 8, 1)
    mask_both = cv.CreateImage(cv.GetSize(image_hsv), 8, 1)
    demasked = cv.CreateImage(cv.GetSize(image_hsv), 8, 3)
    cv.Rectangle(demasked, (0, 0), cv.GetSize(image_hsv), cv.CV_RGB(255, 255, 255), cv.CV_FILLED)

    cv.InRangeS(image_hsv, cv.Scalar(0, 64, 100), cv.Scalar(150, 255, 255), mask1)
    cv.InRangeS(image_hsv, cv.Scalar(150, 64, 100), cv.Scalar(180, 255, 255), mask2)
    cv.Or(mask1, mask2, mask_both)
    cv.Not(mask_both, mask_both)
    cv.Copy(array2cv(image_color), demasked, mask_both)

    _, tmp = tempfile.mkstemp('.png')
    cv.SaveImage(tmp, demasked)
    demasked = cv2.imread(tmp)
    os.unlink(tmp)

    image = cv2.cvtColor(demasked, cv.CV_RGB2GRAY)
    image = cv2.equalizeHist(image)
    #image = cv2.blur(image, (3, 3))
    image = cv2.dilate(image, None, iterations=2)
    _, image = cv2.threshold(image, 80, 255, cv.CV_THRESH_BINARY + cv.CV_THRESH_OTSU)
    h, w = image.shape

    MIN_AREA = 100
    MIN_WIDTH = w * 0.1
    MAX_WIDTH = w * 0.5
    EPSILON = 0.1
    TEMPLATE = cv.LoadImage('template.jpg', 0)

    storage = cv.CreateMemStorage()
    TEMPLATE_CONTOURS = cv.FindContours(TEMPLATE, storage, mode=cv.CV_RETR_EXTERNAL, method=cv.CV_CHAIN_APPROX_NONE, offset=(0, 0))

    storage = cv.CreateMemStorage()
    im_cv = array2cv(image)
    im_cv_c = array2cv(cv2.cvtColor(image, cv.CV_GRAY2RGB))

    cv.XorS(im_cv, cv.Scalar(255, 0, 0, 0), im_cv, None)

    contours = cv.FindContours(im_cv, storage, mode=cv.CV_RETR_EXTERNAL, method=cv.CV_CHAIN_APPROX_NONE, offset=(0, 0))
    if contours:
        cv.DrawContours(im_cv, contours, (0, 0, 0), (0, 0, 0), 7, -1)

        biggestCircle = None
        BC_cnt = None
        while contours:
            area = cv.ContourArea(contours)

            if area < MIN_AREA:
                contours = contours.h_next()
                continue

            storage2 = cv.CreateMemStorage(0)
            hull = cv.ConvexHull2(contours, storage2, cv.CV_CLOCKWISE, 1)
            if hull:
                cv.PolyLine(im_cv_c, [hull], 1, cv.RGB(0, 255, 0), 4, cv.CV_AA)

                xmax, xmin, ymax, ymin = 0, w, 0, h
                for x, y in list(hull):
                    xmax = max(xmax, x)
                    ymax = max(ymax, y)
                    xmin = min(xmin, x)
                    ymin = min(ymin, y)

                cv.Rectangle(im_cv_c, (xmin, ymin), (xmax, ymax), cv.RGB(0, 255, 255), 4)

                height = (ymax - ymin)
                width = (xmax - xmin)
                if width > MAX_WIDTH or width < MIN_WIDTH:
                    contours = contours.h_next()
                    continue

                diff = cv.MatchShapes(contours, TEMPLATE_CONTOURS, cv.CV_CONTOURS_MATCH_I3)
                if diff < EPSILON:
                    cv.DrawContours(im_cv_c, contours, (255, 0, 0), (255, 0, 0), 0, -1)
                    if not biggestCircle:
                        biggestCircle = xmin, ymin, width, height
                        BC_cnt = contours
                    else:
                        if width > biggestCircle[2]:
                            biggestCircle = xmin, ymin, width, height
                            BC_cnt = contours

            contours = contours.h_next()

    if biggestCircle:
        cv.DrawContours(im_cv_c, BC_cnt, (255, 0, 255), (255, 0, 255), 0, -1)
        cv.SaveImage('contours/' + name, im_cv_c)
        return cut_it_out(image, *biggestCircle, name=name)

    #cv.SaveImage('contours/' + name, im_cv_c)
    #cv2.imwrite('gray/' + name, image_processed_color)

    cv.SaveImage('contours/' + name, im_cv_c)
    return None, None


def cut_it_out(image, left, top, width, height, name='unnamed.jpg'):
    num_top = int(top + height)
    num_height = int(height * 0.75)

    im_ipl = array2cv(image)

    # crop LETTER
    cv.SetImageROI(im_ipl, (left, top, width, height))
    cropped = cv.CloneImage(im_ipl)

    _, tmp = tempfile.mkstemp('.png')
    cv.SaveImage(tmp, cropped)
    cropped = cv2.imread(tmp)
    os.unlink(tmp)

    cropped_gray = cv2.cvtColor(cropped, cv.CV_RGB2GRAY)

    resized = cv2.resize(cropped_gray, (40, 40))
    _, bw = cv2.threshold(resized, 80, 255, cv.CV_THRESH_BINARY + cv.CV_THRESH_OTSU)

    # crop NUMBER
    cv.SetImageROI(im_ipl, (left, num_top, width, num_height))
    cropped = cv.CloneImage(im_ipl)

    _, tmp = tempfile.mkstemp('.png')
    cv.SaveImage(tmp, cropped)
    cropped = cv2.imread(tmp)
    os.unlink(tmp)

    cropped_gray = cv2.cvtColor(cropped, cv.CV_RGB2GRAY)

    bw_num = cv2.resize(cropped_gray, (640, 320))
    numbers = isolate_numbers(bw_num, name)

    return bw, numbers


def isolate_numbers(image, name='unnamed.jpg'):
    h, w = image.shape

    #MAX_WIDTH = w * 0.33
    raw_im_cv = array2cv(image)

    # Strip the black line on the bottom
    try:
        transposed = zip(*cv2array(raw_im_cv))
        left_col = list(enumerate(reversed(transposed[2])))
        right_col = list(enumerate(reversed(transposed[-2])))

        findit = lambda a: a[0][0] if a[0][1][0] == 255 else findit(a[1:])  # It just works!
        h -= max(findit(left_col), findit(right_col))

        cv.SetImageROI(raw_im_cv, (0, 0, w, h))
        raw_im_cv = cv.CloneImage(raw_im_cv)

    except IndexError:
        pass

    im_cv = cv.CreateImage((w + 6, h + 6), 8, 1)
    im_cv_c = cv.CreateImage((w + 6, h + 6), 8, 3)
    cv.Rectangle(im_cv, (0, 0), cv.GetSize(im_cv), cv.CV_RGB(255, 255, 255), cv.CV_FILLED)
    cv.CopyMakeBorder(raw_im_cv, im_cv, (3, 3), 0, (255, 255, 255))
    cv.CvtColor(im_cv, im_cv_c, cv.CV_GRAY2RGB)

    storage = cv.CreateMemStorage()
    contours = cv.FindContours(im_cv, storage, mode=cv.CV_RETR_LIST, method=cv.CV_CHAIN_APPROX_NONE, offset=(0, 0))
    if not contours:
        return None

    else:
        cv.DrawContours(im_cv, contours, (0, 0, 0), (0, 0, 0), 7, -1)

        rects = []
        i = -1
        while contours:
            i += 1

            storage2 = cv.CreateMemStorage(0)
            hull = cv.ConvexHull2(contours, storage2, cv.CV_CLOCKWISE, 1)
            if hull:
                #cv.PolyLine(im_cv_c, [hull], 1, cv.RGB(0, 255, 0), 4, cv.CV_AA)

                xmax, xmin, ymax, ymin = 0, w, 0, h
                for x, y in list(hull):
                    xmax = max(xmax, x)
                    ymax = max(ymax, y)
                    xmin = min(xmin, x)
                    ymin = min(ymin, y)

                height = (ymax - ymin)
                width = (xmax - xmin)

                if xmin < 5 or width < 50 or height < 50:
                    contours = contours.h_next()
                    continue

                #cv.Rectangle(im_cv_c, (xmin, ymin), (xmax, ymax), cv.RGB(0, 255, 255), 4)

                rects.append((xmin, ymin, xmax, ymax))

            contours = contours.h_next()

        rects = sorted(rects)
        pointer = 0
        while pointer < len(rects) - 1:
            me = rects[pointer]
            you = rects[pointer + 1]
            right = me[2]
            left = you[0]

            if right > left:
                me = (me[0],
                      min(me[1], you[1]),
                      max(me[2], you[2]),
                      max(me[3], you[3])
                      )
                rects = rects[:pointer] + [me] + rects[pointer + 2:]

            else:
                pointer += 1

        numbers = []
        #i = 0
        for xmin, ymin, xmax, ymax in rects:
            #cv.Rectangle(im_cv_c, (xmin, ymin), (xmax, ymax), cv.RGB(128, 0, 128), 4)
            cv.SetImageROI(im_cv_c, (xmin, ymin, xmax - xmin, ymax - ymin))

            dst = cv.CreateImage((40, 40), 8, 3)
            cv.Resize(im_cv_c, dst)
            #cv.SaveImage('numbers/' + name.replace('.jpg', '.%d.jpg' % (i,)), dst)

            _, tmp = tempfile.mkstemp('.png')
            cv.SaveImage(tmp, dst)
            dst = cv2.imread(tmp, 0)
            os.unlink(tmp)
            _, bw = cv2.threshold(dst, 80, 255, cv.CV_THRESH_BINARY + cv.CV_THRESH_OTSU)
            numbers.append(bw)
            #i += 1

        return numbers


def cv2array(im):
  depth2dtype = {
        cv.IPL_DEPTH_8U: 'uint8',
        cv.IPL_DEPTH_8S: 'int8',
        cv.IPL_DEPTH_16U: 'uint16',
        cv.IPL_DEPTH_16S: 'int16',
        cv.IPL_DEPTH_32S: 'int32',
        cv.IPL_DEPTH_32F: 'float32',
        cv.IPL_DEPTH_64F: 'float64',
    }

  arrdtype=im.depth
  a = numpy.fromstring(
         im.tostring(),
         dtype=depth2dtype[im.depth],
         count=im.width*im.height*im.nChannels)
  a.shape = (im.height,im.width,im.nChannels)
  return a

def array2cv(a):
  dtype2depth = {
        'uint8':   cv.IPL_DEPTH_8U,
        'int8':    cv.IPL_DEPTH_8S,
        'uint16':  cv.IPL_DEPTH_16U,
        'int16':   cv.IPL_DEPTH_16S,
        'int32':   cv.IPL_DEPTH_32S,
        'float32': cv.IPL_DEPTH_32F,
        'float64': cv.IPL_DEPTH_64F,
    }
  try:
    nChannels = a.shape[2]
  except:
    nChannels = 1
  cv_im = cv.CreateImageHeader((a.shape[1],a.shape[0]),
dtype2depth[str(a.dtype)], nChannels)
  cv.SetData(cv_im, a.tostring(),a.dtype.itemsize*nChannels*a.shape[1])
  return cv_im








    # result = cv2.matchTemplate(image_processed_color, template,
    #                            cv.CV_TM_SQDIFF_NORMED)
    # (min_x, max_y, min_loc, max_loc) = cv2.minMaxLoc(result)
    # x = int(min_loc[0])
    # y = int(min_loc[1])

    # print(image_processed_color, (x, y), (int, maxLoc), cv.CV_RGB(255, 0, 0), 2)


# A = lambda l, r: (l[1] - r[1]) / (l[0] - r[0])
# B = lambda l, r: (r[0] * l[1] - r[1] * l[0]) / (r[0] - l[0])
# AB = lambda l, r: (A(l, r), B(l, r))

# def newpoint(old):
#     a, b = AB(old[0], old[1])
#     x = [0, b]
#     y = [w, a * w + b]
#     return (x, y)

# tl, tr = newpoint((tl, tr))
# bl, br = newpoint((bl, br))

# cv2.circle(image_processed_color, tuple(tl), 13, cv.CV_RGB(255, 255, 0), -2) # zluta
# cv2.circle(image_processed_color, tuple(tr), 13, cv.CV_RGB(255, 0, 255), -2) # fialova
# cv2.circle(image_processed_color, tuple(bl), 13, cv.CV_RGB(0, 255, 255), -2) # modra
# cv2.circle(image_processed_color, tuple(br), 13, cv.CV_RGB(255, 255, 255), -2) # bila

# src = numpy.array([tl, br, bl, tr], numpy.float32)
# dst = numpy.array([tl, br, [bl[0], br[1]], [tr[0], tl[1]]], numpy.float32)
# matrix = cv2.getPerspectiveTransform(src, dst)

# image_processed_color = cv2.warpPerspective(image_processed_color, matrix, (w, h))
# image = cv2.warpPerspective(image, matrix, (w, h))








        # edges = cv2.Canny(image, 100, 200)
        # lines = cv2.HoughLinesP(edges, 1, cv.CV_PI/360, 50, None, 100, 10)

        # if lines is not None:

        #     # init top/bottom
        #     x1, y1, x2, y2 = lines[0][0]
        #     top, bottom = None, None

        #     for x1, y1, x2, y2 in lines[0]:
        #         # first point is more on top
        #         if y1 > y2:
        #             x1, y1, x2, y2 = x2, y2, x1, y1

        #         # if horizontal
        #         if abs(y1 - y2) < abs(x1 - x2):
        #             if not top:
        #                 top = ([x1, y1], [x2, y2])
        #                 bottom = ([x1, y1], [x2, y2])

        #             elif y1 < top[0][1]:
        #                 top = ([x1, y1], [x2, y2])
        #             elif y1 > bottom[1][1]:
        #                 bottom = ([x1, y1], [x2, y2])

        #         cv2.line(image_processed_color, (x1, y1), (x2, y2), cv.CV_RGB(255, 0, 0), 2)

        # tl = top[0] if top[0][0] < top[1][0] else top[1]
        # tr = top[1] if top[0][0] < top[1][0] else top[0]
        # bl = bottom[0] if bottom[0][0] < bottom[1][0] else bottom[1]
        # br = bottom[1] if bottom[0][0] < bottom[1][0] else bottom[0]



        # cv2.line(image_processed_color, tuple(top[0]), tuple(top[1]), cv.CV_RGB(0, 255, 0), 2)
        # cv2.line(image_processed_color, tuple(bottom[0]), tuple(bottom[1]), cv.CV_RGB(0, 0, 255), 2)

        # cv2.circle(image_processed_color, tuple(tl), 13, cv.CV_RGB(255, 255, 0), -2) # zluta
        # cv2.circle(image_processed_color, tuple(tr), 13, cv.CV_RGB(255, 0, 255), -2) # fialova
        # cv2.circle(image_processed_color, tuple(bl), 13, cv.CV_RGB(0, 255, 255), -2) # modra
        # cv2.circle(image_processed_color, tuple(br), 13, cv.CV_RGB(255, 255, 255), -2) # bila
