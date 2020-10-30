package com.zehua.review.io;

import java.io.*;

/**
 * @Auther zehua
 * @Date 2020/10/30 9:09
 **/
public class CopyPicture {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String pictureName = "picture.jpg";
        String copyPictureName = "copyPicture.jpg";
        String fullName = path + File.separator + pictureName;
        String copyPicturefullName = path + File.separator + copyPictureName;

        File file = new File(fullName);
        File copyPictureFile = new File(copyPicturefullName);
        if (!file.exists()) {
            System.out.println("picture does not exist ,return");
            return;
        }

        try (
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(copyPictureFile);
        ) {
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }

            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
