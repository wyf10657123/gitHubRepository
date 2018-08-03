package util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.util.Map;

public class ExcelUtil2 {

    String[] cityNames=new String[]
            {"成都","达州","南充","重庆","郑州","广州","佛山","汕头","中山","太原","大连","北京","天津","石家庄","潍坊","济宁",
                    "杭州","宁波","温州","金华","台州","上海","无锡","","","","","","","",};

    public static void main(String[] args){
        Long companyId=28238L;
        readExcel(companyId);
        Map<String,Long> cityMap=CityUtil.getCityMap();
        System.out.println(cityMap);
    }


    public static void readExcel(Long companyId){
        FileInputStream fileInputStream=null;
        try{
            Map<String,Long> cityMap=CityUtil.getCityMap();
            //获取Excel文档的路径（这里Excel路径暂时固定，后期可能会作为参数获取。）
            String filePath = "D:\\downloads\\0322.xlsx";
            fileInputStream=new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(3);
            int rows = sheet.getLastRowNum();
            //校验第二行头部列数
            Row headRow=sheet.getRow(1);
            int cells = headRow.getLastCellNum();//返回最后一列不为空的单元格数
            StringBuilder sb=new StringBuilder();
            String sql="DELETE FROM lease_vehicle WHERE platenumber =";
            int count=0;
            for (int i = 1; i <= rows; i++) {
                Row row = sheet.getRow(i);

                //校验是否空行
                if(getNumOfRowNull(row)>=row.getLastCellNum()){
                    continue;
                }
                sb.append(sql);
                //城市
                Cell c0 = row.getCell(0);
                Object c0Obj = c0 == null ? null : ExcelUtil.getJavaValue(c0);
                if (null!= c0Obj && !StringUtils.isBlank(c0Obj.toString())) {
                    sb.append("'"+c0Obj+"'");
                    sb.append(";\n");
                    count++;
                }
            }
            System.out.println(sb);
            System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           try{
               fileInputStream.close();
           }catch (Exception e){
               e.printStackTrace();
           }
        }
    }

    private static int getNumOfRowNull(Row row){
        int num = 0;
        for(int i=0;i<row.getLastCellNum();i++){
            Cell cell=row.getCell(i);
            if(null==cell||cell.getCellType()==Cell.CELL_TYPE_BLANK){
                num++;
            }
        }
        return num;
    }

}
