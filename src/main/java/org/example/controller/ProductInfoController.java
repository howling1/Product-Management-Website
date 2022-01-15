package org.example.controller;

import com.github.pagehelper.PageInfo;
import org.example.pojo.ProductInfo;
import org.example.pojo.vo.ProductSearchVo;
import org.example.service.ProductInfoService;
import org.example.utils.FileNameUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoController {
    private static final int PAGE_SIZE = 5;

    private String fileName = "";

    @Autowired
    ProductInfoService productInfoService;

    @RequestMapping("/clearImageFileName.action")
    public ResponseEntity clearImageFileName(){
        fileName = "";
        return new ResponseEntity(HttpStatus.OK);
    }

    // display first page of products
    @RequestMapping("/displayProds.action")
    public String displayFistPage(HttpServletRequest httpServletRequest){
        PageInfo<ProductInfo> productInfos = productInfoService.getPaginatedProducts(1, PAGE_SIZE);
        httpServletRequest.setAttribute("info",productInfos);
        return "product";
    }

    // @ResponseBody changes the return data to json type and put it in the response body
    @ResponseBody
    @RequestMapping("/ajaxSplit.action")
    public void ajaxPaginate(ProductSearchVo productSearchVo, HttpSession session){
        if (productSearchVo.getTypeid() == -1){
            productSearchVo.setTypeid(null);
        }

        PageInfo<ProductInfo> pageInfo = productInfoService.paginateProductsByConditionSearch(productSearchVo,PAGE_SIZE);
        session.setAttribute("info",pageInfo);
    }

    //handle product image asynchronous ajax uploading;
    //ajax need the function return json, so return Object
    //spring provides a class to accept uploaded document from the frontend, which is binded with bean
    @ResponseBody
    @RequestMapping("/ajaxImg.action")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest httpServletRequest) {

        //generate new file name
        this.fileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());

        //get absolute path of folder for storing upload product images
        String path = httpServletRequest.getServletContext().getRealPath("/image_big");

        //Save the file received from the front end to the above path
        try {
            pimage.transferTo(new File(path+File.separator+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imgurl", fileName);

        return jsonObject.toString();
    }

    @RequestMapping("save.action")
    public String createProduct(ProductInfo productInfo, HttpServletRequest httpServletRequest){
        productInfo.setpDate(new Date());
        productInfo.setpImage(fileName);

        Integer num = -1;
        try {
            num = productInfoService.save(productInfo);
            if (num == 1){
                httpServletRequest.setAttribute("msg", "Add commodity successfully!");
            } else {
                httpServletRequest.setAttribute("msg", "Fail to add commodity!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        // clear fileName
        clearImageFileName();

        return "forward:/prod/displayProds.action";
    }

    @RequestMapping("/getProductById.action")
    public String getProductById(Integer pid, HttpServletRequest httpServletRequest){
        ProductInfo productInfo = productInfoService.getProductById(pid);
        httpServletRequest.setAttribute("prod", productInfo);

        return "update";
    }

    @RequestMapping("/updateProductInfo.action")
    public String updateProductInfo(ProductInfo productInfo, HttpServletRequest httpServletRequest){
        //If the front end uploads a new picture via Ajax,
        // then fileName is the generated name of the newly uploaded picture.
        // If not, then fileNme="", so when saving this new productInfo,
        // use the pImage provided in the hidden form field of the front end(the original picture’s name)
        if (!fileName.equals("")){
            productInfo.setpImage(fileName);
        }

        Integer num = -1;
        try {
            num = productInfoService.updateProductInfo(productInfo);
            if (num == 1){
                httpServletRequest.setAttribute("msg", "update commodity successfully!");
            } else {
                httpServletRequest.setAttribute("msg", "Fail to update commodity!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        // clear fileName
        clearImageFileName();

        return "forward:/prod/displayProds.action";
    }

    @RequestMapping("/delete.action")
    @ResponseBody
    public String deleteSingleProduct(Integer pid, HttpServletRequest httpServletRequest){
        String msg = "";
        Integer num = -1;
        PageInfo pageInfo = null;
        try {
            num = productInfoService.deleteProduct(pid);
            if (num == 1){
                msg =  "delete single item successfully!";
            } else {
               msg =  "fail to delete!";
            }
        } catch (Exception e){
            e.printStackTrace();
            msg =  "This commodity is non-deletable!";
        } finally {
            pageInfo = productInfoService.getPaginatedProducts(1, PAGE_SIZE) ;
            httpServletRequest.getSession().setAttribute("info",pageInfo);
            return msg;
        }
    }

    @RequestMapping("/deleteBatch.action")
    @ResponseBody
    public String deleteBatch(String pids, HttpServletRequest httpServletRequest){
        System.out.println("test");
        String []pids_array = pids.split(",");
        String msg = "";
        int num = -1;
        PageInfo pageInfo = null;

        try {
            num = productInfoService.deleteBatchProducts(pids_array);
            if (num > 0){
                msg = "Batch deletion succeeds!";
            } else {
                msg = "Batch deletion fails!";
            }
        } catch (Exception e){
            e.printStackTrace();
            msg = "There exist non-deletable commodities！";
        } finally {
            pageInfo = productInfoService.getPaginatedProducts(1, PAGE_SIZE) ;
            httpServletRequest.getSession().setAttribute("info",pageInfo);
            return msg;
        }
    }
}
