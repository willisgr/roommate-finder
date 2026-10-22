package com.roommatefinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatcherWeights {
    private double[] boolWeights;
    private double[] scaleWeights;
}
