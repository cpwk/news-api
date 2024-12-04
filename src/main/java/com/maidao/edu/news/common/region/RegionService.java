package com.maidao.edu.news.common.region;

import com.alibaba.fastjson.JSONArray;
import com.maidao.edu.news.common.util.R;
import com.sunnysuperman.commons.util.FileUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
@Service
public class RegionService {

    private List<Region> regions;

    @PostConstruct
    public void init() throws Exception {
        String data = FileUtil.read(R.getStream("pca_code.json"));
        regions = JSONArray.parseArray(data, Region.class);
    }

    private String[] getCodes(String code) {
        String codes[] = new String[3];
        if (code != null && code.length() == 6) {
            codes[0] = code.substring(0, 2);
            codes[1] = code.substring(0, 4);
            codes[2] = code;
        }
        return codes;
    }

    public String getPCD(String code) {
        if (regions == null || regions.size() == 0) {
            return "数据错误";
        }

        String codes[] = getCodes(code);
        StringBuilder pcd = new StringBuilder();

        for (Region r1 : regions) {
            if (r1.getValue().equals(codes[0])) {
                pcd.append(r1.getLabel());
                for (Region r2 : r1.getChildren()) {
                    if (r2.getValue().equals(codes[1])) {
                        pcd.append(" ");
                        pcd.append(r2.getLabel());
                        for (Region r3 : r2.getChildren()) {
                            if (r3.getValue().equals(codes[2])) {
                                pcd.append(" ");
                                pcd.append(r3.getLabel());
                            }
                        }
                    }

                }
            }
        }
        return pcd.toString();
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

}