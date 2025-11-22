package com.example.cau2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SoftwareAdapter extends ArrayAdapter<Software> {

    private Context context;
    private List<Software> softwareList;

    public SoftwareAdapter(Context context, List<Software> softwareList) {
        super(context, 0, softwareList);
        this.context = context;
        this.softwareList = softwareList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.software_item, parent, false);
        }

        Software software = softwareList.get(position);

        TextView tvSoftId = convertView.findViewById(R.id.tv_soft_id);
        TextView tvSoftName = convertView.findViewById(R.id.tv_soft_name);
        TextView tvVersion = convertView.findViewById(R.id.tv_version);
        TextView tvPublish = convertView.findViewById(R.id.tv_publish);

        tvSoftId.setText("ID: " + software.getSoftId());
        tvSoftName.setText("Tên: " + software.getSoftName());
        tvVersion.setText("Phiên bản: " + software.getVersion());
        tvPublish.setText("Nhà phát hành: " + software.getPublish());

        return convertView;
    }
}