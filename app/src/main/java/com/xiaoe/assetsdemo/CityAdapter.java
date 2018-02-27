package com.xiaoe.assetsdemo;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 *
 * Created by xiaoe on 2018/2/27.
 */

public class CityAdapter extends BaseQuickAdapter<City, BaseViewHolder> {


    public CityAdapter(@Nullable List<City> data) {
        super(R.layout.item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, City item) {
        helper.setText(R.id.name, item.getName());
        helper.setText(R.id.pinyin, item.getPinyin());
    }
}
