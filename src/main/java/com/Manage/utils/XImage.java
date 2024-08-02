package com.Manage.utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;


public class XImage {
    public static Image getAppIcon(){
        //đường link dẫn tới thư mục hình ảnh
        URL url = XImage.class.getResource("D:\\DuAn1_ps39536\\src\\main\\resources\\anhCSDL");// getResource(): lấy đg dẫn tới file hinh trong thu mục hinh
        return new ImageIcon(url).getImage(); // ImageIcon từ đường dẫn và lấy ra Image từ ImageIcon.
    }
    
    // nó qét hết 1 đg dẫn trong file thư mục 
    public static boolean save(File src){// (File src) cái biến chèn dô
        File dst = new File("anhCSDL", src.getName());// xong nó sẽ chạy đến file thư mục lấy tên của getname
        if(!dst.getParentFile().exists()){ //  nếu như thu mục hinh khong tồn tại 
            dst.getParentFile().mkdirs(); //nó sẽ đó bằng tạo thư mục đó bằng cách sd mkdirs.
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());// sử dụng hàm Path
            Path to = Paths.get(dst.getAbsolutePath());
            // Files.copy để copy file từ đg dẫn nguồn from sang đg dẫn đích to
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);//REPLACE_EXISTING để ghi đè nếu file đích đẫ tồn tại
            return true; // nếu 0 có lỗi xảy ra nó sẽ trả về true
        } 
        catch (Exception e) {
            return true;
        }
    }
    
    public static ImageIcon read(String fileName){
        File path = new File("anhCSDL", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
