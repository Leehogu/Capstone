import sys
import cv2
import dlib
import glob
import openface
import os


def load_image(file_name, mode=cv2.IMREAD_ANYCOLOR):
    img = cv2.imread(file_name, mode)  # load 3 dimensional array 	# 이미지를 3차원 배열로 읽어오는 함수
    return img


if __name__ == "__main__":

    file_path = sys.argv[1]
    file_name = sys.argv[2]
    predictor_model ="D:/Study/FaceDataSet/shape_predictor_68_face_landmarks.dat"
    train_img_dir = file_path + "/" + file_name
    save_path = "D:/Study/Capstone/CoreTech/enroll_img"

    img = load_image(train_img_dir)
    face_detector = dlib.get_frontal_face_detector()
    face_pose_predictor = dlib.shape_predictor(predictor_model)
    face_aligner = openface.AlignDlib(predictor_model)
    detected_faces = face_detector(img, 1)
    for j, face_rect in enumerate(detected_faces):
        left, right, top, bottom = face_rect.left(), face_rect.right(), face_rect.top(), face_rect.bottom()  # 좌우상하 값을 옮겨받는다.
        try:
            pose_landmarks = face_pose_predictor(img, face_rect)
            alignedFace = face_aligner.align(100, img, face_rect, landmarkIndices=openface.AlignDlib.OUTER_EYES_AND_NOSE)
            cv2.imwrite(save_path + "/" + file_name, alignedFace)
        except Exception as ex:
            print(ex)

    os.remove(train_img_dir)
