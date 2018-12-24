package xb.dev.document.temp;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xb.dev.document.model.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * excel parse tool
 * create by huangxb on 2018/6/21
 */
public class ExcelUtils {

    private final static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 表格解析
     *
     * @param target 与表格记录定义的实体类
     * @param path   本地文件路径
     * @param logger 日志记录
     * @param <T>
     * @return 返回map对象，key为excel中工作表名称，value为该工作表解析出的记录列表
     */
    public static <T> Map<String, List<T>> excelParse(Class<T> target, String path, Logger logger) throws ExcelParseException {

        Map<String, List<T>> results = new ExcelHandler<T>(target, path, logger).parse();
        return results;
    }

    /**
     * 表格解析
     *
     * @param target 与表格记录定义的实体类
     * @param is     excel字节流对象
     * @param logger 日志记录
     * @param <T>
     * @return 返回map对象，key为excel中工作表名称，value为该工作表解析出的记录列表
     */
    public static <T> Map<String, List<T>> excelParse(Class<T> target, InputStream is, Logger logger) throws ExcelParseException {
        Map<String, List<T>> results = new ExcelHandler<T>(target, is, logger).parse();
        return results;
    }

    public static void main(String[] args) throws Exception {
        //海关编码类目
//        hsCatagory();
        //添加类目
//        category();
        //添加类目
//        backCategory();
//      mallBackCategory();
        mallCategory();
        //添加参数
//        prop();
        //添加商品参数
//        goodsProp();
    }


    public static void goodsProp() throws Exception {
        Map<String, List<MallExcelPropData>> map = excelParse(MallExcelPropData.class, "D:/hscode/mall-prop/prop.xlsx", logger);
        //属性列表
        List<MallGoodsProp> inserts = new ArrayList<>();

        List<MallExcelPropData> result = map.get("Sheet1");
        boolean start = false;
        //最终数据
        List<MallPropData> goodsPropList = new ArrayList<>();
        String[] category = null;
        for (MallExcelPropData g : result) {
            if (g.getPropEn() != null && !"".equals(g.getPropEn().trim())) {
                //等于product name
                if (g.getPropEn().replaceAll("\\s", "").equalsIgnoreCase("Product name".replaceAll("\\s", ""))) {
                    if (start) {//start = true 表示遇到的是第二个产品名称
                        //表示到达下一属性集合，此时应该进行保存操作再继续向下
                        MallPropData goodsPropInsert = new MallPropData();
                        goodsPropInsert.setCategory(category);
                        List<MallGoodsProp> list = new ArrayList<>(inserts);
                        goodsPropInsert.setMallGoodsProp(list);
                        category = g.getCategory().split("，");
                        goodsPropList.add(goodsPropInsert);
                        inserts.clear();
                        MallGoodsProp goodsProp = new MallGoodsProp();
                        goodsProp.setPropZh(g.getPropZh());
                        goodsProp.setPropEn(g.getPropEn());
                        goodsProp.setPropType(g.getPropType());
                        goodsProp.setInputType(g.getInputType());
                        goodsProp.setNav(g.getNav());
                        goodsProp.setRequired(g.getRequired());
                        if (g.getPropValueEn() != null) {
                            goodsProp.setPropValueEn(g.getPropValueEn().split("、"));
                        }
                        if (g.getPropValueZh() != null) {
                            goodsProp.setPropValueZh(g.getPropValueZh().split("、"));
                        }
                        inserts.add(goodsProp);


                    } else {//start = false 表示遇到的是第一个个产品名称
                        //不等于product name表示是参数
                        MallGoodsProp goodsProp = new MallGoodsProp();
                        category = g.getCategory().split("，");
                        goodsProp.setPropZh(g.getPropZh());
                        goodsProp.setPropEn(g.getPropEn());
                        goodsProp.setPropType(g.getPropType());
                        goodsProp.setInputType(g.getInputType());
                        goodsProp.setNav(g.getNav());
                        goodsProp.setRequired(g.getRequired());
                        if (g.getPropValueEn() != null) {
                            goodsProp.setPropValueEn(g.getPropValueEn().split("、"));
                        }
                        if (g.getPropValueZh() != null) {
                            goodsProp.setPropValueZh(g.getPropValueZh().split("、"));
                        }
                        inserts.add(goodsProp);
                        start = true;
                    }
                } else {
                    //不等于product name表示是参数
                    MallGoodsProp goodsProp = new MallGoodsProp();
                    goodsProp.setPropZh(g.getPropZh());
                    goodsProp.setPropEn(g.getPropEn());
                    goodsProp.setPropType(g.getPropType());
                    goodsProp.setInputType(g.getInputType());
                    goodsProp.setNav(g.getNav());
                    goodsProp.setRequired(g.getRequired());
                    if (g.getPropValueEn() != null) {
                        goodsProp.setPropValueEn(g.getPropValueEn().split("、"));
                    }
                    if (g.getPropValueZh() != null) {
                        goodsProp.setPropValueZh(g.getPropValueZh().split("、"));
                    }
                    inserts.add(goodsProp);
                }
            }
        }
        OutputStream os = new FileOutputStream("D:/hscode/mall-prop/prop.json");
        JSON.writeJSONString(os, goodsPropList);
        os.close();
        System.out.println();
    }

