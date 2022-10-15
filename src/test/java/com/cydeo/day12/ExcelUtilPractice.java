package com.cydeo.day12;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ExcelUtilPractice {


    @Test
    public void test1() {

        //how to use ExcelUtil ?
        //it accepts two arguments
        //Arg1: location of the file(path)
        //Arg2: worksheet that we want to open

        ExcelUtil vytrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx", "QA3-short");

        List<Map<String, String>> dataList = vytrackFile.getDataList();

        for (Map<String, String> rowMap : dataList) {
            System.out.println(rowMap);
        }

    }
}
