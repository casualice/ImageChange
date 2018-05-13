package Controller;

import Changer.ImageMark;
import Changer.UploadImages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {


    @Value("${Image.savePath}")
    private String ImageSavePath;

    @RequestMapping("/index")
    public String page(){
        return "index";
    }

    @PostMapping("/pushImage")
    @ResponseBody
    public void pushImage(HttpServletRequest request,  @RequestParam(value="file",required = false) MultipartFile image,@RequestParam(value = "text")String message) {
        UploadImages uploadImages = new UploadImages();
        String path1 = request.getSession().getServletContext().getContextPath();  //上传的路径
        String path2 = "UploadFiles";
        String bigImg = uploadImages.upLoadImage(request, image, path1, path2);
        try {
            ImageMark imageMark = new ImageMark();
            String path = imageMark.markByText2("E://images//haschanged"+bigImg,ImageSavePath,"jpg",message,image.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return "/download";
    }
}
