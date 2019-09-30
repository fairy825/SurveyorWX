package com.surveyor.mapper;

import java.util.List;

import com.surveyor.pojo.SearchRecords;
import com.surveyor.utils.MyMapper;

public interface SearchRecordsMapper extends MyMapper<SearchRecords> {
	public List<String> getHotWords();
}