    public static void prop() throws Exception {
        Map<String, List<PropData>> map = excelParse(PropData.class, "D:/prop.xlsx", logger);
        List<PropData> result = map.get("大宗类目透视表");
        String prop = "";
        String one = "";
        String two = "";
        String three = "";
        String error = "(空白),#N/A";
        for (PropData c : result) {
            if (c.getProp() != null && !"".equals(c.getProp().trim()) && !"(空白)".equals(c.getProp().trim())) {
                prop = c.getProp();
            }
            if (c.getOne() != null && (!"".equals(c.getOne().trim()))) {
                one = c.getOne();
            }
            if (c.getTwo() != null && (!"".equals(c.getTwo().trim()))) {
                two = c.getTwo();
            }
            if (c.getThree() != null && (!"".equals(c.getThree().trim()))) {
                three = c.getThree();
            }
            if (prop.indexOf(error) > -1 || one.indexOf(error) > -1 || two.indexOf(error) > -1 || three.indexOf(error) > -1) {
                //
            } else {
                insertProp(prop, one, two, three);
            }
        }
    }

    public static void category() throws Exception {
        Map<String, List<CategoryData>> map = excelParse(CategoryData.class, "D:/hscode/material-category/category.xlsx", logger);
        List<CategoryModel> categoryModels = new ArrayList<>(32);
        CategoryModel zero = null;
        CategoryModel one = null;
        CategoryModel two = null;
        List<CategoryData> result = map.get("前台类目模板");
        for (CategoryData c : result) {
            if (c.getZero() != null && (!"".equals(c.getZero().trim()))) {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCategoryNameCn(c.getZero());
                categoryModel.setCategoryNameEn(c.getZeroEn());
                categoryModel.setSort(1);
                zero = categoryModel;
                categoryModels.add(categoryModel);
            }
            if (c.getOne() != null && (!"".equals(c.getOne().trim()))) {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCategoryNameCn(c.getOne());
                categoryModel.setCategoryNameEn(c.getOneEn());
                categoryModel.setSort(1);
                if (zero.getChilds() == null) {
                    List<CategoryModel> list = new ArrayList<>();
                    list.add(categoryModel);
                    zero.setChilds(list);
                } else {
                    zero.getChilds().add(categoryModel);
                }
                one = categoryModel;
            }
            if (c.getTwo() != null && (!"".equals(c.getTwo().trim()))) {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCategoryNameCn(c.getTwo());
                categoryModel.setCategoryNameEn(c.getTwoEn());
                categoryModel.setSort(1);
                if (one.getChilds() == null) {
                    List<CategoryModel> list = new ArrayList<>();
                    list.add(categoryModel);
                    one.setChilds(list);
                } else {
                    one.getChilds().add(categoryModel);
                }
                two = categoryModel;
            }
            if (c.getRelated() != null && (!"".equals(c.getRelated().trim()))) {
                if (two.getRelated() == null) {
                    List<String> list = new ArrayList<>();
                    list.add(c.getRelated());
                    two.setRelated(list);
                } else {
                    two.getRelated().add(c.getRelated());
                }
            }
        }
        OutputStream os = new FileOutputStream("D:/hscode/material-category/category.json");
        JSON.writeJSONString(os, categoryModels);
        os.close();
        System.out.println();
    }

