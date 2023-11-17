package com.nju.cloud;

import cn.hutool.json.JSONUtil;
import com.nju.cloud.pojo.Bill;
import com.nju.cloud.repository.BillRepository;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class NjuCloudApplicationTests {
    @Autowired
    private BillRepository billRepository;

    @Test
    void contextLoads() {
        Bill bill = new Bill();
        bill.setId("12222");
        bill.setTitle("lzm");
        billRepository.save(bill);
    }
    @Test
    public void insertToMongo() {
//        ArrayList jsonList = new ArrayList();
        List<Bill> billList = new ArrayList<>();
        JSONObject obj;
        // The name of the file to open.
        String fileName = "D:\\train.jsonl ";
        // This will reference one line at a time
        String line = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
//              obj = (JSONObject) new JSONParser().parse(line);
                cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(line);
                Bill bill = jsonObject.toBean(Bill.class);
                billList.add(bill);
//                jsonList.add(obj);
            }
            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
//          catch (ParseException e) {
//            e.printStackTrace();
//        }
        System.out.println("123");
        // 存入mongodb
        billRepository.saveAll(billList);

    }

}
