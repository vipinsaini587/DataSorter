package com.DataSorter.DataSorter.Dto;


import lombok.Data;
import java.util.List;
@Data
public class FindLocationCollection {
 private String radioType;
 private List<CellTowers> cellTowers;

 }