    public static void backCategory() throws Exception {
        Map<String, List<BackCategoryData>> map = excelParse(BackCategoryData.class, "D:/hscode/material-category/category.xlsx", logger);
        List<CategoryModel> categoryModels = new ArrayList<>(32);
        CategoryModel zero = null;
        CategoryModel one = null;
        CategoryModel two = null;
        List<BackCategoryData> result = map.get("后台类目模板");
        for (BackCategoryData c : result) {
            if (c.getZero() != null && (!"".equals(c.getZero().trim()))) {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCategoryNameCn(c.getZero());
                categoryModel.setCategoryNameEn(c.getZeroEn());
                categoryModel.setSort(1);
                zero = categoryModel;
                categoryModels.add(categoryModel);
            }
            if (c.getOne() != null && (!"".equals(c.getOne().trim()))) {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCategoryNameCn(c.getOne());
                categoryModel.setCategoryNameEn(c.getOneEn());
                categoryModel.setSort(1);
                if (zero.getChilds() == null) {
                    List<CategoryModel> list = new ArrayList<>();
                    list.add(categoryModel);
                    zero.setChilds(list);
                } else {
                    zero.getChilds().add(categoryModel);
                }
                one = categoryModel;
            }
            if (c.getTwo() != null && (!"".equals(c.getTwo().trim()))) {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCategoryNameCn(c.getTwo());
                categoryModel.setCategoryNameEn(c.getTwoEn());
                categoryModel.setSort(1);
                if (one.getChilds() == null) {
                    List<CategoryModel> list = new ArrayList<>();
                    list.add(categoryModel);
                    one.setChilds(list);
                } else {
                    one.getChilds().add(categoryModel);
                }
                two = categoryModel;
            }
            if (c.getThree() != null && (!"".equals(c.getThree().trim()))) {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCategoryNameCn(c.getThree());
                categoryModel.setCategoryNameEn(c.getThreeEn());
                categoryModel.setSort(1);
                if (two.getChilds() == null) {
                    List<CategoryModel> list = new ArrayList<>();
                    list.add(categoryModel);
                    two.setChilds(list);
                } else {
                    two.getChilds().add(categoryModel);
                }
            }
        }
        OutputStream os = new FileOutputStream("D:/hscode/material-category/backCategory.json");
        JSON.writeJSONString(os, categoryModels);
        os.close();
        System.out.println();
    }

    public static void mallCategory() throws Exception {
        Map<String, List<CategoryData>> map = excelParse(CategoryData.class, "D:/hscode/mall-category/category.xlsx", logger);
        Map<String, MallCategoryModel> categoryModels = new HashMap<>(64);
        List<CategoryData> result = map.get("前台类目模板");
        for (CategoryData c : result) {
            String cateZero = c.getZero();
            String cateOne = c.getOne();
            String cateTwo = c.getTwo();
            String relate = c.getRelated();
            Map<String, MallCategoryModel> one = null;
            Map<String, MallCategoryModel> two = null;
            //一级类目不为空
            if (cateZero != null && (!"".equals(cateZero))) {
                MallCategoryModel categoryModel = new MallCategoryModel();
                //赋值
                categoryModel.setCategoryNameCn(cateZero);
                categoryModel.setCategoryNameEn(c.getZeroEn());
                categoryModel.setSort(1);
                if (categoryModels.get(cateZero) == null) {
                    categoryModels.put(cateZero, categoryModel);
                }
            }
            //二级类目不为空
            if (cateOne != null && (!"".equals(cateOne))) {
                //父级
                MallCategoryModel cc = categoryModels.get(cateZero);
                //构造类目信息
                MallCategoryModel categoryModel = new MallCategoryModel();
                categoryModel.setCategoryNameCn(cateOne);
                categoryModel.setCategoryNameEn(c.getOneEn());
                categoryModel.setSort(1);
                //父级子节点为空
                if (cc.getChilds() == null) {
                    Map<String, MallCategoryModel> oneMap = new HashMap<>(64);
                    oneMap.put(cateOne, categoryModel);
                    cc.setChilds(oneMap);
                } else {
                    //父级子节点不为空
                    //子节点还不包含当前类目
                    if (cc.getChilds().get(cateOne) == null) {
                        cc.getChilds().put(cateOne, categoryModel);
                    }
                }
                one = cc.getChilds();
            }
            //三级类目不为空
            if (cateTwo != null && (!"".equals(cateTwo))) {
                //父级
                MallCategoryModel cc = one.get(cateOne);
                //构造类目信息
                MallCategoryModel categoryModel = new MallCategoryModel();
                categoryModel.setCategoryNameCn(cateTwo);
                categoryModel.setCategoryNameEn(c.getTwoEn());
                categoryModel.setSort(1);

                //父级子节点为空
                if (cc.getChilds() == null) {
                    Map<String, MallCategoryModel> oneMap = new HashMap<>(64);
                    oneMap.put(cateTwo, categoryModel);
                    cc.setChilds(oneMap);
                } else {
                    //父级子节点不为空
                    //子节点还不包含当前类目
                    if (cc.getChilds().get(cateTwo) == null) {
                        cc.getChilds().put(cateTwo, categoryModel);
                    }
                }
                two = cc.getChilds();
            }

            if (relate != null && (!"".equals(relate))) {
                //父级
                MallCategoryModel cc = two.get(cateTwo);

                if (cc.getRelated() == null) {
                    List<String> r = new ArrayList<>();
                    r.add(relate);
                    cc.setRelated(r);
                } else {
                    cc.getRelated().add(relate);
                }
            }
        }

        OutputStream os = new FileOutputStream("D:/hscode/mall-category/category.json");
        List<CategoryModel> res = aggregateResult(categoryModels.values());
        JSON.writeJSONString(os, res);
        os.close();
        System.out.println();
    }

    public static void mallBackCategory() throws Exception {
        Map<String, List<BackCategoryData>> map = excelParse(BackCategoryData.class, "D:/hscode/mall-category/category.xlsx", logger);
        Map<String, MallCategoryModel> categoryModels = new HashMap<>(64);
        List<BackCategoryData> result = map.get("后台类目模板");
        for (BackCategoryData c : result) {
            String cateZero = c.getZero();
            String cateOne = c.getOne();
            String cateTwo = c.getTwo();
            String cateThree = c.getThree();
            Map<String, MallCategoryModel> one = null;
            Map<String, MallCategoryModel> two = null;
            //一级类目不为空
            if (cateZero != null && (!"".equals(cateZero))) {
                MallCategoryModel categoryModel = new MallCategoryModel();
                //赋值
                categoryModel.setCategoryNameCn(cateZero);
                categoryModel.setCategoryNameEn(c.getZeroEn());
                categoryModel.setSort(1);
                if (categoryModels.get(cateZero) == null) {
                    categoryModels.put(cateZero, categoryModel);
                }
            }
            //二级类目不为空
            if (cateOne != null && (!"".equals(cateOne))) {
                //父级
                MallCategoryModel cc = categoryModels.get(cateZero);
                //构造类目信息
                MallCategoryModel categoryModel = new MallCategoryModel();
                categoryModel.setCategoryNameCn(cateOne);
                categoryModel.setCategoryNameEn(c.getOneEn());
                categoryModel.setSort(1);
                //父级子节点为空
                if (cc.getChilds() == null) {
                    Map<String, MallCategoryModel> oneMap = new HashMap<>(64);
                    oneMap.put(cateOne, categoryModel);
                    cc.setChilds(oneMap);
                } else {
                    //父级子节点不为空
                    //子节点还不包含当前类目
                    if (cc.getChilds().get(cateOne) == null) {
                        cc.getChilds().put(cateOne, categoryModel);
                    }
                }
                one = cc.getChilds();
            }
            //三级类目不为空
            if (cateTwo != null && (!"".equals(cateTwo))) {
                //父级
                MallCategoryModel cc = one.get(cateOne);
                //构造类目信息
                MallCategoryModel categoryModel = new MallCategoryModel();
                categoryModel.setCategoryNameCn(cateTwo);
                categoryModel.setCategoryNameEn(c.getTwoEn());
                categoryModel.setSort(1);

                //父级子节点为空
                if (cc.getChilds() == null) {
                    Map<String, MallCategoryModel> oneMap = new HashMap<>(64);
                    oneMap.put(cateTwo, categoryModel);
                    cc.setChilds(oneMap);
                } else {
                    //父级子节点不为空
                    //子节点还不包含当前类目
                    if (cc.getChilds().get(cateTwo) == null) {
                        cc.getChilds().put(cateTwo, categoryModel);
                    }
                }
                two = cc.getChilds();
            }

            if (cateThree != null && (!"".equals(cateThree))) {
                //父级
                MallCategoryModel cc = two.get(cateTwo);
                //构造类目信息
                MallCategoryModel categoryModel = new MallCategoryModel();
                categoryModel.setCategoryNameCn(cateThree);
                categoryModel.setCategoryNameEn(c.getThreeEn());
                categoryModel.setSort(1);

                if (cc.getChilds() == null) {
                    Map<String, MallCategoryModel> oneMap = new HashMap<>(64);
                    oneMap.put(cateThree, categoryModel);
                    cc.setChilds(oneMap);
                } else {
                    cc.getChilds().put(cateThree, categoryModel);
                }
            }
        }
        OutputStream os = new FileOutputStream("D:/hscode/mall-category/backCategory.json");
        List<CategoryModel> res = aggregateResult(categoryModels.values());
        JSON.writeJSONString(os, res);
        os.close();
        System.out.println();
    }

    public static List<CategoryModel> aggregateResult(Collection<MallCategoryModel> mallCategoryModels){
        if(mallCategoryModels.isEmpty()){
            return Collections.emptyList();
        }
        List<CategoryModel> r = new ArrayList<>();
        for (MallCategoryModel mallCategoryModel : mallCategoryModels) {
            CategoryModel c = new CategoryModel();
            c.setCategoryNameCn(mallCategoryModel.getCategoryNameCn());
            c.setCategoryNameEn(mallCategoryModel.getCategoryNameEn());
            if(mallCategoryModel.getRelated()!=null){
                c.setRelated(mallCategoryModel.getRelated());
            }
            c.setSort(1);
            r.add(c);
            if(mallCategoryModel.getChilds()!=null) {
                c.setChilds(aggregateResult(mallCategoryModel.getChilds().values()));
            }
        }
        return r;
    }

    public static void insertProp(String propName, String categoryName1, String categoryName2, String categoryName3) {
        try {
            propName = URLEncoder.encode(propName, "utf-8");
            categoryName1 = URLEncoder.encode(categoryName1, "utf-8");
            categoryName2 = URLEncoder.encode(categoryName2, "utf-8");
            categoryName3 = URLEncoder.encode(categoryName3, "utf-8");
            String url = "http://192.168.10.158:8081/propsManage/batchAddCaItemProp?propName=" + propName + "&categoryName1=" + categoryName1 + "&categoryName2=" + categoryName2 + "&categoryName3=" + categoryName3;
            URL urlConnect = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnect.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            //设置是否从HttpURLConnection读入内容，默认为true
            connection.setDoInput(true);
            connection.setRequestProperty("Charset", "utf-8");
            connection.setRequestProperty("Connection", "keep-alive");
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(isr);
            String s = bufferedReader.readLine();
            if (!"true".equals(s)) {
                System.out.println("insert error : propName : " + propName + ",categoryName1 : " + categoryName1 + ",categoryName2 : " + categoryName2 + ",categoryName3 : " + categoryName3);
            } else {
                System.out.println("insert success : propName : " + propName + ",categoryName1 : " + categoryName1 + ",categoryName2 : " + categoryName2 + ",categoryName3 : " + categoryName3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据异常");
        }
    }

    public static Integer getId(String name, Integer parentId) {
        try {
            name = URLEncoder.encode(name.trim(), "utf-8");
            String url = "http://192.168.10.158:8081/category/addCaItemCategoryWithBack?categoryName=" + name + "&parentId=" + parentId;
            URL urlConnect = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnect.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            //设置是否从HttpURLConnection读入内容，默认为true
            connection.setDoInput(true);
            connection.setRequestProperty("Charset", "utf-8");
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(isr);
            String s = bufferedReader.readLine();
            System.err.println("insert " + name + " success");
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("insert " + name + " error");
            throw new RuntimeException("数据异常");
        }
    }

    public static void hsCatagory() {


    }

    public static void test() {

    }
}

